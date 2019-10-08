package com.github.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchPlayer2IdChangedEvent extends GenericMatchChangedEvent {
	private final Long player2Player2Id;
	private final Long previousPlayer2Id;
	
	public MatchPlayer2IdChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final Long player2Player2Id, final Long previousPlayer2Id) {
		super(tournament, previousTournament, match, previousMatch);
		this.player2Player2Id = player2Player2Id;
		this.previousPlayer2Id = previousPlayer2Id;
	}
	
	public Long getPlayer2Id() {
		return this.player2Player2Id;
	}
	
	public Long getPreviousPlayer2Id() {
		return this.previousPlayer2Id;
	}
}
