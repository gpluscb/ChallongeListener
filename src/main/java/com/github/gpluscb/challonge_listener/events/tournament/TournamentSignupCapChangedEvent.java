package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentSignupCapChangedEvent extends GenericTournamentChangedEvent {
	private final Integer signupCap;
	private final Integer previousSignupCap;
	
	public TournamentSignupCapChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Integer signupCap, final Integer previousSignupCap) {
		super(tournament, previousTournament);
		this.signupCap = signupCap;
		this.previousSignupCap = previousSignupCap;
	}
	
	public Integer getSignupCap() {
		return this.signupCap;
	}
	
	public Integer getPreviousSignupCap() {
		return this.previousSignupCap;
	}
}
