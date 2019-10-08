package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentReviewBeforeFinalizingChangedEvent extends GenericTournamentChangedEvent {
	private final Boolean reviewBeforeFinalizing;
	private final Boolean previousReviewBeforeFinalizing;
	
	public TournamentReviewBeforeFinalizingChangedEvent(final Tournament tournament,
			final Tournament previousTournament, final Boolean reviewBeforeFinalizing,
			final Boolean previousReviewBeforeFinalizing) {
		super(tournament, previousTournament);
		this.reviewBeforeFinalizing = reviewBeforeFinalizing;
		this.previousReviewBeforeFinalizing = previousReviewBeforeFinalizing;
	}
	
	public Boolean getReviewBeforeFinalizing() {
		return this.reviewBeforeFinalizing;
	}
	
	public Boolean getPreviousReviewBeforeFinalizing() {
		return this.previousReviewBeforeFinalizing;
	}
}
