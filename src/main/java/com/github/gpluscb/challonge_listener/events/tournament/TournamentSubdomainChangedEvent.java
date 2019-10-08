package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentSubdomainChangedEvent extends GenericTournamentChangedEvent {
	private final String subdomain;
	private final String previousSubdomain;
	
	public TournamentSubdomainChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final String subdomain, final String previousSubdomain) {
		super(tournament, previousTournament);
		this.subdomain = subdomain;
		this.previousSubdomain = previousSubdomain;
	}
	
	public String getSubdomain() {
		return this.subdomain;
	}
	
	public String getPreviousSubdomain() {
		return this.previousSubdomain;
	}
}
