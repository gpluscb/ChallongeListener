package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentCheckInDurationChangedEvent extends GenericTournamentChangedEvent {
	private final Long checkInDuration;
	private final Long previousCheckInDuration;
	
	public TournamentCheckInDurationChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Long checkInDuration, final Long previousCheckInDuration) {
		super(tournament, previousTournament);
		this.checkInDuration = checkInDuration;
		this.previousCheckInDuration = previousCheckInDuration;
	}
	
	public Long getCheckInDuration() {
		return this.checkInDuration;
	}
	
	public Long getPreviousCheckInDuration() {
		return this.previousCheckInDuration;
	}
}
