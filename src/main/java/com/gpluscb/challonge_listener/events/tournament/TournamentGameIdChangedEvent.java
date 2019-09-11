package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentGameIdChangedEvent extends GenericTournamentChangedEvent {
	private Long gameGameId;
	private Long previousGameId;
	
	public TournamentGameIdChangedEvent(Tournament tournament, Tournament previousTournament, Long gameGameId, Long previousGameId) {
		super(tournament, previousTournament);
		this.gameGameId = gameGameId;
		this.previousGameId = previousGameId;
	}
	
	public Long getGameId() {
		return gameGameId;
	}
	
	public Long getPreviousGameId() {
		return previousGameId;
	}
}
