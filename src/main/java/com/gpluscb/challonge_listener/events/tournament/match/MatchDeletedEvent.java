package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchDeletedEvent extends GenericMatchEvent {
	public MatchDeletedEvent(Tournament tournament, Tournament previousTournament, Match match) {
		super(tournament, previousTournament, match);
	}
}
