package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchLocationChangedEvent extends GenericMatchChangedEvent {
	private String location;
	private String previousLocation;
	
	public MatchLocationChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, String location, String previousLocation) {
		super(tournament, previousTournament, match, previousMatch);
		this.location = location;
		this.previousLocation = previousLocation;
	}
	
	public String getLocation() {
		return location;
	}
	
	public String getPreviousLocation() {
		return previousLocation;
	}
}
