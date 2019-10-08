package com.github.gpluscb.challonge_listener.events.tournament.match;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchCompletedAtChangedEvent extends GenericMatchChangedEvent {
	private final OffsetDateTime completedAt;
	private final OffsetDateTime previousCompletedAt;
	
	public MatchCompletedAtChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final OffsetDateTime completedAt,
			final OffsetDateTime previousCompletedAt) {
		super(tournament, previousTournament, match, previousMatch);
		this.completedAt = completedAt;
		this.previousCompletedAt = previousCompletedAt;
	}
	
	public OffsetDateTime getCompletedAt() {
		return this.completedAt;
	}
	
	public OffsetDateTime getPreviousCompletedAt() {
		return this.previousCompletedAt;
	}
}
