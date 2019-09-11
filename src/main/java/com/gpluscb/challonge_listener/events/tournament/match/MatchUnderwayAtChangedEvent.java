package com.gpluscb.challonge_listener.events.tournament.match;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchUnderwayAtChangedEvent extends GenericMatchChangedEvent {
	private OffsetDateTime underwayAt;
	private OffsetDateTime previousUnderwayAt;
	
	public MatchUnderwayAtChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, OffsetDateTime underwayAt, OffsetDateTime previousUnderwayAt) {
		super(tournament, previousTournament, match, previousMatch);
		this.underwayAt = underwayAt;
		this.previousUnderwayAt = previousUnderwayAt;
	}
	
	public OffsetDateTime getUnderwayAt() {
		return underwayAt;
	}
	
	public OffsetDateTime getPreviousUnderwayAt() {
		return previousUnderwayAt;
	}
}
