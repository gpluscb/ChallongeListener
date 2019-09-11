package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.enumeration.MatchState;

public class MatchStateChangedEvent extends GenericMatchChangedEvent {
	private MatchState state;
	private MatchState previousState;
	
	public MatchStateChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, MatchState state, MatchState previousState) {
		super(tournament, previousTournament, match, previousMatch);
		this.state = state;
		this.previousState = previousState;
	}
	
	public MatchState getState() {
		return state;
	}
	
	public MatchState getPreviousState() {
		return previousState;
	}
}
