package com.github.gpluscb.challonge_listener.events.tournament.match;

import com.github.gpluscb.challonge_listener.events.tournament.GenericTournamentChangedEvent;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public abstract class GenericMatchEvent extends GenericTournamentChangedEvent {
	private final Match match;
	
	public GenericMatchEvent(final Tournament tournament, final Tournament previousTournament, final Match match) {
		super(tournament, previousTournament);
		this.match = match;
	}
	
	/**
	 * The match primarily associated with this event.
	 * 
	 * @return The primary match of this event
	 */
	public Match getMatch() {
		return this.match;
	}
}
