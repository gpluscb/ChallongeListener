package com.github.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchGroupIdChangedEvent extends GenericMatchChangedEvent {
	private final Long groupGroupId;
	private final Long previousGroupId;
	
	public MatchGroupIdChangedEvent(final Tournament tournament, final Tournament previousTournament, final Match match,
			final Match previousMatch, final Long groupGroupId, final Long previousGroupId) {
		super(tournament, previousTournament, match, previousMatch);
		this.groupGroupId = groupGroupId;
		this.previousGroupId = previousGroupId;
	}
	
	public Long getGroupId() {
		return this.groupGroupId;
	}
	
	public Long getPreviousGroupId() {
		return this.previousGroupId;
	}
}
