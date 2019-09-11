package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchPlayer2IdChangedEvent extends GenericMatchChangedEvent {
	private Long player2Player2Id;
	private Long previousPlayer2Id;
	
	public MatchPlayer2IdChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, Long player2Player2Id, Long previousPlayer2Id) {
		super(tournament, previousTournament, match, previousMatch);
		this.player2Player2Id = player2Player2Id;
		this.previousPlayer2Id = previousPlayer2Id;
	}
	
	public Long getPlayer2Id() {
		return player2Player2Id;
	}
	
	public Long getPreviousPlayer2Id() {
		return previousPlayer2Id;
	}
}
