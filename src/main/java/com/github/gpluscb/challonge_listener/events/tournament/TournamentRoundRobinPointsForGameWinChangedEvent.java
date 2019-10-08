package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentRoundRobinPointsForGameWinChangedEvent extends GenericTournamentChangedEvent {
	private final Float roundRobinPointsForGameWin;
	private final Float previousRoundRobinPointsForGameWin;
	
	public TournamentRoundRobinPointsForGameWinChangedEvent(final Tournament tournament,
			final Tournament previousTournament, final Float roundRobinPointsForGameWin,
			final Float previousRoundRobinPointsForGameWin) {
		super(tournament, previousTournament);
		this.roundRobinPointsForGameWin = roundRobinPointsForGameWin;
		this.previousRoundRobinPointsForGameWin = previousRoundRobinPointsForGameWin;
	}
	
	public Float getRoundRobinPointsForGameWin() {
		return this.roundRobinPointsForGameWin;
	}
	
	public Float getPreviousRoundRobinPointsForGameWin() {
		return this.previousRoundRobinPointsForGameWin;
	}
}
