package com.gpluscb.challonge_listener;

import java.util.Scanner;

import com.google.gson.GsonBuilder;
import com.gpluscb.challonge_listener.events.tournament.GenericTournamentEvent;
import com.gpluscb.challonge_listener.listener.ListenerManager;

import at.stefangeyer.challonge.model.Credentials;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.rest.retrofit.RetrofitRestClient;
import at.stefangeyer.challonge.serializer.Serializer;
import at.stefangeyer.challonge.serializer.gson.GsonSerializer;

public class ChallongeListenerDemonstration {
	public static void main(String[] args) {
		try {
			System.out.println("Starting...");
			
			GsonBuilder builder = new GsonBuilder();
			builder.serializeNulls().setPrettyPrinting();
			Serializer serializer = new GsonSerializer(builder);
			RetrofitRestClient client = new RetrofitRestClient();
			Credentials credentials = new Credentials(System.getenv("ChallongeUsername"),
					System.getenv("ChallongeToken"));
			ChallongeExtension challonge = new ChallongeExtension(credentials, serializer, client);
			
			ListenerManager manager = new ListenerManager(challonge, 5000);
			
			manager.addListener(event -> {
				// Removes all time based events, as they are fired constantly
				// for some reason.
				if(!event.getClass().getName().contains("AtChangedEvent")) {
					System.out.println(event);
					if(event instanceof GenericTournamentEvent) {
						Tournament tournament = ((GenericTournamentEvent) event).getTournament();
						System.out.println("In Tournament " + tournament.getFullChallongeUrl());
					}
				}
			});
			
			Scanner s = new Scanner(System.in);
			
			System.out.println("Everything running. Press enter to close.");
			s.nextLine();
			System.out.println("Stopping...");
			
			manager.shutdown();
			client.close();
			s.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
