package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchGroupIdChangedEvent extends GenericMatchChangedEvent {
	private Long groupGroupId;
	private Long previousGroupId;
	
	public MatchGroupIdChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, Long groupGroupId, Long previousGroupId) {
		super(tournament, previousTournament, match, previousMatch);
		this.groupGroupId = groupGroupId;
		this.previousGroupId = previousGroupId;
	}
	
	public Long getGroupId() {
		return groupGroupId;
	}
	
	public Long getPreviousGroupId() {
		return previousGroupId;
	}
}
