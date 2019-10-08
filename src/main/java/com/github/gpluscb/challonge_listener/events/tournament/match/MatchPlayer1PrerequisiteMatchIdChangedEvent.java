package com.github.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchPlayer1PrerequisiteMatchIdChangedEvent extends GenericMatchChangedEvent {
	private final Long player1PrerequisiteMatchPlayer1PrerequisiteMatchId;
	private final Long previousPlayer1PrerequisiteMatchId;
	
	public MatchPlayer1PrerequisiteMatchIdChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final Long player1PrerequisiteMatchPlayer1PrerequisiteMatchId,
			final Long previousPlayer1PrerequisiteMatchId) {
		super(tournament, previousTournament, match, previousMatch);
		this.player1PrerequisiteMatchPlayer1PrerequisiteMatchId = player1PrerequisiteMatchPlayer1PrerequisiteMatchId;
		this.previousPlayer1PrerequisiteMatchId = previousPlayer1PrerequisiteMatchId;
	}
	
	public Long getPlayer1PrerequisiteMatchId() {
		return this.player1PrerequisiteMatchPlayer1PrerequisiteMatchId;
	}
	
	public Long getPreviousPlayer1PrerequisiteMatchId() {
		return this.previousPlayer1PrerequisiteMatchId;
	}
}
