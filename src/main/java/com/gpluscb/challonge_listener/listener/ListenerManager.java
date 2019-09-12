package com.gpluscb.challonge_listener.listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;
import com.gpluscb.challonge_listener.ChallongeExtension;
import com.gpluscb.challonge_listener.events.GenericEvent;
import com.gpluscb.challonge_listener.events.tournament.*;
import com.gpluscb.challonge_listener.events.tournament.match.*;
import com.gpluscb.challonge_listener.events.tournament.match.attachment.*;
import com.gpluscb.challonge_listener.events.tournament.participant.*;

/**
 * The heart of this project. Requests all the tournament created or co-owned by
 * your account at specific intervals and compares all of the tournaments. If a
 * difference is found,
 * {@link com.gpluscb.challonge_listener.events.GenericEvent GenericEvents} are
 * fired to all managed
 * {@link com.gpluscb.challonge_listener.listener.EventListener EventListeners}.
 * Events exist for all theoretically possible properties, including events that
 * will never be fired like
 * {@link com.gpluscb.challonge_listener.events.tournament.TournamentIdChangedEvent
 * TournamentIdChangedEvent}. If the id of a tournament really changed, it would
 * not be seen as the same tournament anymore, therefore a
 * {@link com.gpluscb.challonge_listener.events.tournament.TournamentDeletedEvent
 * TournamentDeletedEvent} and a
 * {@link com.gpluscb.challonge_listener.events.tournament.TournamentCreatedEvent
 * TournamentCreatedEvent} would be fired rather than a
 * {@link com.gpluscb.challonge_listener.events.tournament.TournamentIdChangedEvent
 * TournamentIdChangedEvent}.
 * <h3>The manager can be in various states, the states progress as
 * follows:</h3>
 * <ul>
 * <li>INITIALIZING</li>
 * <li>RUNNING</li>
 * <li>SHUTTING_DOWN</li>
 * <li>SHUT_DOWN</li>
 * </ul>
 */
// TODO: Maybe make a void update() method public (would need Tournament
// previousState field for that) for manual update handling after shutdown or
// maybe even include a constructor that allows the listenerThread to not start.
// Can't think of a good use case right now.
public class ListenerManager {
	public static enum ManagerState {
		INITIALIZING(0), RUNNING(1), SHUTTING_DOWN(2), SHUT_DOWN(3);
		
		private int stateLayer;
		
		private ManagerState(int stateLayer) {
			this.stateLayer = stateLayer;
		}
		
		public int getStateLayer() {
			return stateLayer;
		}
	}
	
	private static Logger LOG = LogManager.getLogger(ListenerManager.class);
	
	// TODO: potentially find better solution. Also get a clue about
	// synchronized things.
	// Handles wait inside of ChallongeListener thread and notify outside of it.
	private Object sync = new Object();
	private Thread listenerThread;
	
	private ManagerState state;
	
	private ChallongeExtension challonge;
	
	private long interval;
	
	private List<EventListener> managedListeners;
	
	/**
	 * Creates a running instance that tries to update every 5 seconds.<br>
	 * The instance will run until the {@link ListenerManager#shutdown()
	 * shutdown()} method is called or the garbage collector destroys it.
	 * 
	 * @param challonge
	 *            The ChallongeExtension instance used to get all the tournament
	 *            data
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed
	 */
	public ListenerManager(ChallongeExtension challonge) throws DataAccessException {
		this(challonge, 5000, null);
	}
	
	/**
	 * Creates a running instance that tries to update at the specified
	 * interval. Note that if something is changed but then changed back
	 * in-between updates the changes may not fire events.<br>
	 * The instance will run until the {@link ListenerManager#shutdown()
	 * shutdown()} method is called or the garbage collector destroys it.
	 * 
	 * @param challonge
	 *            The ChallongeExtension instance used to get all the tournament
	 *            data
	 * @param interval
	 *            The interval to try to update at in milliseconds. If the
	 *            interval is 0, the manager tries to update as quickly as
	 *            possible
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed
	 */
	public ListenerManager(ChallongeExtension challonge, long interval) throws DataAccessException {
		this(challonge, interval, null);
	}
	
