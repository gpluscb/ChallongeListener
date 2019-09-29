package com.gpluscb.challonge_listener.events.tournament.match;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchStartedAtChangedEvent extends GenericMatchChangedEvent {
	private final OffsetDateTime startedAt;
	private final OffsetDateTime previousStartedAt;
	
	public MatchStartedAtChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final OffsetDateTime startedAt,
			final OffsetDateTime previousStartedAt) {
		super(tournament, previousTournament, match, previousMatch);
		this.startedAt = startedAt;
		this.previousStartedAt = previousStartedAt;
	}
	
	public OffsetDateTime getStartedAt() {
		return this.startedAt;
	}
	
	public OffsetDateTime getPreviousStartedAt() {
		return this.previousStartedAt;
	}
}
