package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentPointsForGameWinChangedEvent extends GenericTournamentChangedEvent {
	private final Float pointsForGameWin;
	private final Float previousPointsForGameWin;
	
	public TournamentPointsForGameWinChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Float pointsForGameWin, final Float previousPointsForGameWin) {
		super(tournament, previousTournament);
		this.pointsForGameWin = pointsForGameWin;
		this.previousPointsForGameWin = previousPointsForGameWin;
	}
	
	public Float getPointsForGameWin() {
		return this.pointsForGameWin;
	}
	
	public Float getPreviousPointsForGameWin() {
		return this.previousPointsForGameWin;
	}
}
