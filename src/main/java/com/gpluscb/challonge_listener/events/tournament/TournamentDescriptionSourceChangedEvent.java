package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentDescriptionSourceChangedEvent extends GenericTournamentChangedEvent {
	private final String descriptionSource;
	private final String previousDescriptionSource;
	
	public TournamentDescriptionSourceChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final String descriptionSource, final String previousDescriptionSource) {
		super(tournament, previousTournament);
		this.descriptionSource = descriptionSource;
		this.previousDescriptionSource = previousDescriptionSource;
	}
	
	public String getDescriptionSource() {
		return this.descriptionSource;
	}
	
	public String getPreviousDescriptionSource() {
		return this.previousDescriptionSource;
	}
}
