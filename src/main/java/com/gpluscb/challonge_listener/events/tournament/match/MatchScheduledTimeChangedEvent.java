package com.gpluscb.challonge_listener.events.tournament.match;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchScheduledTimeChangedEvent extends GenericMatchChangedEvent {
	private OffsetDateTime scheduledTime;
	private OffsetDateTime previousScheduledTime;
	
	public MatchScheduledTimeChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, OffsetDateTime scheduledTime, OffsetDateTime previousScheduledTime) {
		super(tournament, previousTournament, match, previousMatch);
		this.scheduledTime = scheduledTime;
		this.previousScheduledTime = previousScheduledTime;
	}
	
	public OffsetDateTime getScheduledTime() {
		return scheduledTime;
	}
	
	public OffsetDateTime getPreviousScheduledTime() {
		return previousScheduledTime;
	}
}
