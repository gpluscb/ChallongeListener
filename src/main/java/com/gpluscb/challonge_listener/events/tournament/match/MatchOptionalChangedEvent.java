package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchOptionalChangedEvent extends GenericMatchChangedEvent {
	private final Boolean optional;
	private final Boolean previousOptional;
	
	public MatchOptionalChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final Boolean optional, final Boolean previousOptional) {
		super(tournament, previousTournament, match, previousMatch);
		this.optional = optional;
		this.previousOptional = previousOptional;
	}
	
	public Boolean getOptional() {
		return this.optional;
	}
	
	public Boolean getPreviousOptional() {
		return this.previousOptional;
	}
}
