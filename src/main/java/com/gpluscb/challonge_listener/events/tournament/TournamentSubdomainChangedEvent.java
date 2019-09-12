package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentSubdomainChangedEvent extends GenericTournamentChangedEvent {
	private String subdomain;
	private String previousSubdomain;
	
	public TournamentSubdomainChangedEvent(Tournament tournament, Tournament previousTournament, String subdomain,
			String previousSubdomain) {
		super(tournament, previousTournament);
		this.subdomain = subdomain;
		this.previousSubdomain = previousSubdomain;
	}
	
	public String getSubdomain() {
		return subdomain;
	}
	
	public String getPreviousSubdomain() {
		return previousSubdomain;
	}
}
