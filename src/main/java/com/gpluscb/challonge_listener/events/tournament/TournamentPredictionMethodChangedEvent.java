package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentPredictionMethodChangedEvent extends GenericTournamentChangedEvent {
	private final Integer predictionMethod;
	private final Integer previousPredictionMethod;
	
	public TournamentPredictionMethodChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Integer predictionMethod, final Integer previousPredictionMethod) {
		super(tournament, previousTournament);
		this.predictionMethod = predictionMethod;
		this.previousPredictionMethod = previousPredictionMethod;
	}
	
	public Integer getPredictionMethod() {
		return this.predictionMethod;
	}
	
	public Integer getPreviousPredictionMethod() {
		return this.previousPredictionMethod;
	}
}
