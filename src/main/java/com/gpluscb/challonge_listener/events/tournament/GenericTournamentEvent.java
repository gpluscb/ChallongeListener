package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;
import com.gpluscb.challonge_listener.events.GenericEvent;

public abstract class GenericTournamentEvent implements GenericEvent {
	private Tournament tournament;
	
	public GenericTournamentEvent(Tournament tournament) {
		this.tournament = tournament;
	}
	
	public Tournament getTournament() {
		return tournament;
	}
}
