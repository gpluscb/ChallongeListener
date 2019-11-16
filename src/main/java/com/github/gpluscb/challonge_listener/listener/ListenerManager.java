package com.github.gpluscb.challonge_listener.listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.github.gpluscb.challonge_listener.ChallongeExtension;
import com.github.gpluscb.challonge_listener.cache.ChallongeCache;
import com.github.gpluscb.challonge_listener.events.GenericEvent;
import com.github.gpluscb.challonge_listener.events.tournament.GenericTournamentChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.GenericTournamentEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentCreatedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentDeletedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentDoesOwnChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.GenericMatchChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchCreatedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchDeletedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentCreatedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentDeletedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.attachment.GenericAttachmentChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.GenericParticipantChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantCreatedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantDeletedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantMatchAddedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantMatchRemovedEvent;

import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

/**
 * The heart of this project. Requests all the tournaments specified by the
 * listeners at specific intervals and compares all of the tournaments. If a
 * difference is found, specific implementations of
 * {@link com.github.gpluscb.challonge_listener.events.GenericEvent
 * GenericEvents} are fired to all managed
 * {@link com.github.gpluscb.challonge_listener.listener.EventListener
 * EventListeners} that are subscribed to the tournament the event is from.
 * Events exist for all theoretically possible properties, including events that
 * will never be fired like
 * {@link com.github.gpluscb.challonge_listener.events.tournament.TournamentIdChangedEvent
 * TournamentIdChangedEvent}. If the id of a tournament really changed, it would
 * not be seen as the same tournament anymore, therefore a
 * {@link com.github.gpluscb.challonge_listener.events.tournament.TournamentDeletedEvent
 * TournamentDeletedEvent} would be fired rather than a
 * {@link com.github.gpluscb.challonge_listener.events.tournament.TournamentIdChangedEvent
 * TournamentIdChangedEvent}.
 * <h3>The manager can be in various states, the states progress as
 * follows:</h3>
 * <ol>
 * <li>{@link com.github.gpluscb.challonge_listener.listener.ListenerManager.ManagerState#INITIALIZING
 * INITIALIZING}</li>
 * <li>{@link com.github.gpluscb.challonge_listener.listener.ListenerManager.ManagerState#RUNNING
 * RUNNING}</li>
 * <li>{@link com.github.gpluscb.challonge_listener.listener.ListenerManager.ManagerState#SHUTTING_DOWN
 * SHUTTING_DOWN}</li>
 * <li>{@link com.github.gpluscb.challonge_listener.listener.ListenerManager.ManagerState#SHUT_DOWN
 * SHUT_DOWN}</li>
 * </ol>
 */
// TODO: Block setters of Tournament etc. or clone them.
// TODO: Know when to use synchronize
public class ListenerManager {
	private final ScheduledExecutorService executor;
	
	private ManagerState state;
	
	private final ChallongeExtension challonge;
	
	private final long interval;
	
	private final List<EventListener> managedListeners;
	
	private final ChallongeCache cache;
	
	/**
	 * Creates a running instance that tries to update every 5 seconds. Note
	 * that if something is changed but then changed back in-between updates the
	 * changes may not fire events.<br>
	 * The instance will run until the {@link ListenerManager#shutdown()
	 * shutdown()} method is called.
	 *
	 * @param challonge
	 *            The ChallongeExtension instance used to get all the tournament
	 *            data
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed
	 */
	public ListenerManager(final ChallongeExtension challonge) throws DataAccessException {
		this(challonge, 5000);
	}
	
