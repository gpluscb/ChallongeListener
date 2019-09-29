package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentGameIdChangedEvent extends GenericTournamentChangedEvent {
	private final Long gameGameId;
	private final Long previousGameId;
	
	public TournamentGameIdChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Long gameGameId, final Long previousGameId) {
		super(tournament, previousTournament);
		this.gameGameId = gameGameId;
		this.previousGameId = previousGameId;
	}
	
	public Long getGameId() {
		return this.gameGameId;
	}
	
	public Long getPreviousGameId() {
		return this.previousGameId;
	}
}
