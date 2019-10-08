package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentRoundRobinPointsForMatchWinChangedEvent extends GenericTournamentChangedEvent {
	private final Float roundRobinPointsForMatchWin;
	private final Float previousRoundRobinPointsForMatchWin;
	
	public TournamentRoundRobinPointsForMatchWinChangedEvent(final Tournament tournament,
			final Tournament previousTournament, final Float roundRobinPointsForMatchWin,
			final Float previousRoundRobinPointsForMatchWin) {
		super(tournament, previousTournament);
		this.roundRobinPointsForMatchWin = roundRobinPointsForMatchWin;
		this.previousRoundRobinPointsForMatchWin = previousRoundRobinPointsForMatchWin;
	}
	
	public Float getRoundRobinPointsForMatchWin() {
		return this.roundRobinPointsForMatchWin;
	}
	
	public Float getPreviousRoundRobinPointsForMatchWin() {
		return this.previousRoundRobinPointsForMatchWin;
	}
}
