package com.gpluscb.challonge_listener.events.tournament;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentStartedAtChangedEvent extends GenericTournamentChangedEvent {
	private final OffsetDateTime startedAt;
	private final OffsetDateTime previousStartedAt;
	
	public TournamentStartedAtChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final OffsetDateTime startedAt, final OffsetDateTime previousStartedAt) {
		super(tournament, previousTournament);
		this.startedAt = startedAt;
		this.previousStartedAt = previousStartedAt;
	}
	
	public OffsetDateTime getStartedAt() {
		return this.startedAt;
	}
	
	public OffsetDateTime getPreviousStartedAt() {
		return this.previousStartedAt;
	}
}
