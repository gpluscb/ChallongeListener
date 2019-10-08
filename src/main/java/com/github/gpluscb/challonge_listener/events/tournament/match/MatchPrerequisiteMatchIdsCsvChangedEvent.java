package com.github.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchPrerequisiteMatchIdsCsvChangedEvent extends GenericMatchChangedEvent {
	private final String prerequisiteMatchPrerequisiteMatchIdsCsvsCsv;
	private final String previousPrerequisiteMatchIdsCsv;
	
	public MatchPrerequisiteMatchIdsCsvChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final String prerequisiteMatchPrerequisiteMatchIdsCsvsCsv,
			final String previousPrerequisiteMatchIdsCsv) {
		super(tournament, previousTournament, match, previousMatch);
		this.prerequisiteMatchPrerequisiteMatchIdsCsvsCsv = prerequisiteMatchPrerequisiteMatchIdsCsvsCsv;
		this.previousPrerequisiteMatchIdsCsv = previousPrerequisiteMatchIdsCsv;
	}
	
	public String getPrerequisiteMatchIdsCsv() {
		return this.prerequisiteMatchPrerequisiteMatchIdsCsvsCsv;
	}
	
	public String getPreviousPrerequisiteMatchIdsCsv() {
		return this.previousPrerequisiteMatchIdsCsv;
	}
}
