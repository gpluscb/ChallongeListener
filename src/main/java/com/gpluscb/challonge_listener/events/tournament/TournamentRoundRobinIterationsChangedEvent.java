package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentRoundRobinIterationsChangedEvent extends GenericTournamentChangedEvent {
	private final Integer roundRobinIterations;
	private final Integer previousRoundRobinIterations;
	
	public TournamentRoundRobinIterationsChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Integer roundRobinIterations, final Integer previousRoundRobinIterations) {
		super(tournament, previousTournament);
		this.roundRobinIterations = roundRobinIterations;
		this.previousRoundRobinIterations = previousRoundRobinIterations;
	}
	
	public Integer getRoundRobinIterations() {
		return this.roundRobinIterations;
	}
	
	public Integer getPreviousRoundRobinIterations() {
		return this.previousRoundRobinIterations;
	}
}
