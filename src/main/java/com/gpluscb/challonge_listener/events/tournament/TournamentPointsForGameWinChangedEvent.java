package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentPointsForGameWinChangedEvent extends GenericTournamentChangedEvent {
	private Float pointsForGameWin;
	private Float previousPointsForGameWin;
	
	public TournamentPointsForGameWinChangedEvent(Tournament tournament, Tournament previousTournament, Float pointsForGameWin, Float previousPointsForGameWin) {
		super(tournament, previousTournament);
		this.pointsForGameWin = pointsForGameWin;
		this.previousPointsForGameWin = previousPointsForGameWin;
	}
	
	public Float getPointsForGameWin() {
		return pointsForGameWin;
	}
	
	public Float getPreviousPointsForGameWin() {
		return previousPointsForGameWin;
	}
}
