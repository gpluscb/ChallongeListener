package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public abstract class GenericTournamentChangedEvent extends GenericTournamentEvent {
	private Tournament previousTournament;
	
	public GenericTournamentChangedEvent(Tournament tournament, Tournament previousTournament) {
		super(tournament);
		this.previousTournament = previousTournament;
	}
	
	public Tournament getPreviousTournament() {
		return previousTournament;
	}
}
