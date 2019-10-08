package com.github.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.enumeration.MatchState;

public class MatchStateChangedEvent extends GenericMatchChangedEvent {
	private final MatchState state;
	private final MatchState previousState;
	
	public MatchStateChangedEvent(final Tournament tournament, final Tournament previousTournament, final Match match,
			final Match previousMatch, final MatchState state, final MatchState previousState) {
		super(tournament, previousTournament, match, previousMatch);
		this.state = state;
		this.previousState = previousState;
	}
	
	public MatchState getState() {
		return this.state;
	}
	
	public MatchState getPreviousState() {
		return this.previousState;
	}
}
