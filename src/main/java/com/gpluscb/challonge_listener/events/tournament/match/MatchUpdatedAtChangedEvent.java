package com.gpluscb.challonge_listener.events.tournament.match;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchUpdatedAtChangedEvent extends GenericMatchChangedEvent {
	private final OffsetDateTime updatedAt;
	private final OffsetDateTime previousUpdatedAt;
	
	public MatchUpdatedAtChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final OffsetDateTime updatedAt,
			final OffsetDateTime previousUpdatedAt) {
		super(tournament, previousTournament, match, previousMatch);
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