	/**
	 * Creates a running instance that tries to update at the specified
	 * interval. Note that if something is changed but then changed back
	 * in-between updates the changes may not fire events.<br>
	 * The instance will run until the {@link ListenerManager#shutdown()
	 * shutdown()} method is called.
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
	public ListenerManager(final ChallongeExtension challonge, final long interval) throws DataAccessException {
		setState(ManagerState.INITIALIZING);
		
		// Check if the ChallongeExtension works in principle. Reducing failures
		// inside of ChallongeListener thread
		challonge.getTournaments();
		
		this.challonge = challonge;
		this.interval = interval;
		
		this.managedListeners = new ArrayList<>();
		this.cache = new ChallongeCache(Collections.emptyList());
		
		this.executor = Executors.newSingleThreadScheduledExecutor(runnable -> new Thread(runnable, "ListenerManager"));
		this.executor.scheduleAtFixedRate(new Runnable() {
			private List<TournamentWrapper> previousTournaments = null;
			
			@Override
			public void run() {
				try {
					this.previousTournaments = update(this.previousTournaments);
				} catch(final DataAccessException e) {
					System.out.println("ListenerManager: DataAccessException caught, trying to continue anyway: "
							+ e.getMessage());
				}
			}
		}, 0, Math.max(interval, 1), TimeUnit.MILLISECONDS);
		
		setState(ManagerState.RUNNING);
	}
	
	private List<TournamentWrapper> update(final List<TournamentWrapper> previousTournaments)
			throws DataAccessException {
		// Fetch current tournaments state
		final List<TournamentWrapper> currentTournaments = getSubscribedTournaments();
		this.cache.update(currentTournaments.stream().filter(TournamentWrapper::exists)
				.map(TournamentWrapper::getTournament).collect(Collectors.toList()));
		
		// Does nothing if prevoiusTournaments is null
		compareTournaments(previousTournaments, currentTournaments);
		
		return currentTournaments;
	}
	
	private List<TournamentWrapper> getSubscribedTournaments() throws DataAccessException {
		final Set<Long> subscribedToTournamentIds = new HashSet<>();
		
		for(final EventListener listener : this.managedListeners) {
			if(listener.getSubscribedTournamentIds() != null) {
				for(final Long tournamentId : listener.getSubscribedTournamentIds()) {
					if(tournamentId != null) {
						subscribedToTournamentIds.add(tournamentId);
					}
				}
			}
		}
		
		final List<Tournament> ownedTournaments = this.challonge.getTournaments();
		
		final List<TournamentWrapper> subscribedToTournaments = new ArrayList<>();
		
		for(final long tournamentId : subscribedToTournamentIds) {
			// TODO: Maybe that should be handled somewhere else in the
			// project. Like this it is quite ugly, but should somewhat
			// work.
			// Try to get tournament four times
			final int NUM_TRIES = 4;
			for(int i = 1; i <= NUM_TRIES; i++) {
				try {
					final Tournament tournament = this.challonge.getTournamentOrNull(String.valueOf(tournamentId), true,
							true, true);
					
					Boolean doesOwn = null;
					
					if(tournament != null) {
						doesOwn = Boolean.FALSE;
						for(final Tournament ownedTournament : ownedTournaments) {
							if(ownedTournament.getId().longValue() == tournamentId) {
								doesOwn = Boolean.TRUE;
								break;
							}
						}
					}
					
					subscribedToTournaments.add(new TournamentWrapper(Long.valueOf(tournamentId), tournament, doesOwn));
					
					break;
				} catch(final DataAccessException e) {
					if(i >= NUM_TRIES) {
						throw e;
					}
					System.out.println(
							"ListenerManager: DataAccessException caught while getting Tournaments, trying again: "
									+ e.getMessage());
				}
			}
		}
		
		return subscribedToTournaments;
	}
	
	private void compareTournaments(final List<TournamentWrapper> previousTournaments,
			final List<TournamentWrapper> currentTournaments) {
		if(currentTournaments != null && previousTournaments != null) {
			for(final TournamentWrapper currentTournament : currentTournaments) {
				for(final TournamentWrapper previousTournament : previousTournaments) {
					if(currentTournament.getTournamentId().longValue() == previousTournament.getTournamentId()
							.longValue()) {
						// They are the same
						if(previousTournament.exists()) {
							if(currentTournament.exists()) {
								// Both exist
								if(previousTournament.getDoesOwn().booleanValue()
										^ currentTournament.getDoesOwn().booleanValue()) {
									// doesOwn changed
									final TournamentDoesOwnChangedEvent event = new TournamentDoesOwnChangedEvent(
											currentTournament.getTournament(), previousTournament.getTournament(),
											currentTournament.getDoesOwn(), previousTournament.getDoesOwn());
									fireEvent(event);
								}
								
								// Compare tournament values
								compareTournamentValues(previousTournament.getTournament(),
										currentTournament.getTournament());
								// Compare participants
								compareParticipants(previousTournament.getTournament(),
										currentTournament.getTournament());
								// Compare matches
								compareMatches(previousTournament.getTournament(), currentTournament.getTournament());
							} else {
								// Was deleted
								final TournamentDeletedEvent event = new TournamentDeletedEvent(
										previousTournament.getTournament());
								fireEvent(event);
							}
						} else if(currentTournament.exists()) {
							// Was created
							final TournamentCreatedEvent event = new TournamentCreatedEvent(
									currentTournament.getTournament());
							fireEvent(event);
						}
						
						break;
					}
				}
			}
		}
	}
	
	private void compareParticipants(final Tournament previousTournament, final Tournament currentTournament) {
		if(currentTournament.getParticipants() != null && previousTournament.getParticipants() != null) {
			final List<Participant> deletedParticipants = new ArrayList<>(previousTournament.getParticipants());
			for(final Participant currentParticipant : currentTournament.getParticipants()) {
				boolean didExist = false;
				for(final Participant previousParticipant : previousTournament.getParticipants()) {
					if(currentParticipant.getId() == null ? previousParticipant.getId() == null
							: currentParticipant.getId().equals(previousParticipant.getId())) {
						// They are the same
						// Compare participant values
						compareParticipantValues(previousTournament, currentTournament, previousParticipant,
								currentParticipant);
						didExist = true;
						
						// previousParticipant still exists in the current state
						// and thus has not been
						// deleted
						deletedParticipants.remove(previousParticipant);
						
						// Compare participant matches
						compareParticipantMatches(previousTournament, currentTournament, previousParticipant,
								currentParticipant);
						break;
					}
				}
				
				if(!didExist) {
					final ParticipantCreatedEvent event = new ParticipantCreatedEvent(currentTournament,
							previousTournament, currentParticipant);
					fireEvent(event);
				}
			}
			
			for(final Participant deletedParticipant : deletedParticipants) {
				final ParticipantDeletedEvent event = new ParticipantDeletedEvent(currentTournament, previousTournament,
						deletedParticipant);
				fireEvent(event);
			}
		}
	}
	
	private void compareParticipantMatches(final Tournament previousTournament, final Tournament currentTournament,
			final Participant previousParticipant, final Participant currentParticipant) {
		// Only for comparison of list (as in was a match added or removed), not
		// match values. That happens on the tournament level.
		if(currentParticipant.getMatches() != null && previousParticipant.getMatches() != null) {
			final List<Match> deletedMatches = new ArrayList<>(previousParticipant.getMatches());
			for(final Match currentMatch : currentParticipant.getMatches()) {
				boolean didExist = false;
				for(final Match previousMatch : previousParticipant.getMatches()) {
					if(currentMatch.getId() == null ? previousMatch.getId() == null
							: currentMatch.getId().equals(previousMatch.getId())) {
						// They are the same
						didExist = true;
						
						// previousMatch still exists in the current state and
						// thus has not been
						// deleted
						deletedMatches.remove(previousMatch);
						
						break;
					}
				}
				
				if(!didExist) {
					final ParticipantMatchAddedEvent event = new ParticipantMatchAddedEvent(currentTournament,
							previousTournament, currentParticipant, previousParticipant, currentMatch);
					fireEvent(event);
				}
			}
			
			for(final Match deletedMatch : deletedMatches) {
				final ParticipantMatchRemovedEvent event = new ParticipantMatchRemovedEvent(currentTournament,
						previousTournament, currentParticipant, previousParticipant, deletedMatch);
				fireEvent(event);
			}
		}
	}
	
	private void compareMatches(final Tournament previousTournament, final Tournament currentTournament) {
		if(currentTournament.getMatches() != null && previousTournament.getMatches() != null) {
			final List<Match> deletedMatches = new ArrayList<>(previousTournament.getMatches());
			for(final Match currentMatch : currentTournament.getMatches()) {
				boolean didExist = false;
				for(final Match previousMatch : previousTournament.getMatches()) {
					if(currentMatch.getId() == null ? previousMatch.getId() == null
							: currentMatch.getId().equals(previousMatch.getId())) {
						// They are the same
						// Compare match values
						compareMatchValues(previousTournament, currentTournament, previousMatch, currentMatch);
						didExist = true;
						
						// previousMatch still exists in the current state and
						// thus has not been
						// deleted
						deletedMatches.remove(previousMatch);
						
						// Compare attachments
						compareAttachments(previousTournament, currentTournament, previousMatch, currentMatch);
						break;
					}
				}
				
				if(!didExist) {
					final MatchCreatedEvent event = new MatchCreatedEvent(currentTournament, previousTournament,
							currentMatch);
					fireEvent(event);
				}
			}
			
			for(final Match deletedMatch : deletedMatches) {
				final MatchDeletedEvent event = new MatchDeletedEvent(currentTournament, previousTournament,
						deletedMatch);
				fireEvent(event);
			}
		}
	}
	
	private void compareAttachments(final Tournament previousTournament, final Tournament currentTournament,
			final Match previousMatch, final Match currentMatch) {
		if(currentMatch.getAttachments() != null && previousMatch.getAttachments() != null) {
			final List<Attachment> deletedAttachments = new ArrayList<>(previousMatch.getAttachments());
			for(final Attachment currentAttachment : currentMatch.getAttachments()) {
				boolean didExist = false;
				for(final Attachment previousAttachment : previousMatch.getAttachments()) {
					if(currentAttachment.getId() == null ? previousAttachment.getId() == null
							: currentAttachment.getId().equals(previousAttachment.getId())) {
						// They are the same
						// Compare attachment values
						compareAttachmentValues(previousTournament, currentTournament, previousMatch, currentMatch,
								previousAttachment, currentAttachment);
						didExist = true;
						
						// previousAttachment still exists in the current state
						// and thus has not been
						// deleted
						deletedAttachments.remove(previousAttachment);
						
						break;
					}
				}
				
				if(!didExist) {
					final AttachmentCreatedEvent event = new AttachmentCreatedEvent(currentTournament,
							previousTournament, currentMatch, previousMatch, currentAttachment);
					fireEvent(event);
				}
			}
			
			for(final Attachment deletedAttachment : deletedAttachments) {
				final AttachmentDeletedEvent event = new AttachmentDeletedEvent(currentTournament, previousTournament,
						currentMatch, previousMatch, deletedAttachment);
				fireEvent(event);
			}
		}
	}
	
	private void compareTournamentValues(final Tournament previousTournament, final Tournament currentTournament) {
		final Method[] tournamentMethods = Tournament.class.getMethods();
		
		for(final Method method : tournamentMethods) {
			// Filter all getters
			if(method.getName().startsWith("get") && method.getReturnType() != void.class
					&& method.getParameterCount() == 0 && method.getModifiers() == Modifier.PUBLIC
					&& !method.getReturnType().equals(void.class)) {
				try {
					// Get the UpperCamelCase name of the property the getter
					// returns.
					final String propertyName = method.getName().substring(3);
					
					final Class<?> propertyType = method.getReturnType();
					
					// Get the properties to compare
					final Object currentProperty = method.invoke(currentTournament);
					final Object previousProperty = method.invoke(previousTournament);
					
					// Compare the two properties
					if(currentProperty == null ? previousProperty != null : !currentProperty.equals(previousProperty)) {
						// They are different
						// Get the corresponding TournamentChangedEvent
						final Class<?> eventClass = Class
								.forName("com.github.gpluscb.challonge_listener.events.tournament.Tournament"
										+ propertyName + "ChangedEvent");
						final Constructor<?> constructor = eventClass.getConstructor(Tournament.class, Tournament.class,
								propertyType, propertyType);
						// Create and fire the corresponding event
						final GenericTournamentChangedEvent event = (GenericTournamentChangedEvent) constructor
								.newInstance(currentTournament, previousTournament, currentProperty, previousProperty);
						fireEvent(event);
					}
				} catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException | InstantiationException
						| ClassNotFoundException e) {
					// If all TournamentChangedEvents exist, this should never
					// occur
					e.printStackTrace();
				}
			}
		}
	}
	
	private void compareParticipantValues(final Tournament previousTournament, final Tournament currentTournament,
			final Participant previousParticipant, final Participant currentParticipant) {
		final Method[] participantMethods = Participant.class.getMethods();
		
		for(final Method method : participantMethods) {
			// Filter all getters
			if(method.getName().startsWith("get") && method.getReturnType() != void.class
					&& method.getParameterCount() == 0 && method.getModifiers() == Modifier.PUBLIC
					&& !method.getReturnType().equals(void.class)) {
				try {
					// Get the UpperCamelCase name of the property the getter
					// returns.
					final String propertyName = method.getName().substring(3);
					
					final Class<?> propertyType = method.getReturnType();
					
					// Get the properties to compare
					final Object currentProperty = method.invoke(currentParticipant);
					final Object previousProperty = method.invoke(previousParticipant);
					
					// Compare the two properties
					if(currentProperty == null ? previousProperty != null : !currentProperty.equals(previousProperty)) {
						// They are different
						// Get the corresponding ParticipantChangedEvent
						final Class<?> eventClass = Class.forName(
								"com.github.gpluscb.challonge_listener.events.tournament.participant.Participant"
										+ propertyName + "ChangedEvent");
						final Constructor<?> constructor = eventClass.getConstructor(Tournament.class, Tournament.class,
								Participant.class, Participant.class, propertyType, propertyType);
						// Create and fire the corresponding event
						final GenericParticipantChangedEvent event = (GenericParticipantChangedEvent) constructor
								.newInstance(currentTournament, previousTournament, currentParticipant,
										previousParticipant, currentProperty, previousProperty);
						fireEvent(event);
					}
				} catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException | InstantiationException
						| ClassNotFoundException e) {
					// If all ParticipantChangedEvents exist, this should never
					// occur
					e.printStackTrace();
				}
			}
		}
	}
	
	private void compareMatchValues(final Tournament previousTournament, final Tournament currentTournament,
			final Match previousMatch, final Match currentMatch) {
		final Method[] matchMethods = Match.class.getMethods();
		
		for(final Method method : matchMethods) {
			// Filter all getters
			if(method.getName().startsWith("get") && method.getReturnType() != void.class
					&& method.getParameterCount() == 0 && method.getModifiers() == Modifier.PUBLIC
					&& !method.getReturnType().equals(void.class)) {
				try {
					// Get the UpperCamelCase name of the property the getter
					// returns
					final String propertyName = method.getName().substring(3);
					
					final Class<?> propertyType = method.getReturnType();
					
					// Get the properties to compare
					final Object currentProperty = method.invoke(currentMatch);
					final Object previousProperty = method.invoke(previousMatch);
					
					// Compare the two properties
					if(currentProperty == null ? previousProperty != null : !currentProperty.equals(previousProperty)) {
						// They are different
						// Get the corresponding MatchChangedEvent
						final Class<?> eventClass = Class
								.forName("com.github.gpluscb.challonge_listener.events.tournament.match.Match"
										+ propertyName + "ChangedEvent");
						final Constructor<?> constructor = eventClass.getConstructor(Tournament.class, Tournament.class,
								Match.class, Match.class, propertyType, propertyType);
						// Create and fire the corresponding event
						final GenericMatchChangedEvent event = (GenericMatchChangedEvent) constructor.newInstance(
								currentTournament, previousTournament, currentMatch, previousMatch, currentProperty,
								previousProperty);
						fireEvent(event);
					}
				} catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException | InstantiationException
						| ClassNotFoundException e) {
					// If all MatchChangedEvents exist, this should never occur
					e.printStackTrace();
				}
			}
		}
	}
	
	private void compareAttachmentValues(final Tournament previousTournament, final Tournament currentTournament,
			final Match previousMatch, final Match currentMatch, final Attachment previousAttachment,
			final Attachment currentAttachment) {
		final Method[] attachmentMethods = Attachment.class.getMethods();
		
		for(final Method method : attachmentMethods) {
			// Filter all getters
			if(method.getName().startsWith("get") && method.getReturnType() != void.class
					&& method.getParameterCount() == 0 && method.getModifiers() == Modifier.PUBLIC
					&& !method.getReturnType().equals(void.class)) {
				try {
					// Get the UpperCamelCase name of the property the getter
					// returns
					final String propertyName = method.getName().substring(3);
					
					final Class<?> propertyType = method.getReturnType();
					
					// Get the properties to compare
					final Object currentProperty = method.invoke(currentAttachment);
					final Object previousProperty = method.invoke(previousAttachment);
					
					// Compare the two properties
					if(currentProperty == null ? previousProperty != null : !currentProperty.equals(previousProperty)) {
						// They are different
						// Get the corresponding AttachmentChangedEvent
						final Class<?> eventClass = Class.forName(
								"com.github.gpluscb.challonge_listener.events.tournament.match.attachment.Attachment"
										+ propertyName + "ChangedEvent");
						final Constructor<?> constructor = eventClass.getConstructor(Tournament.class, Tournament.class,
								Match.class, Match.class, Attachment.class, Attachment.class, propertyType,
								propertyType);
						// Create and fire the corresponding event
						final GenericAttachmentChangedEvent event = (GenericAttachmentChangedEvent) constructor
								.newInstance(currentTournament, previousTournament, currentMatch, previousMatch,
										currentAttachment, previousAttachment, currentProperty, previousProperty);
						fireEvent(event);
					}
				} catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException | InstantiationException
						| ClassNotFoundException e) {
					// If all AttachmentChangedEvents exist, this should never
					// occur
					e.printStackTrace();
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
	private void fireEvent(final GenericEvent event) {
		for(final EventListener listener : this.managedListeners) {
			for(final long tournamentId : listener.getSubscribedTournamentIds()) {
				if(((GenericTournamentEvent) event).getTournament().getId().longValue() == tournamentId) {
					listener.onEvent(event);
					break;
				}
			}
		}
		
		// TODO: Optimization: check for *(Deleted|Created)Events, apply that to
		// cache instead of iterating over everything there
	}
	
	private void setState(final ManagerState state) {
		final ManagerState prevState = this.state;
		this.state = state;
		if(prevState != null) {
			synchronized(prevState) {
				prevState.notify();
			}
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
	public void awaitState(final ManagerState state) throws InterruptedException, IllegalStateException {
		if(!this.state.isReachable(state)) {
			throw new IllegalStateException("The given state is unreachable.");
		}
		
		while(this.state.isBefore(state)) {
			synchronized(this.state) {
				this.state.wait();
			}
		}
	}
	
	/**
	 * Shuts down the update cycle and removes all listeners from its managed
	 * listeners list. After the shutdown is complete this instance is
	 * completely useless.
	 */
	public void shutdown() {
		if(this.state.isBefore(ManagerState.SHUTTING_DOWN)) {
			setState(ManagerState.SHUTTING_DOWN);
			this.executor.shutdown();
			
			new Thread(() -> {
				try {
					while(!this.executor.awaitTermination(10, TimeUnit.SECONDS)) {}
					setState(ManagerState.SHUT_DOWN);
				} catch(final InterruptedException e) {
					e.printStackTrace();
				}
			}, "ListenerManager OnShutdownChangeState").start();
		}
	}
	
