package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentDescriptionChangedEvent extends GenericTournamentChangedEvent {
	private final String description;
	private final String previousDescription;
	
	public TournamentDescriptionChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final String description, final String previousDescription) {
		super(tournament, previousTournament);
		this.description = description;
		this.previousDescription = previousDescription;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String getPreviousDescription() {
		return this.previousDescription;
	}
}
