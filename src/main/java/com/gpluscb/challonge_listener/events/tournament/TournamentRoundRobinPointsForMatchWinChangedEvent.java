package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentRoundRobinPointsForMatchWinChangedEvent extends GenericTournamentChangedEvent {
	private Float roundRobinPointsForMatchWin;
	private Float previousRoundRobinPointsForMatchWin;
	
	public TournamentRoundRobinPointsForMatchWinChangedEvent(Tournament tournament, Tournament previousTournament,
			Float roundRobinPointsForMatchWin, Float previousRoundRobinPointsForMatchWin) {
		super(tournament, previousTournament);
		this.roundRobinPointsForMatchWin = roundRobinPointsForMatchWin;
		this.previousRoundRobinPointsForMatchWin = previousRoundRobinPointsForMatchWin;
	}
	
	public Float getRoundRobinPointsForMatchWin() {
		return roundRobinPointsForMatchWin;
	}
	
	public Float getPreviousRoundRobinPointsForMatchWin() {
		return previousRoundRobinPointsForMatchWin;
	}
}
