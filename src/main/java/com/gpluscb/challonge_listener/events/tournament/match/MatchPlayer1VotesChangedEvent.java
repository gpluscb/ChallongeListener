package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchPlayer1VotesChangedEvent extends GenericMatchChangedEvent {
	private Integer player1Votes;
	private Integer previousPlayer1Votes;
	
	public MatchPlayer1VotesChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, Integer player1Votes, Integer previousPlayer1Votes) {
		super(tournament, previousTournament, match, previousMatch);
		this.player1Votes = player1Votes;
		this.previousPlayer1Votes = previousPlayer1Votes;
	}
	
	public Integer getPlayer1Votes() {
		return player1Votes;
	}
	
	public Integer getPreviousPlayer1Votes() {
		return previousPlayer1Votes;
	}
}
