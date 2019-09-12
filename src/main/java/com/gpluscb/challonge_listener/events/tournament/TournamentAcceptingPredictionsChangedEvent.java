package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentAcceptingPredictionsChangedEvent extends GenericTournamentChangedEvent {
	private Boolean acceptingPredictions;
	private Boolean previousAcceptingPredictions;
	
	public TournamentAcceptingPredictionsChangedEvent(Tournament tournament, Tournament previousTournament,
			Boolean acceptingPredictions, Boolean previousAcceptingPredictions) {
		super(tournament, previousTournament);
		this.acceptingPredictions = acceptingPredictions;
		this.previousAcceptingPredictions = previousAcceptingPredictions;
	}
	
	public Boolean getAcceptingPredictions() {
		return acceptingPredictions;
	}
	
	public Boolean getPreviousAcceptingPredictions() {
		return previousAcceptingPredictions;
	}
}
