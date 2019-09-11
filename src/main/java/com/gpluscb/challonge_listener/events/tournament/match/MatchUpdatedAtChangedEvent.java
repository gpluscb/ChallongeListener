package com.gpluscb.challonge_listener.events.tournament.match;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchUpdatedAtChangedEvent extends GenericMatchChangedEvent {
	private OffsetDateTime updatedAt;
	private OffsetDateTime previousUpdatedAt;
	
	public MatchUpdatedAtChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, OffsetDateTime updatedAt, OffsetDateTime previousUpdatedAt) {
		super(tournament, previousTournament, match, previousMatch);
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
