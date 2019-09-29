package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchWinnerIdChangedEvent extends GenericMatchChangedEvent {
	private final Long winnerWinnerId;
	private final Long previousWinnerId;
	
	public MatchWinnerIdChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final Long winnerWinnerId, final Long previousWinnerId) {
		super(tournament, previousTournament, match, previousMatch);
		this.winnerWinnerId = winnerWinnerId;
		this.previousWinnerId = previousWinnerId;
	}
	
	public Long getWinnerId() {
		return this.winnerWinnerId;
	}
	
	public Long getPreviousWinnerId() {
		return this.previousWinnerId;
	}
}
