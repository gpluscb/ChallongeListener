package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentPointsForMatchTieChangedEvent extends GenericTournamentChangedEvent {
	private final Float pointsForMatchTie;
	private final Float previousPointsForMatchTie;
	
	public TournamentPointsForMatchTieChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Float pointsForMatchTie, final Float previousPointsForMatchTie) {
		super(tournament, previousTournament);
		this.pointsForMatchTie = pointsForMatchTie;
		this.previousPointsForMatchTie = previousPointsForMatchTie;
	}
	
	public Float getPointsForMatchTie() {
		return this.pointsForMatchTie;
	}
	
	public Float getPreviousPointsForMatchTie() {
		return this.previousPointsForMatchTie;
	}
}
