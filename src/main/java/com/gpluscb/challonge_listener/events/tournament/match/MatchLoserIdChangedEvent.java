package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchLoserIdChangedEvent extends GenericMatchChangedEvent {
	private Long loserLoserId;
	private Long previousLoserId;
	
	public MatchLoserIdChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, Long loserLoserId, Long previousLoserId) {
		super(tournament, previousTournament, match, previousMatch);
		this.loserLoserId = loserLoserId;
		this.previousLoserId = previousLoserId;
	}
	
	public Long getLoserId() {
		return loserLoserId;
	}
	
	public Long getPreviousLoserId() {
		return previousLoserId;
	}
}
