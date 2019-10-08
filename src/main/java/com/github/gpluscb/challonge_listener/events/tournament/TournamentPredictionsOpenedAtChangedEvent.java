package com.github.gpluscb.challonge_listener.events.tournament;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentPredictionsOpenedAtChangedEvent extends GenericTournamentChangedEvent {
	private final OffsetDateTime predictionsOpenedAt;
	private final OffsetDateTime previousPredictionsOpenedAt;
	
	public TournamentPredictionsOpenedAtChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final OffsetDateTime predictionsOpenedAt, final OffsetDateTime previousPredictionsOpenedAt) {
		super(tournament, previousTournament);
		this.predictionsOpenedAt = predictionsOpenedAt;
		this.previousPredictionsOpenedAt = previousPredictionsOpenedAt;
	}
	
	public OffsetDateTime getPredictionsOpenedAt() {
		return this.predictionsOpenedAt;
	}
	
	public OffsetDateTime getPreviousPredictionsOpenedAt() {
		return this.previousPredictionsOpenedAt;
	}
}
