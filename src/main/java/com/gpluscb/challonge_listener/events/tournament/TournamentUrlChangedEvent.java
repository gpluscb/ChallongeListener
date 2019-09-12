package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentUrlChangedEvent extends GenericTournamentChangedEvent {
	private String url;
	private String previousUrl;
	
	public TournamentUrlChangedEvent(Tournament tournament, Tournament previousTournament, String url,
			String previousUrl) {
		super(tournament, previousTournament);
		this.url = url;
		this.previousUrl = previousUrl;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getPreviousUrl() {
		return previousUrl;
	}
}
