package com.gpluscb.challonge_listener.events.tournament;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentCreatedAtChangedEvent extends GenericTournamentChangedEvent {
	private final OffsetDateTime createdAt;
	private final OffsetDateTime previousCreatedAt;
	
	public TournamentCreatedAtChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final OffsetDateTime createdAt, final OffsetDateTime previousCreatedAt) {
		super(tournament, previousTournament);
		this.createdAt = createdAt;
		this.previousCreatedAt = previousCreatedAt;
	}
	
	public OffsetDateTime getCreatedAt() {
		return this.createdAt;
	}
	
	public OffsetDateTime getPreviousCreatedAt() {
		return this.previousCreatedAt;
	}
}
