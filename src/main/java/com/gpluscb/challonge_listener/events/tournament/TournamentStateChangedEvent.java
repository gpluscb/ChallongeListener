package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.enumeration.TournamentState;

public class TournamentStateChangedEvent extends GenericTournamentChangedEvent {
	private final TournamentState state;
	private final TournamentState previousState;
	
	public TournamentStateChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final TournamentState state, final TournamentState previousState) {
		super(tournament, previousTournament);
		this.state = state;
		this.previousState = previousState;
	}
	
	public TournamentState getState() {
		return this.state;
	}
	
	public TournamentState getPreviousState() {
		return this.previousState;
	}
}