	/**
	 * The interval in milliseconds the listener tries to update in.
	 *
	 * @return The interval in milliseconds
	 */
	public long getInterval() {
		return this.interval;
	}
	
	/**
	 * Adds all of the listeners to the list of managed listeners. Any future
	 * events will be fired on these listeners as well.
	 *
	 * @param listener
	 *            The listeners to be added
	 */
	public void addListener(final EventListener... listener) {
		this.managedListeners.addAll(Arrays.asList(listener));
	}
	
	/**
	 * Removes all the specified listeners from the list of managed listeners if
	 * they exist in that list.
	 *
	 * @param listener
	 *            The listeners to remove from the list of managed listeners
	 * @return true if the list of managed listeners changed as a result of this
	 *             call.
	 */
	public boolean removeListener(final EventListener... listener) {
		return this.managedListeners.removeAll(Arrays.asList(listener));
	}
	
	/**
	 * List of all the listeners the manager currently manages.
	 *
	 * @return All managed listeners
	 */
	public List<EventListener> getListeners() {
		return this.managedListeners;
	}
	
	/**
	 * The current state of the manager.
	 *
	 * @return The state of the manager
	 */
	public ManagerState getState() {
		return this.state;
	}
	
	/**
	 * Whether the update cycle is running.
	 *
	 * @return true if the instance has not been shut down yet
	 * @deprecated Use {@link com.github.gpluscb.challonge_listener.listener.ListenerManager#getState()
	 *                 getState()} instead
	 */
	@Deprecated
	public boolean isRunning() {
		return this.state.equals(ManagerState.RUNNING);
	}
	
