package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentCreatedByApiChangedEvent extends GenericTournamentChangedEvent {
	private final Boolean createdByApi;
	private final Boolean previousCreatedByApi;
	
	public TournamentCreatedByApiChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Boolean createdByApi, final Boolean previousCreatedByApi) {
		super(tournament, previousTournament);
		this.createdByApi = createdByApi;
		this.previousCreatedByApi = previousCreatedByApi;
	}
	
	public Boolean getCreatedByApi() {
		return this.createdByApi;
	}
	
	public Boolean getPreviousCreatedByApi() {
		return this.previousCreatedByApi;
	}
}
