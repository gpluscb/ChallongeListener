package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentReviewBeforeFinalizingChangedEvent extends GenericTournamentChangedEvent {
	private Boolean reviewBeforeFinalizing;
	private Boolean previousReviewBeforeFinalizing;
	
	public TournamentReviewBeforeFinalizingChangedEvent(Tournament tournament, Tournament previousTournament, Boolean reviewBeforeFinalizing, Boolean previousReviewBeforeFinalizing) {
		super(tournament, previousTournament);
		this.reviewBeforeFinalizing = reviewBeforeFinalizing;
		this.previousReviewBeforeFinalizing = previousReviewBeforeFinalizing;
	}
	
	public Boolean getReviewBeforeFinalizing() {
		return reviewBeforeFinalizing;
	}
	
	public Boolean getPreviousReviewBeforeFinalizing() {
		return previousReviewBeforeFinalizing;
	}
}