	/**
	 * Gets the cache of all of the managed tournaments. The instance will not
	 * change, only contents will be updated.
	 * 
	 * @return The cache of the managed tournaments
	 */
	public ChallongeCache getCache() {
		return this.cache;
	}
	
	/**
	 * Represents a possible state of a ListenerManager.<br>
	 * The states progress as follows:
	 * <ol>
	 * <li>{@link com.github.gpluscb.challonge_listener.listener.ListenerManager.ManagerState#INITIALIZING
	 * INITIALIZING}</li>
	 * <li>{@link com.github.gpluscb.challonge_listener.listener.ListenerManager.ManagerState#RUNNING
	 * RUNNING}</li>
	 * <li>{@link com.github.gpluscb.challonge_listener.listener.ListenerManager.ManagerState#SHUTTING_DOWN
	 * SHUTTING_DOWN}</li>
	 * <li>{@link com.github.gpluscb.challonge_listener.listener.ListenerManager.ManagerState#SHUT_DOWN
	 * SHUT_DOWN}</li>
	 * </ol>
	 */
	public enum ManagerState {
		/**
		 * The Manager is currently initializing.
		 */
		INITIALIZING(0),
		/**
		 * The Manager is currently in its core loop.
		 */
		RUNNING(1),
		/**
		 * The Manager is currently shutting down.
		 */
		SHUTTING_DOWN(2),
		/**
		 * The manager has been shut down. The instance is useless.
		 */
		SHUT_DOWN(3);
		
