package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchIdChangedEvent extends GenericMatchChangedEvent {
	private Long id;
	private Long previousId;
	
	public MatchIdChangedEvent(Tournament tournament, Tournament previousTournament, Match match, Match previousMatch,
			Long id, Long previousId) {
		super(tournament, previousTournament, match, previousMatch);
		this.id = id;
		this.previousId = previousId;
	}
	
	public Long getId() {
		return id;
	}
	
	public Long getPreviousId() {
		return previousId;
	}
}
