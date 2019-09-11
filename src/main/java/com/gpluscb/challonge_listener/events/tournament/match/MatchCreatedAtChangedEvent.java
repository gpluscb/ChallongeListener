package com.gpluscb.challonge_listener.events.tournament.match;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchCreatedAtChangedEvent extends GenericMatchChangedEvent {
	private OffsetDateTime createdAt;
	private OffsetDateTime previousCreatedAt;
	
	public MatchCreatedAtChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, OffsetDateTime createdAt, OffsetDateTime previousCreatedAt) {
		super(tournament, previousTournament, match, previousMatch);
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
