package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentUrlChangedEvent extends GenericTournamentChangedEvent {
	private final String url;
	private final String previousUrl;
	
	public TournamentUrlChangedEvent(final Tournament tournament, final Tournament previousTournament, final String url,
			final String previousUrl) {
		super(tournament, previousTournament);
		this.url = url;
		this.previousUrl = previousUrl;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public String getPreviousUrl() {
		return this.previousUrl;
	}
}
