package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchWinnerIdChangedEvent extends GenericMatchChangedEvent {
	private Long winnerWinnerId;
	private Long previousWinnerId;
	
	public MatchWinnerIdChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, Long winnerWinnerId, Long previousWinnerId) {
		super(tournament, previousTournament, match, previousMatch);
		this.winnerWinnerId = winnerWinnerId;
		this.previousWinnerId = previousWinnerId;
	}
	
	public Long getWinnerId() {
		return winnerWinnerId;
	}
	
	public Long getPreviousWinnerId() {
		return previousWinnerId;
	}
}
