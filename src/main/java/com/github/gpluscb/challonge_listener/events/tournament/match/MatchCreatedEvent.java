package com.github.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchCreatedEvent extends GenericMatchEvent {
	public MatchCreatedEvent(final Tournament tournament, final Tournament previousTournament, final Match match) {
		super(tournament, previousTournament, match);
	}
}
