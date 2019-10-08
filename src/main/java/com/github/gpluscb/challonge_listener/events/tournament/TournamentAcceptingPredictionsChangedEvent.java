package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentAcceptingPredictionsChangedEvent extends GenericTournamentChangedEvent {
	private final Boolean acceptingPredictions;
	private final Boolean previousAcceptingPredictions;
	
	public TournamentAcceptingPredictionsChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Boolean acceptingPredictions, final Boolean previousAcceptingPredictions) {
		super(tournament, previousTournament);
		this.acceptingPredictions = acceptingPredictions;
		this.previousAcceptingPredictions = previousAcceptingPredictions;
	}
	
	public Boolean getAcceptingPredictions() {
		return this.acceptingPredictions;
	}
	
	public Boolean getPreviousAcceptingPredictions() {
		return this.previousAcceptingPredictions;
	}
}
