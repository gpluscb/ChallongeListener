package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentRoundRobinPointsForMatchTieChangedEvent extends GenericTournamentChangedEvent {
	private Float roundRobinPointsForMatchTie;
	private Float previousRoundRobinPointsForMatchTie;
	
	public TournamentRoundRobinPointsForMatchTieChangedEvent(Tournament tournament, Tournament previousTournament, Float roundRobinPointsForMatchTie, Float previousRoundRobinPointsForMatchTie) {
		super(tournament, previousTournament);
		this.roundRobinPointsForMatchTie = roundRobinPointsForMatchTie;
		this.previousRoundRobinPointsForMatchTie = previousRoundRobinPointsForMatchTie;
	}
	
	public Float getRoundRobinPointsForMatchTie() {
		return roundRobinPointsForMatchTie;
	}
	
	public Float getPreviousRoundRobinPointsForMatchTie() {
		return previousRoundRobinPointsForMatchTie;
	}
}