	/**
	 * Creates a running instance that tries to update at the specified
	 * interval. Note that if something is changed but then changed back
	 * in-between updates the changes may not fire events.<br>
	 * The instance will run until the {@link ListenerManager#shutdown()
	 * shutdown()} method is called or the garbage collector destroys it.
	 * 
	 * @param challonge
	 *            The ChallongeExtension instance used to get all the tournament
	 *            data
	 * @param interval
	 *            The interval to try to update at in milliseconds. If the
	 *            interval is 0, the manager tries to update as quickly as
	 *            possible
	 * @param tournaments
	 *            The state of tournaments to be the initial state. At first,
	 *            the current state will be compared to this state
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed
	 */
	public ListenerManager(ChallongeExtension challonge, long interval, List<Tournament> tournaments)
			throws DataAccessException {
		state = ManagerState.INITIALIZING;
		
		// Check if the ChallongeExtension works in principle. Reducing failures
		// inside of ChallongeListener thread
		challonge.getTournaments();
		
		this.challonge = challonge;
		this.interval = interval;
		managedListeners = new ArrayList<>();
		
		listenerThread = new Thread(() -> {
			try {
				List<Tournament> previousTournaments = tournaments;
				
				if(previousTournaments == null) {
					// TODO: Maybe that should be handled somewhere else in the
					// project. Like this it is quite ugly, but should somewhat
					// work.
					// Try to get tournament four times
					final int NUM_TRIES = 4;
					for(int i = 1; i <= NUM_TRIES; i++) {
						try {
							previousTournaments = challonge.getTournamentsWithFullData();
							break;
						} catch(DataAccessException e) {
							if(i >= NUM_TRIES) throw e;
							LOG.warn("DataAccessException caught while initializing, trying again.");
							LOG.catching(Level.WARN, e);
						}
					}
				}
				
				state = ManagerState.RUNNING;
				
				while(state.equals(ManagerState.RUNNING)) {
					long cycleStartTime = System.currentTimeMillis();
					
					try {
						previousTournaments = update(previousTournaments);
					} catch(DataAccessException e) {
						LOG.warn("DataAccessException caught, trying to continue anyway.");
						LOG.catching(e);
					}
					
					long cycleTime = System.currentTimeMillis() - cycleStartTime;
					long waitTime = interval - cycleTime;
					synchronized(sync) {
						if(waitTime > 0) sync.wait(waitTime);
					}
				}
			} catch(InterruptedException | DataAccessException e) {
				LOG.error("Exception caught in ChallongeListener thread, shutting down.");
				LOG.catching(e);
				shutdown();
			}
			
			state = ManagerState.SHUT_DOWN;
		}, "ChallongeListener");
		
		listenerThread.start();
	}
	
	private List<Tournament> update(List<Tournament> previousTournaments) throws DataAccessException {
		// Fetch current tournaments state
		List<Tournament> currentTournaments = challonge.getTournamentsWithFullData();
		
		if(previousTournaments != null) {
			compareTournaments(previousTournaments, currentTournaments);
		}
		
		return currentTournaments;
	}
	
	private void compareTournaments(List<Tournament> previousTournaments, List<Tournament> currentTournaments) {
		if(currentTournaments != null && previousTournaments != null) {
			List<Tournament> deletedTournaments = new ArrayList<>(previousTournaments);
			for(Tournament currentTournament : currentTournaments) {
				boolean didExist = false;
				for(Tournament previousTournament : previousTournaments) {
					if(currentTournament.getId() == null ? previousTournament.getId() == null
							: currentTournament.getId().equals(previousTournament.getId())) {
						// They are the same
						// Compare tournament values
						compareTournamentValues(previousTournament, currentTournament);
						// Compare participants
						compareParticipants(previousTournament, currentTournament);
						// Compare matches
						compareMatches(previousTournament, currentTournament);
						
						didExist = true;
						// previousTournament still exists in the
						// current state and thus has not been
						// deleted
						deletedTournaments.remove(previousTournament);
						break;
					}
				}
				
				if(!didExist) {
					TournamentCreatedEvent event = new TournamentCreatedEvent(currentTournament);
					fireEvent(event);
				}
			}
			
			for(Tournament deletedTournament : deletedTournaments) {
				TournamentDeletedEvent event = new TournamentDeletedEvent(deletedTournament);
				fireEvent(event);
			}
		}
	}
	
