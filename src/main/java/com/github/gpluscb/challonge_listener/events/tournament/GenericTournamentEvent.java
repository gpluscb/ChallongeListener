package com.github.gpluscb.challonge_listener.events.tournament;

import com.github.gpluscb.challonge_listener.events.GenericEvent;

import at.stefangeyer.challonge.model.Tournament;

public abstract class GenericTournamentEvent implements GenericEvent {
	private final Tournament tournament;
	
	public GenericTournamentEvent(final Tournament tournament) {
		this.tournament = tournament;
	}
	
	/**
	 * The tournament primarily associated with this event.
	 * 
	 * @return The primary tournament of this event
	 */
	public Tournament getTournament() {
		return this.tournament;
	}
}
