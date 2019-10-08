package com.github.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchIdentifierChangedEvent extends GenericMatchChangedEvent {
	private final String identifier;
	private final String previousIdentifier;
	
	public MatchIdentifierChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final String identifier, final String previousIdentifier) {
		super(tournament, previousTournament, match, previousMatch);
		this.identifier = identifier;
		this.previousIdentifier = previousIdentifier;
	}
	
	public String getIdentifier() {
		return this.identifier;
	}
	
	public String getPreviousIdentifier() {
		return this.previousIdentifier;
	}
}
