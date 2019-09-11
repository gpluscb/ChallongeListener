package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentDescriptionSourceChangedEvent extends GenericTournamentChangedEvent {
	private String descriptionSource;
	private String previousDescriptionSource;
	
	public TournamentDescriptionSourceChangedEvent(Tournament tournament, Tournament previousTournament, String descriptionSource, String previousDescriptionSource) {
		super(tournament, previousTournament);
		this.descriptionSource = descriptionSource;
		this.previousDescriptionSource = previousDescriptionSource;
	}
	
	public String getDescriptionSource() {
		return descriptionSource;
	}
	
	public String getPreviousDescriptionSource() {
		return previousDescriptionSource;
	}
}
