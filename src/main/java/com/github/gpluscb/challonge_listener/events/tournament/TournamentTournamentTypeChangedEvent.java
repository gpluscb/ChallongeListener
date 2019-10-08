package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.enumeration.TournamentType;

public class TournamentTournamentTypeChangedEvent extends GenericTournamentChangedEvent {
	private final TournamentType tournamentType;
	private final TournamentType previousTournamentType;
	
	public TournamentTournamentTypeChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final TournamentType tournamentType, final TournamentType previousTournamentType) {
		super(tournament, previousTournament);
		this.tournamentType = tournamentType;
		this.previousTournamentType = previousTournamentType;
	}
	
	public TournamentType getTournamentType() {
		return this.tournamentType;
	}
	
	public TournamentType getPreviousTournamentType() {
		return this.previousTournamentType;
	}
}
