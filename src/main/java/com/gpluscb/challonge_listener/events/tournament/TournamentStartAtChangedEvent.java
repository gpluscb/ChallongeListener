package com.gpluscb.challonge_listener.events.tournament;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentStartAtChangedEvent extends GenericTournamentChangedEvent {
	private OffsetDateTime startAt;
	private OffsetDateTime previousStartAt;
	
	public TournamentStartAtChangedEvent(Tournament tournament, Tournament previousTournament, OffsetDateTime startAt, OffsetDateTime previousStartAt) {
		super(tournament, previousTournament);
		this.startAt = startAt;
		this.previousStartAt = previousStartAt;
	}
	
	public OffsetDateTime getStartAt() {
		return startAt;
	}
	
	public OffsetDateTime getPreviousStartAt() {
		return previousStartAt;
	}
}
