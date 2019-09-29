package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchDeletedEvent extends GenericMatchEvent {
	public MatchDeletedEvent(final Tournament tournament, final Tournament previousTournament, final Match match) {
		super(tournament, previousTournament, match);
	}
}
