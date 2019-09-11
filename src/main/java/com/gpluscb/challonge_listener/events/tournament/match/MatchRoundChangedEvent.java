package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchRoundChangedEvent extends GenericMatchChangedEvent {
	private Integer round;
	private Integer previousRound;
	
	public MatchRoundChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, Integer round, Integer previousRound) {
		super(tournament, previousTournament, match, previousMatch);
		this.round = round;
		this.previousRound = previousRound;
	}
	
	public Integer getRound() {
		return round;
	}
	
	public Integer getPreviousRound() {
		return previousRound;
	}
}
