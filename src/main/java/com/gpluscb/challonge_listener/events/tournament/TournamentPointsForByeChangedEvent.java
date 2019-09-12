package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentPointsForByeChangedEvent extends GenericTournamentChangedEvent {
	private Float pointsForBye;
	private Float previousPointsForBye;
	
	public TournamentPointsForByeChangedEvent(Tournament tournament, Tournament previousTournament, Float pointsForBye,
			Float previousPointsForBye) {
		super(tournament, previousTournament);
		this.pointsForBye = pointsForBye;
		this.previousPointsForBye = previousPointsForBye;
	}
	
	public Float getPointsForBye() {
		return pointsForBye;
	}
	
	public Float getPreviousPointsForBye() {
		return previousPointsForBye;
	}
}
