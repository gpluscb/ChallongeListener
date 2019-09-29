package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchPlayer1VotesChangedEvent extends GenericMatchChangedEvent {
	private final Integer player1Votes;
	private final Integer previousPlayer1Votes;
	
	public MatchPlayer1VotesChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final Integer player1Votes,
			final Integer previousPlayer1Votes) {
		super(tournament, previousTournament, match, previousMatch);
		this.player1Votes = player1Votes;
		this.previousPlayer1Votes = previousPlayer1Votes;
	}
	
	public Integer getPlayer1Votes() {
		return this.player1Votes;
	}
	
	public Integer getPreviousPlayer1Votes() {
		return this.previousPlayer1Votes;
	}
}
