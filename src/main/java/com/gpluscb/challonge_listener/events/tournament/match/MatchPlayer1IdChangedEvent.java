package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchPlayer1IdChangedEvent extends GenericMatchChangedEvent {
	private Long player1Player1Id;
	private Long previousPlayer1Id;
	
	public MatchPlayer1IdChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, Long player1Player1Id, Long previousPlayer1Id) {
		super(tournament, previousTournament, match, previousMatch);
		this.player1Player1Id = player1Player1Id;
		this.previousPlayer1Id = previousPlayer1Id;
	}
	
	public Long getPlayer1Id() {
		return player1Player1Id;
	}
	
	public Long getPreviousPlayer1Id() {
		return previousPlayer1Id;
	}
}
