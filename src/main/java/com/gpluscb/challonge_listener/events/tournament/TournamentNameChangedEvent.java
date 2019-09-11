package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentNameChangedEvent extends GenericTournamentChangedEvent {
	private String name;
	private String previousName;
	
	public TournamentNameChangedEvent(Tournament tournament, Tournament previousTournament, String name, String previousName) {
		super(tournament, previousTournament);
		this.name = name;
		this.previousName = previousName;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPreviousName() {
		return previousName;
	}
}
