package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchPlayer1PrerequisiteMatchIdChangedEvent extends GenericMatchChangedEvent {
	private Long player1PrerequisiteMatchPlayer1PrerequisiteMatchId;
	private Long previousPlayer1PrerequisiteMatchId;
	
	public MatchPlayer1PrerequisiteMatchIdChangedEvent(Tournament tournament, Tournament previousTournament,
			Match match, Match previousMatch, Long player1PrerequisiteMatchPlayer1PrerequisiteMatchId,
			Long previousPlayer1PrerequisiteMatchId) {
		super(tournament, previousTournament, match, previousMatch);
		this.player1PrerequisiteMatchPlayer1PrerequisiteMatchId = player1PrerequisiteMatchPlayer1PrerequisiteMatchId;
		this.previousPlayer1PrerequisiteMatchId = previousPlayer1PrerequisiteMatchId;
	}
	
	public Long getPlayer1PrerequisiteMatchId() {
		return player1PrerequisiteMatchPlayer1PrerequisiteMatchId;
	}
	
	public Long getPreviousPlayer1PrerequisiteMatchId() {
		return previousPlayer1PrerequisiteMatchId;
	}
}
