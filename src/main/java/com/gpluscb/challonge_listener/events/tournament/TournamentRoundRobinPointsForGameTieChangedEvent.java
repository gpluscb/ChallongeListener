package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentRoundRobinPointsForGameTieChangedEvent extends GenericTournamentChangedEvent {
	private final Float roundRobinPointsForGameTie;
	private final Float previousRoundRobinPointsForGameTie;
	
	public TournamentRoundRobinPointsForGameTieChangedEvent(final Tournament tournament,
			final Tournament previousTournament, final Float roundRobinPointsForGameTie,
			final Float previousRoundRobinPointsForGameTie) {
		super(tournament, previousTournament);
		this.roundRobinPointsForGameTie = roundRobinPointsForGameTie;
		this.previousRoundRobinPointsForGameTie = previousRoundRobinPointsForGameTie;
	}
	
	public Float getRoundRobinPointsForGameTie() {
		return this.roundRobinPointsForGameTie;
	}
	
	public Float getPreviousRoundRobinPointsForGameTie() {
		return this.previousRoundRobinPointsForGameTie;
	}
}
