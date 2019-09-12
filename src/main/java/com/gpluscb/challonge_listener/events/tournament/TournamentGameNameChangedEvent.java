package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentGameNameChangedEvent extends GenericTournamentChangedEvent {
	private String gameName;
	private String previousGameName;
	
	public TournamentGameNameChangedEvent(Tournament tournament, Tournament previousTournament, String gameName,
			String previousGameName) {
		super(tournament, previousTournament);
		this.gameName = gameName;
		this.previousGameName = previousGameName;
	}
	
	public String getGameName() {
		return gameName;
	}
	
	public String getPreviousGameName() {
		return previousGameName;
	}
}
