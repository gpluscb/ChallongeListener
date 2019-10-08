package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentPointsForByeChangedEvent extends GenericTournamentChangedEvent {
	private final Float pointsForBye;
	private final Float previousPointsForBye;
	
	public TournamentPointsForByeChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Float pointsForBye, final Float previousPointsForBye) {
		super(tournament, previousTournament);
		this.pointsForBye = pointsForBye;
		this.previousPointsForBye = previousPointsForBye;
	}
	
	public Float getPointsForBye() {
		return this.pointsForBye;
	}
	
	public Float getPreviousPointsForBye() {
		return this.previousPointsForBye;
	}
}
