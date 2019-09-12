package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentSignUpUrlChangedEvent extends GenericTournamentChangedEvent {
	private String signUpUrl;
	private String previousSignUpUrl;
	
	public TournamentSignUpUrlChangedEvent(Tournament tournament, Tournament previousTournament, String signUpUrl,
			String previousSignUpUrl) {
		super(tournament, previousTournament);
		this.signUpUrl = signUpUrl;
		this.previousSignUpUrl = previousSignUpUrl;
	}
	
	public String getSignUpUrl() {
		return signUpUrl;
	}
	
	public String getPreviousSignUpUrl() {
		return previousSignUpUrl;
	}
}
