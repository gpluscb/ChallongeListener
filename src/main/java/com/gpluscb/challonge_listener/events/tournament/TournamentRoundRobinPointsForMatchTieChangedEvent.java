package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentRoundRobinPointsForMatchTieChangedEvent extends GenericTournamentChangedEvent {
	private final Float roundRobinPointsForMatchTie;
	private final Float previousRoundRobinPointsForMatchTie;
	
	public TournamentRoundRobinPointsForMatchTieChangedEvent(final Tournament tournament,
			final Tournament previousTournament, final Float roundRobinPointsForMatchTie,
			final Float previousRoundRobinPointsForMatchTie) {
		super(tournament, previousTournament);
		this.roundRobinPointsForMatchTie = roundRobinPointsForMatchTie;
		this.previousRoundRobinPointsForMatchTie = previousRoundRobinPointsForMatchTie;
	}
	
	public Float getRoundRobinPointsForMatchTie() {
		return this.roundRobinPointsForMatchTie;
	}
	
	public Float getPreviousRoundRobinPointsForMatchTie() {
		return this.previousRoundRobinPointsForMatchTie;
	}
}
