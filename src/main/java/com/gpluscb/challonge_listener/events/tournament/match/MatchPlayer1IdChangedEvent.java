package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchPlayer1IdChangedEvent extends GenericMatchChangedEvent {
	private final Long player1Player1Id;
	private final Long previousPlayer1Id;
	
	public MatchPlayer1IdChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final Long player1Player1Id, final Long previousPlayer1Id) {
		super(tournament, previousTournament, match, previousMatch);
		this.player1Player1Id = player1Player1Id;
		this.previousPlayer1Id = previousPlayer1Id;
	}
	
	public Long getPlayer1Id() {
		return this.player1Player1Id;
	}
	
	public Long getPreviousPlayer1Id() {
		return this.previousPlayer1Id;
	}
}
