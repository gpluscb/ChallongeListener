package com.gpluscb.challonge_listener.events.tournament;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentCreatedAtChangedEvent extends GenericTournamentChangedEvent {
	private OffsetDateTime createdAt;
	private OffsetDateTime previousCreatedAt;
	
	public TournamentCreatedAtChangedEvent(Tournament tournament, Tournament previousTournament, OffsetDateTime createdAt, OffsetDateTime previousCreatedAt) {
		super(tournament, previousTournament);
		this.createdAt = createdAt;
		this.previousCreatedAt = previousCreatedAt;
	}
	
	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}
	
	public OffsetDateTime getPreviousCreatedAt() {
		return previousCreatedAt;
	}
}
