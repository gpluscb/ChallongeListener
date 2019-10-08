package com.github.gpluscb.challonge_listener.events.tournament;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentStartedCheckingInAtChangedEvent extends GenericTournamentChangedEvent {
	private final OffsetDateTime startedCheckingInAt;
	private final OffsetDateTime previousStartedCheckingInAt;
	
	public TournamentStartedCheckingInAtChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final OffsetDateTime startedCheckingInAt, final OffsetDateTime previousStartedCheckingInAt) {
		super(tournament, previousTournament);
		this.startedCheckingInAt = startedCheckingInAt;
		this.previousStartedCheckingInAt = previousStartedCheckingInAt;
	}
	
	public OffsetDateTime getStartedCheckingInAt() {
		return this.startedCheckingInAt;
	}
	
	public OffsetDateTime getPreviousStartedCheckingInAt() {
		return this.previousStartedCheckingInAt;
	}
}
