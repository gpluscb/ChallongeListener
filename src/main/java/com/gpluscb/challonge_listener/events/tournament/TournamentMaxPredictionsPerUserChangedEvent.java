package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentMaxPredictionsPerUserChangedEvent extends GenericTournamentChangedEvent {
	private final Integer maxPredictionsPerUser;
	private final Integer previousMaxPredictionsPerUser;
	
	public TournamentMaxPredictionsPerUserChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Integer maxPredictionsPerUser, final Integer previousMaxPredictionsPerUser) {
		super(tournament, previousTournament);
		this.maxPredictionsPerUser = maxPredictionsPerUser;
		this.previousMaxPredictionsPerUser = previousMaxPredictionsPerUser;
	}
	
	public Integer getMaxPredictionsPerUser() {
		return this.maxPredictionsPerUser;
	}
	
	public Integer getPreviousMaxPredictionsPerUser() {
		return this.previousMaxPredictionsPerUser;
	}
}
