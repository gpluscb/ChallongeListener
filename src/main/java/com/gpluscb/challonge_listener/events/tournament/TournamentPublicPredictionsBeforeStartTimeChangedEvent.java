package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentPublicPredictionsBeforeStartTimeChangedEvent extends GenericTournamentChangedEvent {
	private Boolean publicPredictionsBeforeStartTime;
	private Boolean previousPublicPredictionsBeforeStartTime;
	
	public TournamentPublicPredictionsBeforeStartTimeChangedEvent(Tournament tournament, Tournament previousTournament,
			Boolean publicPredictionsBeforeStartTime, Boolean previousPublicPredictionsBeforeStartTime) {
		super(tournament, previousTournament);
		this.publicPredictionsBeforeStartTime = publicPredictionsBeforeStartTime;
		this.previousPublicPredictionsBeforeStartTime = previousPublicPredictionsBeforeStartTime;
	}
	
	public Boolean getPublicPredictionsBeforeStartTime() {
		return publicPredictionsBeforeStartTime;
	}
	
	public Boolean getPreviousPublicPredictionsBeforeStartTime() {
		return previousPublicPredictionsBeforeStartTime;
	}
}
