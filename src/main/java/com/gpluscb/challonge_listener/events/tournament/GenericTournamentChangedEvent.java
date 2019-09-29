package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public abstract class GenericTournamentChangedEvent extends GenericTournamentEvent {
	private final Tournament previousTournament;
	
	public GenericTournamentChangedEvent(final Tournament tournament, final Tournament previousTournament) {
		super(tournament);
		this.previousTournament = previousTournament;
	}
	
	/**
	 * The tournament before it changed.
	 * 
	 * @return the previous tournament
	 */
	public Tournament getPreviousTournament() {
		return this.previousTournament;
	}
}
