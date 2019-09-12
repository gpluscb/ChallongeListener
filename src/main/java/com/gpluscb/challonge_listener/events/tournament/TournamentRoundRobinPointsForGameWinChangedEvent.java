package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentRoundRobinPointsForGameWinChangedEvent extends GenericTournamentChangedEvent {
	private Float roundRobinPointsForGameWin;
	private Float previousRoundRobinPointsForGameWin;
	
	public TournamentRoundRobinPointsForGameWinChangedEvent(Tournament tournament, Tournament previousTournament,
			Float roundRobinPointsForGameWin, Float previousRoundRobinPointsForGameWin) {
		super(tournament, previousTournament);
		this.roundRobinPointsForGameWin = roundRobinPointsForGameWin;
		this.previousRoundRobinPointsForGameWin = previousRoundRobinPointsForGameWin;
	}
	
	public Float getRoundRobinPointsForGameWin() {
		return roundRobinPointsForGameWin;
	}
	
	public Float getPreviousRoundRobinPointsForGameWin() {
		return previousRoundRobinPointsForGameWin;
	}
}
