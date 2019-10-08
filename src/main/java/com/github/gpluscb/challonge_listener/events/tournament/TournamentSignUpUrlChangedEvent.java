package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentSignUpUrlChangedEvent extends GenericTournamentChangedEvent {
	private final String signUpUrl;
	private final String previousSignUpUrl;
	
	public TournamentSignUpUrlChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final String signUpUrl, final String previousSignUpUrl) {
		super(tournament, previousTournament);
		this.signUpUrl = signUpUrl;
		this.previousSignUpUrl = previousSignUpUrl;
	}
	
	public String getSignUpUrl() {
		return this.signUpUrl;
	}
	
	public String getPreviousSignUpUrl() {
		return this.previousSignUpUrl;
	}
}
