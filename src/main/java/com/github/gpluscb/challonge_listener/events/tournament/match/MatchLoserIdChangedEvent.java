package com.github.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchLoserIdChangedEvent extends GenericMatchChangedEvent {
	private final Long loserLoserId;
	private final Long previousLoserId;
	
	public MatchLoserIdChangedEvent(final Tournament tournament, final Tournament previousTournament, final Match match,
			final Match previousMatch, final Long loserLoserId, final Long previousLoserId) {
		super(tournament, previousTournament, match, previousMatch);
		this.loserLoserId = loserLoserId;
		this.previousLoserId = previousLoserId;
	}
	
	public Long getLoserId() {
		return this.loserLoserId;
	}
	
	public Long getPreviousLoserId() {
		return this.previousLoserId;
	}
}