	private void compareParticipants(Tournament previousTournament, Tournament currentTournament) {
		if(currentTournament.getParticipants() != null && previousTournament.getParticipants() != null) {
			List<Participant> deletedParticipants = new ArrayList<>(previousTournament.getParticipants());
			for(Participant currentParticipant : currentTournament.getParticipants()) {
				boolean didExist = false;
				for(Participant previousParticipant : previousTournament.getParticipants()) {
					if(currentParticipant.getId() == null ? previousParticipant.getId() == null
							: currentParticipant.getId().equals(previousParticipant.getId())) {
						// They are the same
						// Compare participant values
						compareParticipantValues(previousTournament, currentTournament, previousParticipant,
								currentParticipant);
						didExist = true;
						
						// previousParticipant still exists in the
						// current state and thus has not been
						// deleted
						deletedParticipants.remove(previousParticipant);
						
						// Compare participant matches
						compareParticipantMatches(previousTournament, currentTournament, previousParticipant,
								currentParticipant);
						break;
					}
				}
				
				if(!didExist) {
					ParticipantCreatedEvent event = new ParticipantCreatedEvent(currentTournament, previousTournament,
							currentParticipant);
					fireEvent(event);
				}
			}
			
			for(Participant deletedParticipant : deletedParticipants) {
				ParticipantDeletedEvent event = new ParticipantDeletedEvent(currentTournament, previousTournament,
						deletedParticipant);
				fireEvent(event);
			}
		}
	}
	
	private void compareParticipantMatches(Tournament previousTournament, Tournament currentTournament,
			Participant previousParticipant, Participant currentParticipant) {
		// Only for comparison of list (as in was a match added or removed), not
		// match values. That happens on the tournament level.
		if(currentParticipant.getMatches() != null && previousParticipant.getMatches() != null) {
			List<Match> deletedMatches = new ArrayList<>(previousParticipant.getMatches());
			for(Match currentMatch : currentParticipant.getMatches()) {
				boolean didExist = false;
				for(Match previousMatch : previousParticipant.getMatches()) {
					if(currentMatch.getId() == null ? previousMatch.getId() == null
							: currentMatch.getId().equals(previousMatch.getId())) {
						// They are the same
						didExist = true;
						
						// previousMatch still exists in the
						// current state and thus has not been
						// deleted
						deletedMatches.remove(previousMatch);
						
						break;
					}
				}
				
				if(!didExist) {
					ParticipantMatchAddedEvent event = new ParticipantMatchAddedEvent(currentTournament,
							previousTournament, currentParticipant, previousParticipant, currentMatch);
					fireEvent(event);
				}
			}
			
			for(Match deletedMatch : deletedMatches) {
				ParticipantMatchRemovedEvent event = new ParticipantMatchRemovedEvent(currentTournament,
						previousTournament, currentParticipant, previousParticipant, deletedMatch);
				fireEvent(event);
			}
		}
	}
	
	private void compareMatches(Tournament previousTournament, Tournament currentTournament) {
		if(currentTournament.getMatches() != null && previousTournament.getMatches() != null) {
			List<Match> deletedMatches = new ArrayList<>(previousTournament.getMatches());
			for(Match currentMatch : currentTournament.getMatches()) {
				boolean didExist = false;
				for(Match previousMatch : previousTournament.getMatches()) {
					if(currentMatch.getId() == null ? previousMatch.getId() == null
							: currentMatch.getId().equals(previousMatch.getId())) {
						// They are the same
						// Compare match values
						compareMatchValues(previousTournament, currentTournament, previousMatch, currentMatch);
						didExist = true;
						
						// previousMatch still exists in the
						// current state and thus has not been
						// deleted
						deletedMatches.remove(previousMatch);
						
						// Compare attachments
						compareAttachments(previousTournament, currentTournament, previousMatch, currentMatch);
						break;
					}
				}
				
				if(!didExist) {
					MatchCreatedEvent event = new MatchCreatedEvent(currentTournament, previousTournament,
							currentMatch);
					fireEvent(event);
				}
			}
			
			for(Match deletedMatch : deletedMatches) {
				MatchDeletedEvent event = new MatchDeletedEvent(currentTournament, previousTournament, deletedMatch);
				fireEvent(event);
			}
		}
	}
	
