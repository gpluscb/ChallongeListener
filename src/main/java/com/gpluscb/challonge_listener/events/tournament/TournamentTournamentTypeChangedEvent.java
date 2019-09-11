package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.enumeration.TournamentType;

public class TournamentTournamentTypeChangedEvent extends GenericTournamentChangedEvent {
	private TournamentType tournamentType;
	private TournamentType previousTournamentType;
	
	public TournamentTournamentTypeChangedEvent(Tournament tournament, Tournament previousTournament, TournamentType tournamentType, TournamentType previousTournamentType) {
		super(tournament, previousTournament);
		this.tournamentType = tournamentType;
		this.previousTournamentType = previousTournamentType;
	}
	
	public TournamentType getTournamentType() {
		return tournamentType;
	}
	
	public TournamentType getPreviousTournamentType() {
		return previousTournamentType;
	}
}
