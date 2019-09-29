package com.gpluscb.challonge_listener.events.tournament.match;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchUnderwayAtChangedEvent extends GenericMatchChangedEvent {
	private final OffsetDateTime underwayAt;
	private final OffsetDateTime previousUnderwayAt;
	
	public MatchUnderwayAtChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final OffsetDateTime underwayAt,
			final OffsetDateTime previousUnderwayAt) {
		super(tournament, previousTournament, match, previousMatch);
		this.underwayAt = underwayAt;
		this.previousUnderwayAt = previousUnderwayAt;
	}
	
	public OffsetDateTime getUnderwayAt() {
		return this.underwayAt;
	}
	
	public OffsetDateTime getPreviousUnderwayAt() {
		return this.previousUnderwayAt;
	}
}
