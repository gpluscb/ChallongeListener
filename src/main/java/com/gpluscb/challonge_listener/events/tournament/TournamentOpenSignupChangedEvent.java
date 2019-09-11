package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentOpenSignupChangedEvent extends GenericTournamentChangedEvent {
	private Boolean openSignup;
	private Boolean previousOpenSignup;
	
	public TournamentOpenSignupChangedEvent(Tournament tournament, Tournament previousTournament, Boolean openSignup, Boolean previousOpenSignup) {
		super(tournament, previousTournament);
		this.openSignup = openSignup;
		this.previousOpenSignup = previousOpenSignup;
	}
	
	public Boolean getOpenSignup() {
		return openSignup;
	}
	
	public Boolean getPreviousOpenSignup() {
		return previousOpenSignup;
	}
}
