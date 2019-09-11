package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchIdentifierChangedEvent extends GenericMatchChangedEvent {
	private String identifier;
	private String previousIdentifier;
	
	public MatchIdentifierChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, String identifier, String previousIdentifier) {
		super(tournament, previousTournament, match, previousMatch);
		this.identifier = identifier;
		this.previousIdentifier = previousIdentifier;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public String getPreviousIdentifier() {
		return previousIdentifier;
	}
}
