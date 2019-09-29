package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentPointsForMatchWinChangedEvent extends GenericTournamentChangedEvent {
	private final Float pointsForMatchWin;
	private final Float previousPointsForMatchWin;
	
	public TournamentPointsForMatchWinChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Float pointsForMatchWin, final Float previousPointsForMatchWin) {
		super(tournament, previousTournament);
		this.pointsForMatchWin = pointsForMatchWin;
		this.previousPointsForMatchWin = previousPointsForMatchWin;
	}
	
	public Float getPointsForMatchWin() {
		return this.pointsForMatchWin;
	}
	
	public Float getPreviousPointsForMatchWin() {
		return this.previousPointsForMatchWin;
	}
}
