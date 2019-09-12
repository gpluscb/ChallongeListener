package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentRoundRobinPointsForGameTieChangedEvent extends GenericTournamentChangedEvent {
	private Float roundRobinPointsForGameTie;
	private Float previousRoundRobinPointsForGameTie;
	
	public TournamentRoundRobinPointsForGameTieChangedEvent(Tournament tournament, Tournament previousTournament,
			Float roundRobinPointsForGameTie, Float previousRoundRobinPointsForGameTie) {
		super(tournament, previousTournament);
		this.roundRobinPointsForGameTie = roundRobinPointsForGameTie;
		this.previousRoundRobinPointsForGameTie = previousRoundRobinPointsForGameTie;
	}
	
	public Float getRoundRobinPointsForGameTie() {
		return roundRobinPointsForGameTie;
	}
	
	public Float getPreviousRoundRobinPointsForGameTie() {
		return previousRoundRobinPointsForGameTie;
	}
}
