package com.gpluscb.challonge_listener;

import java.util.Scanner;

import com.google.gson.GsonBuilder;
import com.gpluscb.challonge_listener.events.tournament.GenericTournamentEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentDescriptionChangedEvent;
import com.gpluscb.challonge_listener.listener.ListenerAdapter;
import com.gpluscb.challonge_listener.listener.ListenerManager;
import com.gpluscb.challonge_listener.listener.ListenerManager.ManagerState;

import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Credentials;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.rest.retrofit.RetrofitRestClient;
import at.stefangeyer.challonge.serializer.Serializer;
import at.stefangeyer.challonge.serializer.gson.GsonSerializer;

public class ChallongeListenerDemonstration extends ListenerAdapter {
	public static void main(final String[] args) {
		try(final RetrofitRestClient client = new RetrofitRestClient(); final Scanner s = new Scanner(System.in)) {
			System.out.println("Starting...");
			
			final GsonBuilder builder = new GsonBuilder();
			builder.serializeNulls().setPrettyPrinting();
			final Serializer serializer = new GsonSerializer(builder);
			final Credentials credentials = new Credentials(System.getenv("ChallongeUsername"),
					System.getenv("ChallongeToken"));
			final ChallongeExtension challonge = new ChallongeExtension(credentials, serializer, client);
			
			final ListenerManager manager = new ListenerManager(challonge, 5000);
			
			manager.addListener(new ChallongeListenerDemonstration(challonge));
			
			manager.awaitRunning();
			
			System.out.println("Everything running. Press enter to close.");
			s.nextLine();
			System.out.println("Stopping...");
			
			manager.shutdown();
			client.close();
			s.close();
			
			manager.awaitState(ManagerState.SHUT_DOWN);
			
			System.out.println("Stopped.");
		} catch(final DataAccessException | IllegalStateException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public ChallongeListenerDemonstration(final ChallongeExtension challonge) throws DataAccessException {
		System.out.println("Subscribed to tournaments:");
		for(final Tournament tournament : challonge.getTournaments()) {
			subscribeTo(tournament.getId().longValue());
			System.out.println(tournament.getFullChallongeUrl());
		}
	}
	
	@Override
	public void onGenericTournamentEvent(final GenericTournamentEvent event) {
		// Removes all time based events, as they are fired constantly
		// for some reason.
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
