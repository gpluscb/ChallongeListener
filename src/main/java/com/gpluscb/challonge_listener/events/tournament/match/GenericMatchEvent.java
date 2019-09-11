package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;
import com.gpluscb.challonge_listener.events.tournament.GenericTournamentChangedEvent;

public abstract class GenericMatchEvent extends GenericTournamentChangedEvent {
	private Match match;
	
	public GenericMatchEvent(Tournament tournament, Tournament previousTournament, Match match) {
		super(tournament, previousTournament);
		this.match = match;
	}
	
	public Match getMatch() {
		return match;
	}
}
