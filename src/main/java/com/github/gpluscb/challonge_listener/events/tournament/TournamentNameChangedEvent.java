package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentNameChangedEvent extends GenericTournamentChangedEvent {
	private final String name;
	private final String previousName;
	
	public TournamentNameChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final String name, final String previousName) {
		super(tournament, previousTournament);
		this.name = name;
		this.previousName = previousName;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPreviousName() {
		return this.previousName;
	}
}
