package com.gpluscb.challonge_listener.events.tournament;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentUpdatedAtChangedEvent extends GenericTournamentChangedEvent {
	private final OffsetDateTime updatedAt;
	private final OffsetDateTime previousUpdatedAt;
	
	public TournamentUpdatedAtChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final OffsetDateTime updatedAt, final OffsetDateTime previousUpdatedAt) {
		super(tournament, previousTournament);
		this.updatedAt = updatedAt;
		this.previousUpdatedAt = previousUpdatedAt;
	}
	
	public OffsetDateTime getUpdatedAt() {
		return this.updatedAt;
	}
	
	public OffsetDateTime getPreviousUpdatedAt() {
		return this.previousUpdatedAt;
	}
}
