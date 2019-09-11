package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentSignupCapChangedEvent extends GenericTournamentChangedEvent {
	private Integer signupCap;
	private Integer previousSignupCap;
	
	public TournamentSignupCapChangedEvent(Tournament tournament, Tournament previousTournament, Integer signupCap, Integer previousSignupCap) {
		super(tournament, previousTournament);
		this.signupCap = signupCap;
		this.previousSignupCap = previousSignupCap;
	}
	
	public Integer getSignupCap() {
		return signupCap;
	}
	
	public Integer getPreviousSignupCap() {
		return previousSignupCap;
	}
}
