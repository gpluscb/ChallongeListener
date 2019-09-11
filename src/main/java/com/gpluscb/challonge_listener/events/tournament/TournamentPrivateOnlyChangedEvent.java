package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentPrivateOnlyChangedEvent extends GenericTournamentChangedEvent {
	private Boolean privateOnly;
	private Boolean previousPrivateOnly;
	
	public TournamentPrivateOnlyChangedEvent(Tournament tournament, Tournament previousTournament, Boolean privateOnly, Boolean previousPrivateOnly) {
		super(tournament, previousTournament);
		this.privateOnly = privateOnly;
		this.previousPrivateOnly = previousPrivateOnly;
	}
	
	public Boolean getPrivateOnly() {
		return privateOnly;
	}
	
	public Boolean getPreviousPrivateOnly() {
		return previousPrivateOnly;
	}
}
