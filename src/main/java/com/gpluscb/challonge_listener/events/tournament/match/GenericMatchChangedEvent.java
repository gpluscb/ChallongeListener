package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public abstract class GenericMatchChangedEvent extends GenericMatchEvent {
	private Match previousMatch;
	
	public GenericMatchChangedEvent(Tournament tournament, Tournament previousTournament, Match match, Match previousMatch) {
		super(tournament, previousTournament, match);
		this.previousMatch = previousMatch;
	}
	
	public Match getPreviousMatch() {
		return previousMatch;
	}
}
