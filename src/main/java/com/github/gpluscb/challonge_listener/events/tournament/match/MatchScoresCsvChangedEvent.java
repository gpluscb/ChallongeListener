package com.github.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchScoresCsvChangedEvent extends GenericMatchChangedEvent {
	private final String scoresCsv;
	private final String previousScoresCsv;
	
	public MatchScoresCsvChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final String scoresCsv, final String previousScoresCsv) {
		super(tournament, previousTournament, match, previousMatch);
		this.scoresCsv = scoresCsv;
		this.previousScoresCsv = previousScoresCsv;
	}
	
	public String getScoresCsv() {
		return this.scoresCsv;
	}
	
	public String getPreviousScoresCsv() {
		return this.previousScoresCsv;
	}
}
