package com.gpluscb.challonge_listener.events.tournament.match;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchStartedAtChangedEvent extends GenericMatchChangedEvent {
	private OffsetDateTime startedAt;
	private OffsetDateTime previousStartedAt;
	
	public MatchStartedAtChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, OffsetDateTime startedAt, OffsetDateTime previousStartedAt) {
		super(tournament, previousTournament, match, previousMatch);
		this.startedAt = startedAt;
		this.previousStartedAt = previousStartedAt;
	}
	
	public OffsetDateTime getStartedAt() {
		return startedAt;
	}
	
	public OffsetDateTime getPreviousStartedAt() {
		return previousStartedAt;
	}
}
