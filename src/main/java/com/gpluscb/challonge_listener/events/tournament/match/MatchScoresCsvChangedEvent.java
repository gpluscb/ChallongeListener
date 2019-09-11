package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchScoresCsvChangedEvent extends GenericMatchChangedEvent {
	private String scoresCsv;
	private String previousScoresCsv;
	
	public MatchScoresCsvChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, String scoresCsv, String previousScoresCsv) {
		super(tournament, previousTournament, match, previousMatch);
		this.scoresCsv = scoresCsv;
		this.previousScoresCsv = previousScoresCsv;
	}
	
	public String getScoresCsv() {
		return scoresCsv;
	}
	
	public String getPreviousScoresCsv() {
		return previousScoresCsv;
	}
}
