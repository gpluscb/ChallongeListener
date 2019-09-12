package com.gpluscb.challonge_listener.events.tournament;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentStartedAtChangedEvent extends GenericTournamentChangedEvent {
	private OffsetDateTime startedAt;
	private OffsetDateTime previousStartedAt;
	
	public TournamentStartedAtChangedEvent(Tournament tournament, Tournament previousTournament,
			OffsetDateTime startedAt, OffsetDateTime previousStartedAt) {
		super(tournament, previousTournament);
		this.startedAt = startedAt;
		this.previousStartedAt = previousStartedAt;
	}
	
	public OffsetDateTime getStartedAt() {
		return startedAt;
	}
	
	public OffsetDateTime getPreviousStartedAt() {
		return previousStartedAt;
	}
}
