package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentCreatedByApiChangedEvent extends GenericTournamentChangedEvent {
	private Boolean createdByApi;
	private Boolean previousCreatedByApi;
	
	public TournamentCreatedByApiChangedEvent(Tournament tournament, Tournament previousTournament,
			Boolean createdByApi, Boolean previousCreatedByApi) {
		super(tournament, previousTournament);
		this.createdByApi = createdByApi;
		this.previousCreatedByApi = previousCreatedByApi;
	}
	
	public Boolean getCreatedByApi() {
		return createdByApi;
	}
	
	public Boolean getPreviousCreatedByApi() {
		return previousCreatedByApi;
	}
}
