package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchForfeitedChangedEvent extends GenericMatchChangedEvent {
	private final Boolean forfeited;
	private final Boolean previousForfeited;
	
	public MatchForfeitedChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final Boolean forfeited, final Boolean previousForfeited) {
		super(tournament, previousTournament, match, previousMatch);
		this.forfeited = forfeited;
		this.previousForfeited = previousForfeited;
	}
	
	public Boolean getForfeited() {
		return this.forfeited;
	}
	
	public Boolean getPreviousForfeited() {
		return this.previousForfeited;
	}
}
