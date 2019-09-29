package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentFullChallongeUrlChangedEvent extends GenericTournamentChangedEvent {
	private final String fullChallongeUrl;
	private final String previousFullChallongeUrl;
	
	public TournamentFullChallongeUrlChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final String fullChallongeUrl, final String previousFullChallongeUrl) {
		super(tournament, previousTournament);
		this.fullChallongeUrl = fullChallongeUrl;
		this.previousFullChallongeUrl = previousFullChallongeUrl;
	}
	
	public String getFullChallongeUrl() {
		return this.fullChallongeUrl;
	}
	
	public String getPreviousFullChallongeUrl() {
		return this.previousFullChallongeUrl;
	}
}