	private void compareAttachments(Tournament previousTournament, Tournament currentTournament, Match previousMatch,
			Match currentMatch) {
		if(currentMatch.getAttachments() != null && previousMatch.getAttachments() != null) {
			List<Attachment> deletedAttachments = new ArrayList<>(previousMatch.getAttachments());
			for(Attachment currentAttachment : currentMatch.getAttachments()) {
				boolean didExist = false;
				for(Attachment previousAttachment : previousMatch.getAttachments()) {
					if(currentAttachment.getId() == null ? previousAttachment.getId() == null
							: currentAttachment.getId().equals(previousAttachment.getId())) {
						// They are the same
						// Compare attachment values
						compareAttachmentValues(previousTournament, currentTournament, previousMatch, currentMatch,
								previousAttachment, currentAttachment);
						didExist = true;
						
						// previousAttachment still exists in the
						// current state and thus has not been
						// deleted
						deletedAttachments.remove(previousAttachment);
						
						break;
					}
				}
				
				if(!didExist) {
					AttachmentCreatedEvent event = new AttachmentCreatedEvent(currentTournament, previousTournament,
							currentMatch, previousMatch, currentAttachment);
					fireEvent(event);
				}
			}
			
			for(Attachment deletedAttachment : deletedAttachments) {
				AttachmentDeletedEvent event = new AttachmentDeletedEvent(currentTournament, previousTournament,
						currentMatch, previousMatch, deletedAttachment);
				fireEvent(event);
			}
		}
	}
	
