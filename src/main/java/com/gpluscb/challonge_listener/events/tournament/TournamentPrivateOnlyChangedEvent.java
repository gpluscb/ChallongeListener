package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentPrivateOnlyChangedEvent extends GenericTournamentChangedEvent {
	private final Boolean privateOnly;
	private final Boolean previousPrivateOnly;
	
	public TournamentPrivateOnlyChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Boolean privateOnly, final Boolean previousPrivateOnly) {
		super(tournament, previousTournament);
		this.privateOnly = privateOnly;
		this.previousPrivateOnly = previousPrivateOnly;
	}
	
	public Boolean getPrivateOnly() {
		return this.privateOnly;
	}
	
	public Boolean getPreviousPrivateOnly() {
		return this.previousPrivateOnly;
	}
}
