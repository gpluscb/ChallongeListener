package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchTournamentIdChangedEvent extends GenericMatchChangedEvent {
	private final Long tournamentTournamentId;
	private final Long previousTournamentId;
	
	public MatchTournamentIdChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final Long tournamentTournamentId,
			final Long previousTournamentId) {
		super(tournament, previousTournament, match, previousMatch);
		this.tournamentTournamentId = tournamentTournamentId;
		this.previousTournamentId = previousTournamentId;
	}
	
	public Long getTournamentId() {
		return this.tournamentTournamentId;
	}
	
	public Long getPreviousTournamentId() {
		return this.previousTournamentId;
	}
}
