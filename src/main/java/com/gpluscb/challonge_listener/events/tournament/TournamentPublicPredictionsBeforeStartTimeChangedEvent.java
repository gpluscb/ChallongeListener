package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentPublicPredictionsBeforeStartTimeChangedEvent extends GenericTournamentChangedEvent {
	private final Boolean publicPredictionsBeforeStartTime;
	private final Boolean previousPublicPredictionsBeforeStartTime;
	
	public TournamentPublicPredictionsBeforeStartTimeChangedEvent(final Tournament tournament,
			final Tournament previousTournament, final Boolean publicPredictionsBeforeStartTime,
			final Boolean previousPublicPredictionsBeforeStartTime) {
		super(tournament, previousTournament);
		this.publicPredictionsBeforeStartTime = publicPredictionsBeforeStartTime;
		this.previousPublicPredictionsBeforeStartTime = previousPublicPredictionsBeforeStartTime;
	}
	
	public Boolean getPublicPredictionsBeforeStartTime() {
		return this.publicPredictionsBeforeStartTime;
	}
	
	public Boolean getPreviousPublicPredictionsBeforeStartTime() {
		return this.previousPublicPredictionsBeforeStartTime;
	}
}
