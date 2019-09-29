package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchLocationChangedEvent extends GenericMatchChangedEvent {
	private final String location;
	private final String previousLocation;
	
	public MatchLocationChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final String location, final String previousLocation) {
		super(tournament, previousTournament, match, previousMatch);
		this.location = location;
		this.previousLocation = previousLocation;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public String getPreviousLocation() {
		return this.previousLocation;
	}
}
