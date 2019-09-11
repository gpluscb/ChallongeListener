package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchCreatedEvent extends GenericMatchEvent {
	public MatchCreatedEvent(Tournament tournament, Tournament previousTournament, Match match) {
		super(tournament, previousTournament, match);
	}
}
