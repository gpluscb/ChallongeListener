package com.gpluscb.challonge_listener.events.tournament;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentPredictionsOpenedAtChangedEvent extends GenericTournamentChangedEvent {
	private OffsetDateTime predictionsOpenedAt;
	private OffsetDateTime previousPredictionsOpenedAt;
	
	public TournamentPredictionsOpenedAtChangedEvent(Tournament tournament, Tournament previousTournament,
			OffsetDateTime predictionsOpenedAt, OffsetDateTime previousPredictionsOpenedAt) {
		super(tournament, previousTournament);
		this.predictionsOpenedAt = predictionsOpenedAt;
		this.previousPredictionsOpenedAt = previousPredictionsOpenedAt;
	}
	
	public OffsetDateTime getPredictionsOpenedAt() {
		return predictionsOpenedAt;
	}
	
	public OffsetDateTime getPreviousPredictionsOpenedAt() {
		return previousPredictionsOpenedAt;
	}
}
