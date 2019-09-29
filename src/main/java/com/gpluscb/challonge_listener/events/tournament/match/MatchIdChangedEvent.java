package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchIdChangedEvent extends GenericMatchChangedEvent {
	private final Long id;
	private final Long previousId;
	
	public MatchIdChangedEvent(final Tournament tournament, final Tournament previousTournament, final Match match,
			final Match previousMatch, final Long id, final Long previousId) {
		super(tournament, previousTournament, match, previousMatch);
		this.id = id;
		this.previousId = previousId;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public Long getPreviousId() {
		return this.previousId;
	}
}
