package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentPointsForGameTieChangedEvent extends GenericTournamentChangedEvent {
	private Float pointsForGameTie;
	private Float previousPointsForGameTie;
	
	public TournamentPointsForGameTieChangedEvent(Tournament tournament, Tournament previousTournament,
			Float pointsForGameTie, Float previousPointsForGameTie) {
		super(tournament, previousTournament);
		this.pointsForGameTie = pointsForGameTie;
		this.previousPointsForGameTie = previousPointsForGameTie;
	}
	
	public Float getPointsForGameTie() {
		return pointsForGameTie;
	}
	
	public Float getPreviousPointsForGameTie() {
		return previousPointsForGameTie;
	}
}
