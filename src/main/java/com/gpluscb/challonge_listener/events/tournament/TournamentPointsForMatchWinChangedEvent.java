package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentPointsForMatchWinChangedEvent extends GenericTournamentChangedEvent {
	private Float pointsForMatchWin;
	private Float previousPointsForMatchWin;
	
	public TournamentPointsForMatchWinChangedEvent(Tournament tournament, Tournament previousTournament,
			Float pointsForMatchWin, Float previousPointsForMatchWin) {
		super(tournament, previousTournament);
		this.pointsForMatchWin = pointsForMatchWin;
		this.previousPointsForMatchWin = previousPointsForMatchWin;
	}
	
	public Float getPointsForMatchWin() {
		return pointsForMatchWin;
	}
	
	public Float getPreviousPointsForMatchWin() {
		return previousPointsForMatchWin;
	}
}
