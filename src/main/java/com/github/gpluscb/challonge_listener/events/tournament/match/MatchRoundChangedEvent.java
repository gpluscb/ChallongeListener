package com.github.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchRoundChangedEvent extends GenericMatchChangedEvent {
	private final Integer round;
	private final Integer previousRound;
	
	public MatchRoundChangedEvent(final Tournament tournament, final Tournament previousTournament, final Match match,
			final Match previousMatch, final Integer round, final Integer previousRound) {
		super(tournament, previousTournament, match, previousMatch);
		this.round = round;
		this.previousRound = previousRound;
	}
	
	public Integer getRound() {
		return this.round;
	}
	
	public Integer getPreviousRound() {
		return this.previousRound;
	}
}
