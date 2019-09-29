package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchPlayer2VotesChangedEvent extends GenericMatchChangedEvent {
	private final Integer player2Votes;
	private final Integer previousPlayer2Votes;
	
	public MatchPlayer2VotesChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final Integer player2Votes,
			final Integer previousPlayer2Votes) {
		super(tournament, previousTournament, match, previousMatch);
		this.player2Votes = player2Votes;
		this.previousPlayer2Votes = previousPlayer2Votes;
	}
	
	public Integer getPlayer2Votes() {
		return this.player2Votes;
	}
	
	public Integer getPreviousPlayer2Votes() {
		return this.previousPlayer2Votes;
	}
}
