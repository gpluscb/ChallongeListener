package com.gpluscb.challonge_listener.events.tournament.match;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchCreatedAtChangedEvent extends GenericMatchChangedEvent {
	private final OffsetDateTime createdAt;
	private final OffsetDateTime previousCreatedAt;
	
	public MatchCreatedAtChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final OffsetDateTime createdAt,
			final OffsetDateTime previousCreatedAt) {
		super(tournament, previousTournament, match, previousMatch);
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
