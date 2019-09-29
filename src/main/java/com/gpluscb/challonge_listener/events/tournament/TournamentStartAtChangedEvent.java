package com.gpluscb.challonge_listener.events.tournament;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentStartAtChangedEvent extends GenericTournamentChangedEvent {
	private final OffsetDateTime startAt;
	private final OffsetDateTime previousStartAt;
	
	public TournamentStartAtChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final OffsetDateTime startAt, final OffsetDateTime previousStartAt) {
		super(tournament, previousTournament);
		this.startAt = startAt;
		this.previousStartAt = previousStartAt;
	}
	
	public OffsetDateTime getStartAt() {
		return this.startAt;
	}
	
	public OffsetDateTime getPreviousStartAt() {
		return this.previousStartAt;
	}
}
