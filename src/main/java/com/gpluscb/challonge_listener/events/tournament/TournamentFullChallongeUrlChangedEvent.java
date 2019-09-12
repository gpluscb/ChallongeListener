package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentFullChallongeUrlChangedEvent extends GenericTournamentChangedEvent {
	private String fullChallongeUrl;
	private String previousFullChallongeUrl;
	
	public TournamentFullChallongeUrlChangedEvent(Tournament tournament, Tournament previousTournament,
			String fullChallongeUrl, String previousFullChallongeUrl) {
		super(tournament, previousTournament);
		this.fullChallongeUrl = fullChallongeUrl;
		this.previousFullChallongeUrl = previousFullChallongeUrl;
	}
	
	public String getFullChallongeUrl() {
		return fullChallongeUrl;
	}
	
	public String getPreviousFullChallongeUrl() {
		return previousFullChallongeUrl;
	}
}
