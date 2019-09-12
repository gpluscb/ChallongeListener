package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentPredictionMethodChangedEvent extends GenericTournamentChangedEvent {
	private Integer predictionMethod;
	private Integer previousPredictionMethod;
	
	public TournamentPredictionMethodChangedEvent(Tournament tournament, Tournament previousTournament,
			Integer predictionMethod, Integer previousPredictionMethod) {
		super(tournament, previousTournament);
		this.predictionMethod = predictionMethod;
		this.previousPredictionMethod = previousPredictionMethod;
	}
	
	public Integer getPredictionMethod() {
		return predictionMethod;
	}
	
	public Integer getPreviousPredictionMethod() {
		return previousPredictionMethod;
	}
}
