package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchPlayer2PrerequisiteMatchIdChangedEvent extends GenericMatchChangedEvent {
	private Long player2PrerequisiteMatchPlayer2PrerequisiteMatchId;
	private Long previousPlayer2PrerequisiteMatchId;
	
	public MatchPlayer2PrerequisiteMatchIdChangedEvent(Tournament tournament, Tournament previousTournament,
			Match match, Match previousMatch, Long player2PrerequisiteMatchPlayer2PrerequisiteMatchId,
			Long previousPlayer2PrerequisiteMatchId) {
		super(tournament, previousTournament, match, previousMatch);
		this.player2PrerequisiteMatchPlayer2PrerequisiteMatchId = player2PrerequisiteMatchPlayer2PrerequisiteMatchId;
		this.previousPlayer2PrerequisiteMatchId = previousPlayer2PrerequisiteMatchId;
	}
	
	public Long getPlayer2PrerequisiteMatchId() {
		return player2PrerequisiteMatchPlayer2PrerequisiteMatchId;
	}
	
	public Long getPreviousPlayer2PrerequisiteMatchId() {
		return previousPlayer2PrerequisiteMatchId;
	}
}
