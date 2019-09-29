package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchPlayer2PrerequisiteMatchIdChangedEvent extends GenericMatchChangedEvent {
	private final Long player2PrerequisiteMatchPlayer2PrerequisiteMatchId;
	private final Long previousPlayer2PrerequisiteMatchId;
	
	public MatchPlayer2PrerequisiteMatchIdChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final Long player2PrerequisiteMatchPlayer2PrerequisiteMatchId,
			final Long previousPlayer2PrerequisiteMatchId) {
		super(tournament, previousTournament, match, previousMatch);
		this.player2PrerequisiteMatchPlayer2PrerequisiteMatchId = player2PrerequisiteMatchPlayer2PrerequisiteMatchId;
		this.previousPlayer2PrerequisiteMatchId = previousPlayer2PrerequisiteMatchId;
	}
	
	public Long getPlayer2PrerequisiteMatchId() {
		return this.player2PrerequisiteMatchPlayer2PrerequisiteMatchId;
	}
	
	public Long getPreviousPlayer2PrerequisiteMatchId() {
		return this.previousPlayer2PrerequisiteMatchId;
	}
}
