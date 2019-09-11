package com.gpluscb.challonge_listener.events.tournament;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentUpdatedAtChangedEvent extends GenericTournamentChangedEvent {
	private OffsetDateTime updatedAt;
	private OffsetDateTime previousUpdatedAt;
	
	public TournamentUpdatedAtChangedEvent(Tournament tournament, Tournament previousTournament, OffsetDateTime updatedAt, OffsetDateTime previousUpdatedAt) {
		super(tournament, previousTournament);
		this.updatedAt = updatedAt;
		this.previousUpdatedAt = previousUpdatedAt;
	}
	
	public OffsetDateTime getUpdatedAt() {
		return updatedAt;
	}
	
	public OffsetDateTime getPreviousUpdatedAt() {
		return previousUpdatedAt;
	}
}
