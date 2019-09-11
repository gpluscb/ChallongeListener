package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchPrerequisiteMatchIdsCsvChangedEvent extends GenericMatchChangedEvent {
	private String prerequisiteMatchPrerequisiteMatchIdsCsvsCsv;
	private String previousPrerequisiteMatchIdsCsv;
	
	public MatchPrerequisiteMatchIdsCsvChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, String prerequisiteMatchPrerequisiteMatchIdsCsvsCsv,
			String previousPrerequisiteMatchIdsCsv) {
		super(tournament, previousTournament, match, previousMatch);
		this.prerequisiteMatchPrerequisiteMatchIdsCsvsCsv = prerequisiteMatchPrerequisiteMatchIdsCsvsCsv;
		this.previousPrerequisiteMatchIdsCsv = previousPrerequisiteMatchIdsCsv;
	}
	
	public String getPrerequisiteMatchIdsCsv() {
		return prerequisiteMatchPrerequisiteMatchIdsCsvsCsv;
	}
	
	public String getPreviousPrerequisiteMatchIdsCsv() {
		return previousPrerequisiteMatchIdsCsv;
	}
}
