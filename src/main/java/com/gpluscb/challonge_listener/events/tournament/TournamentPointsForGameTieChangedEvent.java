package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentPointsForGameTieChangedEvent extends GenericTournamentChangedEvent {
	private final Float pointsForGameTie;
	private final Float previousPointsForGameTie;
	
	public TournamentPointsForGameTieChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Float pointsForGameTie, final Float previousPointsForGameTie) {
		super(tournament, previousTournament);
		this.pointsForGameTie = pointsForGameTie;
		this.previousPointsForGameTie = previousPointsForGameTie;
	}
	
	public Float getPointsForGameTie() {
		return this.pointsForGameTie;
	}
	
	public Float getPreviousPointsForGameTie() {
		return this.previousPointsForGameTie;
	}
}
