package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchTournamentIdChangedEvent extends GenericMatchChangedEvent {
	private Long tournamentTournamentId;
	private Long previousTournamentId;
	
	public MatchTournamentIdChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, Long tournamentTournamentId, Long previousTournamentId) {
		super(tournament, previousTournament, match, previousMatch);
		this.tournamentTournamentId = tournamentTournamentId;
		this.previousTournamentId = previousTournamentId;
	}
	
	public Long getTournamentId() {
		return tournamentTournamentId;
	}
	
	public Long getPreviousTournamentId() {
		return previousTournamentId;
	}
}
