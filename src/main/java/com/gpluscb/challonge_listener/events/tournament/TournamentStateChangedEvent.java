package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.enumeration.TournamentState;

public class TournamentStateChangedEvent extends GenericTournamentChangedEvent {
	private TournamentState state;
	private TournamentState previousState;
	
	public TournamentStateChangedEvent(Tournament tournament, Tournament previousTournament, TournamentState state, TournamentState previousState) {
		super(tournament, previousTournament);
		this.state = state;
		this.previousState = previousState;
	}
	
	public TournamentState getState() {
		return state;
	}
	
	public TournamentState getPreviousState() {
		return previousState;
	}
}
