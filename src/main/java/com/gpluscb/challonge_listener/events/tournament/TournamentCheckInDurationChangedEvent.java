package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentCheckInDurationChangedEvent extends GenericTournamentChangedEvent {
	private Long checkInDuration;
	private Long previousCheckInDuration;
	
	public TournamentCheckInDurationChangedEvent(Tournament tournament, Tournament previousTournament, Long checkInDuration, Long previousCheckInDuration) {
		super(tournament, previousTournament);
		this.checkInDuration = checkInDuration;
		this.previousCheckInDuration = previousCheckInDuration;
	}
	
	public Long getCheckInDuration() {
		return checkInDuration;
	}
	
	public Long getPreviousCheckInDuration() {
		return previousCheckInDuration;
	}
}