		private int stateLayer;
		
		private ManagerState(final int stateLayer) {
			this.stateLayer = stateLayer;
		}
		
		/**
		 * Returns the amount of possible states before it.
		 * 
		 * @return The layer of the state.
		 */
		public int getStateLayer() {
			return this.stateLayer;
		}
		
		/**
		 * Returns whether a state can be reached from this state. Returns
		 * {@code true}, if the given state and the own state are the same.
		 * 
		 * @param state
		 *            The state to check
		 * @return Whether the state can be reached
		 */
		public boolean isReachable(final ManagerState state) {
			return this.stateLayer <= state.getStateLayer();
		}
		
		/**
		 * Returns whether the own state is before another state.
		 * 
		 * @param state
		 *            The state to check
		 * @return Whether the state is before the own state
		 */
		public boolean isBefore(final ManagerState state) {
			return this.stateLayer < state.getStateLayer();
		}
		
		/**
		 * Returns whether a state is after the own state.
		 * 
		 * @param state
		 *            The state to check
		 * @return Whether the state is after the own state
		 */
		public boolean isBeyond(final ManagerState state) {
			return this.stateLayer > state.getStateLayer();
		}
	}
	
	private static class TournamentWrapper {
		private final Long tournamentId;
		private final Tournament tournament;
		private final Boolean doesOwn;
		
		public TournamentWrapper(final Long tournamentId, final Tournament tournament, final Boolean doesOwn) {
			this.tournamentId = tournamentId;
			this.tournament = tournament;
			this.doesOwn = doesOwn;
		}
		
		public Long getTournamentId() {
			return this.tournamentId;
		}
		
		public Tournament getTournament() {
			return this.tournament;
		}
		
		public Boolean getDoesOwn() {
			return this.doesOwn;
		}
		
		public boolean exists() {
			return this.tournament != null;
		}
	}
}