	private void compareTournamentValues(Tournament previousTournament, Tournament currentTournament) {
		Method[] tournamentMethods = Tournament.class.getMethods();
		
		for(Method method : tournamentMethods) {
			// Filter all getters
			if(method.getName().startsWith("get") && method.getReturnType() != void.class
					&& method.getParameterCount() == 0 && method.getModifiers() == Modifier.PUBLIC
					&& !method.getReturnType().equals(void.class)) {
				try {
					// Get the UpperCamelCase name of the property the getter
					// returns.
					String propertyName = method.getName().substring(3);
					
					Class<?> propertyType = method.getReturnType();
					
					// Get the properties to compare
					Object currentProperty = method.invoke(currentTournament);
					Object previousProperty = method.invoke(previousTournament);
					
					// Compare the two properties
					if(currentProperty == null ? previousProperty != null : !currentProperty.equals(previousProperty)) {
						// They are different
						// Get the corresponding TournamentChangedEvent
						Class<?> eventClass = Class
								.forName("com.gpluscb.challonge_listener.events.tournament.Tournament" + propertyName
										+ "ChangedEvent");
						Constructor<?> constructor = eventClass.getConstructor(Tournament.class, Tournament.class,
								propertyType, propertyType);
						// Create and fire the corresponding event
						GenericTournamentChangedEvent event = (GenericTournamentChangedEvent) constructor
								.newInstance(currentTournament, previousTournament, currentProperty, previousProperty);
						fireEvent(event);
					}
				} catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException | InstantiationException
						| ClassNotFoundException e) {
					// If all TournamentChangedEvents exist, this should never
					// occur
					LOG.catching(e);
				}
			}
		}
	}
	
	private void compareParticipantValues(Tournament previousTournament, Tournament currentTournament,
			Participant previousParticipant, Participant currentParticipant) {
		Method[] participantMethods = Participant.class.getMethods();
		
		for(Method method : participantMethods) {
			// Filter all getters
			if(method.getName().startsWith("get") && method.getReturnType() != void.class
					&& method.getParameterCount() == 0 && method.getModifiers() == Modifier.PUBLIC
					&& !method.getReturnType().equals(void.class)) {
				try {
					// Get the UpperCamelCase name of the property the getter
					// returns.
					String propertyName = method.getName().substring(3);
					
					Class<?> propertyType = method.getReturnType();
					
					// Get the properties to compare
					Object currentProperty = method.invoke(currentParticipant);
					Object previousProperty = method.invoke(previousParticipant);
					
					// Compare the two properties
					if(currentProperty == null ? previousProperty != null : !currentProperty.equals(previousProperty)) {
						// They are different
						// Get the corresponding ParticipantChangedEvent
						Class<?> eventClass = Class
								.forName("com.gpluscb.challonge_listener.events.tournament.participant.Participant"
										+ propertyName + "ChangedEvent");
						Constructor<?> constructor = eventClass.getConstructor(Tournament.class, Tournament.class,
								Participant.class, Participant.class, propertyType, propertyType);
						// Create and fire the corresponding event
						GenericParticipantChangedEvent event = (GenericParticipantChangedEvent) constructor.newInstance(
								currentTournament, previousTournament, currentParticipant, previousParticipant,
								currentProperty, previousProperty);
						fireEvent(event);
					}
				} catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException | InstantiationException
						| ClassNotFoundException e) {
					// If all ParticipantChangedEvents exist, this should never
					// occur
					LOG.catching(e);
				}
			}
		}
	}
	
	private void compareMatchValues(Tournament previousTournament, Tournament currentTournament, Match previousMatch,
			Match currentMatch) {
		Method[] matchMethods = Match.class.getMethods();
		
		for(Method method : matchMethods) {
			// Filter all getters
			if(method.getName().startsWith("get") && method.getReturnType() != void.class
					&& method.getParameterCount() == 0 && method.getModifiers() == Modifier.PUBLIC
					&& !method.getReturnType().equals(void.class)) {
				try {
					// Get the UpperCamelCase name of the property the getter
					// returns
					String propertyName = method.getName().substring(3);
					
					Class<?> propertyType = method.getReturnType();
					
					// Get the properties to compare
					Object currentProperty = method.invoke(currentMatch);
					Object previousProperty = method.invoke(previousMatch);
					
					// Compare the two properties
					if(currentProperty == null ? previousProperty != null : !currentProperty.equals(previousProperty)) {
						// They are different
						// Get the corresponding MatchChangedEvent
						Class<?> eventClass = Class
								.forName("com.gpluscb.challonge_listener.events.tournament.match.Match" + propertyName
										+ "ChangedEvent");
						Constructor<?> constructor = eventClass.getConstructor(Tournament.class, Tournament.class,
								Match.class, Match.class, propertyType, propertyType);
						// Create and fire the corresponding event
						GenericMatchChangedEvent event = (GenericMatchChangedEvent) constructor.newInstance(
								currentTournament, previousTournament, currentMatch, previousMatch, currentProperty,
								previousProperty);
						fireEvent(event);
					}
				} catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException | InstantiationException
						| ClassNotFoundException e) {
					// If all MatchChangedEvents exist, this should never occur
					LOG.catching(e);
				}
			}
		}
	}
	
	private void compareAttachmentValues(Tournament previousTournament, Tournament currentTournament,
			Match previousMatch, Match currentMatch, Attachment previousAttachment, Attachment currentAttachment) {
		Method[] attachmentMethods = Attachment.class.getMethods();
		
		for(Method method : attachmentMethods) {
			// Filter all getters
			if(method.getName().startsWith("get") && method.getReturnType() != void.class
					&& method.getParameterCount() == 0 && method.getModifiers() == Modifier.PUBLIC
					&& !method.getReturnType().equals(void.class)) {
				try {
					// Get the UpperCamelCase name of the property the getter
					// returns
					String propertyName = method.getName().substring(3);
					
					Class<?> propertyType = method.getReturnType();
					
					// Get the properties to compare
					Object currentProperty = method.invoke(currentAttachment);
					Object previousProperty = method.invoke(previousAttachment);
					
					// Compare the two properties
					if(currentProperty == null ? previousProperty != null : !currentProperty.equals(previousProperty)) {
						// They are different
						// Get the corresponding AttachmentChangedEvent
						Class<?> eventClass = Class
								.forName("com.gpluscb.challonge_listener.events.tournament.match.attachment.Attachment"
										+ propertyName + "ChangedEvent");
						Constructor<?> constructor = eventClass.getConstructor(Tournament.class, Tournament.class,
								Match.class, Match.class, Attachment.class, Attachment.class, propertyType,
								propertyType);
						// Create and fire the corresponding event
						GenericAttachmentChangedEvent event = (GenericAttachmentChangedEvent) constructor.newInstance(
								currentTournament, previousTournament, currentMatch, previousMatch, currentAttachment,
								previousAttachment, currentProperty, previousProperty);
						fireEvent(event);
					}
				} catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException | InstantiationException
						| ClassNotFoundException e) {
					// If all AttachmentChangedEvents exist, this should never
					// occur
					LOG.catching(e);
				}
			}
		}
	}
	
	/**
	 * Fires the event to all of the managed listeners
	 * 
	 * @param event
	 *            The event to be fired
	 */
	private void fireEvent(GenericEvent event) {
		for(EventListener listener : managedListeners) {
			listener.onEvent(event);
		}
	}
	
	/**
	 * Blocks the current thread until the main cycle is started.
	 * 
	 * @throws InterruptedException
	 *             if the thread is interrupted
	 * @throws IllegalStateException
	 *             if the manager is shutting down or shut down
	 */
	public void awaitRunning() throws InterruptedException, IllegalStateException {
		awaitState(ManagerState.RUNNING);
	}
	
	/**
	 * Blocks the current thread until the given state is reached
	 * 
	 * @param state
	 *            The state to be reached
	 * @throws InterruptedException
	 *             if the thread is interrupted
	 * @throws IllegalStateException
	 *             if the given state is unreachable
	 */
	public void awaitState(ManagerState state) throws InterruptedException, IllegalStateException {
		if(state.getStateLayer() < this.state.getStateLayer())
			throw new IllegalStateException("The given state is unreachable.");
		
		while(!state.equals(this.state))
			Thread.sleep(10);
	}
	
	/**
	 * Shuts down the update cycle and removes all listeners from its managed
	 * listeners list. After the shutdown is complete this instance is
	 * completely useless.
	 */
	public void shutdown() {
		if(state.getStateLayer() <= ManagerState.RUNNING.getStateLayer()) {
			state = ManagerState.SHUTTING_DOWN;
			
			synchronized(sync) {
				sync.notify();
			}
			
			managedListeners.clear();
		}
	}
	
	/**
	 * Adjusts the interval the tournaments are updated at.
	 * 
	 * @param interval
	 *            The new interval to try to update at in milliseconds. If it is
	 *            0, it tries to update as quickly as possible.
	 */
	public void setInterval(long interval) {
		this.interval = interval;
	}
	
	/**
	 * The interval in milliseconds the listener tries to update in.
	 * 
	 * @return The interval in milliseconds
	 */
	public long getInterval() {
		return interval;
	}
	
	/**
	 * Adds all of the listeners to the list of managed listeners. Any future
	 * events will be fired on these listeners as well.
	 * 
	 * @param listener
	 *            The listeners to be added
	 */
	public void addListener(EventListener... listener) {
		managedListeners.addAll(Arrays.asList(listener));
	}
	
	/**
	 * Removes all the specified listeners from the list of managed listeners if
	 * they exist in that list.
	 * 
	 * @param listener
	 *            The listeners to remove from the list of managed listeners
	 * @return true if the list of managed listeners changed as a result of this
	 *         call.
	 */
	public boolean removeListener(EventListener... listener) {
		return managedListeners.removeAll(Arrays.asList(listener));
	}
	
	/**
	 * List of all the listeners the manager currently manages.
	 * 
	 * @return All managed listeners
	 */
	public List<EventListener> getListeners() {
		return managedListeners;
	}
	
	/**
	 * The current state of the manager.
	 * 
	 * @return The state of the manager
	 */
	public ManagerState getState() {
		return state;
	}
	
	/**
	 * Whether the update cycle is running.
	 * 
	 * @return true if the instance has not been shut down yet
	 * 
	 * @deprecated Use
	 *             {@link com.gpluscb.challonge_listener.listener.ListenerManager#getState()
	 *             getState()} instead
	 */
	public boolean isRunning() {
		return state.equals(ManagerState.RUNNING);
	}
	
	@Override
	protected void finalize() {
		shutdown();
	}
}
