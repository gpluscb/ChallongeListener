package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchPlayer2VotesChangedEvent extends GenericMatchChangedEvent {
	private Integer player2Votes;
	private Integer previousPlayer2Votes;
	
	public MatchPlayer2VotesChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, Integer player2Votes, Integer previousPlayer2Votes) {
		super(tournament, previousTournament, match, previousMatch);
		this.player2Votes = player2Votes;
		this.previousPlayer2Votes = previousPlayer2Votes;
	}
	
	public Integer getPlayer2Votes() {
		return player2Votes;
	}
	
	public Integer getPreviousPlayer2Votes() {
		return previousPlayer2Votes;
	}
}
