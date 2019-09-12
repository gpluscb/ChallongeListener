package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentMaxPredictionsPerUserChangedEvent extends GenericTournamentChangedEvent {
	private Integer maxPredictionsPerUser;
	private Integer previousMaxPredictionsPerUser;
	
	public TournamentMaxPredictionsPerUserChangedEvent(Tournament tournament, Tournament previousTournament,
			Integer maxPredictionsPerUser, Integer previousMaxPredictionsPerUser) {
		super(tournament, previousTournament);
		this.maxPredictionsPerUser = maxPredictionsPerUser;
		this.previousMaxPredictionsPerUser = previousMaxPredictionsPerUser;
	}
	
	public Integer getMaxPredictionsPerUser() {
		return maxPredictionsPerUser;
	}
	
	public Integer getPreviousMaxPredictionsPerUser() {
		return previousMaxPredictionsPerUser;
	}
}
