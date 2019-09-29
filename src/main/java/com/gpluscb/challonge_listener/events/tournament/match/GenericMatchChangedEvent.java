package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public abstract class GenericMatchChangedEvent extends GenericMatchEvent {
	private final Match previousMatch;
	
	public GenericMatchChangedEvent(final Tournament tournament, final Tournament previousTournament, final Match match,
			final Match previousMatch) {
		super(tournament, previousTournament, match);
		this.previousMatch = previousMatch;
	}
	
	/**
	 * The match before it changed.
	 * 
	 * @return the previous match
	 */
	public Match getPreviousMatch() {
		return this.previousMatch;
	}
}
