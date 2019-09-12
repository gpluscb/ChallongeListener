package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentPointsForMatchTieChangedEvent extends GenericTournamentChangedEvent {
	private Float pointsForMatchTie;
	private Float previousPointsForMatchTie;
	
	public TournamentPointsForMatchTieChangedEvent(Tournament tournament, Tournament previousTournament,
			Float pointsForMatchTie, Float previousPointsForMatchTie) {
		super(tournament, previousTournament);
		this.pointsForMatchTie = pointsForMatchTie;
		this.previousPointsForMatchTie = previousPointsForMatchTie;
	}
	
	public Float getPointsForMatchTie() {
		return pointsForMatchTie;
	}
	
	public Float getPreviousPointsForMatchTie() {
		return previousPointsForMatchTie;
	}
}
