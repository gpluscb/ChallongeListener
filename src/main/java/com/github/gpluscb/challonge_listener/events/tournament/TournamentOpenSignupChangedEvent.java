package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentOpenSignupChangedEvent extends GenericTournamentChangedEvent {
	private final Boolean openSignup;
	private final Boolean previousOpenSignup;
	
	public TournamentOpenSignupChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Boolean openSignup, final Boolean previousOpenSignup) {
		super(tournament, previousTournament);
		this.openSignup = openSignup;
		this.previousOpenSignup = previousOpenSignup;
	}
	
	public Boolean getOpenSignup() {
		return this.openSignup;
	}
	
	public Boolean getPreviousOpenSignup() {
		return this.previousOpenSignup;
	}
}
