package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentDescriptionChangedEvent extends GenericTournamentChangedEvent {
	private String description;
	private String previousDescription;
	
	public TournamentDescriptionChangedEvent(Tournament tournament, Tournament previousTournament, String description,
			String previousDescription) {
		super(tournament, previousTournament);
		this.description = description;
		this.previousDescription = previousDescription;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getPreviousDescription() {
		return previousDescription;
	}
}
