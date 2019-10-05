package com.gpluscb.challonge_listener;

import java.util.Scanner;

import com.gpluscb.challonge_listener.events.tournament.GenericTournamentEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentDescriptionChangedEvent;
import com.gpluscb.challonge_listener.listener.ListenerAdapter;
import com.gpluscb.challonge_listener.listener.ListenerManager;
import com.gpluscb.challonge_listener.listener.ListenerManager.ManagerState;

import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Credentials;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.rest.retrofit.RetrofitRestClient;
import at.stefangeyer.challonge.serializer.gson.GsonSerializer;

// Extending from ListenerAdapter to execute own code when events fire
public class ChallongeListenerExample extends ListenerAdapter {
	public static void main(final String[] args) {
		// Allocating outside of try/catch scope so we may shut it down in case
		// of an exception
		ListenerManager manager = null;
		
		// Try-with-resource for preventing resource leaks
		try(final RetrofitRestClient client = new RetrofitRestClient(); final Scanner s = new Scanner(System.in)) {
			System.out.println("Starting...");
			
			// Setting credentials for accessing the Challonge api
			final Credentials credentials = new Credentials(System.getenv("ChallongeUsername"),
					System.getenv("ChallongeToken"));
			// Creating ChallongeExtension instance
			final ChallongeExtension challonge = new ChallongeExtension(credentials, new GsonSerializer(), client);
			
			// Creating ListenerManager instance with a cycle time of 5s
			manager = new ListenerManager(challonge, 5000);
			
			// Adding new instance of ChallongeListenerExample which extends
			// ListenerAdapter as a listener so the overridden methods get
			// executed when events fire
			manager.addListener(new ChallongeListenerExample(challonge));
			
			// Waiting until our ListenerManager is ready
			manager.awaitRunning();
			
			System.out.println("Everything running. Press enter to close.");
			// Waiting for the user to input enter
			s.nextLine();
			System.out.println("Stopping...");
			
			// Shutting down our ListenerManager so that its thread does not
			// keep our application alive indefinitely
			manager.shutdown();
			
			// Waiting until our ListenerManager is completely shut down
			manager.awaitState(ManagerState.SHUT_DOWN);
			
			System.out.println("Stopped.");
		} catch(final DataAccessException | IllegalStateException | InterruptedException e) {
			// *Something* went wrong *somewhere*. Trying to shut down our
			// manager only if it already exists.
			if(manager != null) {
				manager.shutdown();
			}
			
			e.printStackTrace();
		}
	}
	
	public ChallongeListenerExample(final ChallongeExtension challonge) throws DataAccessException {
		System.out.println("Subscribed to tournaments:");
		
		// Subscribing to all owned and co-owned tournaments
		for(final Tournament tournament : challonge.getTournaments()) {
			subscribeTo(tournament.getId().longValue());
			System.out.println(tournament.getFullChallongeUrl());
		}
	}
	
	@Override
	public void onGenericTournamentEvent(final GenericTournamentEvent event) {
		// Removes all time based events, as they are fired constantly
		// due to an issue in the api.
		if(!event.getClass().getName().endsWith("AtChangedEvent")) {
			System.out.println(event + " in Tournament " + event.getTournament().getFullChallongeUrl());
		}
	}
	
	@Override
	protected void onTournamentDescriptionChangedEvent(final TournamentDescriptionChangedEvent event) {
		System.out.println("The description of the tournament " + event.getTournament().getFullChallongeUrl()
				+ " was changed from");
		System.out.println(event.getPreviousDescription());
		System.out.println("to");
		System.out.println(event.getDescription());
	}
}
