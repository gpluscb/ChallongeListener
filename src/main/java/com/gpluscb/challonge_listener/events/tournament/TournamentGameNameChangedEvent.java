package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentGameNameChangedEvent extends GenericTournamentChangedEvent {
	private final String gameName;
	private final String previousGameName;
	
	public TournamentGameNameChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final String gameName, final String previousGameName) {
		super(tournament, previousTournament);
		this.gameName = gameName;
		this.previousGameName = previousGameName;
	}
	
	public String getGameName() {
		return this.gameName;
	}
	
	public String getPreviousGameName() {
		return this.previousGameName;
	}
}
