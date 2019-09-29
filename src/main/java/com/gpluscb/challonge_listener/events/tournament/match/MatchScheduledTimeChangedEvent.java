package com.gpluscb.challonge_listener.events.tournament.match;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchScheduledTimeChangedEvent extends GenericMatchChangedEvent {
	private final OffsetDateTime scheduledTime;
	private final OffsetDateTime previousScheduledTime;
	
	public MatchScheduledTimeChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final OffsetDateTime scheduledTime,
			final OffsetDateTime previousScheduledTime) {
		super(tournament, previousTournament, match, previousMatch);
		this.scheduledTime = scheduledTime;
		this.previousScheduledTime = previousScheduledTime;
	}
	
	public OffsetDateTime getScheduledTime() {
		return this.scheduledTime;
	}
	
	public OffsetDateTime getPreviousScheduledTime() {
		return this.previousScheduledTime;
	}
}
