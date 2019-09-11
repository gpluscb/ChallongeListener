package com.gpluscb.challonge_listener.events.tournament;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentStartedCheckingInAtChangedEvent extends GenericTournamentChangedEvent {
	private OffsetDateTime startedCheckingInAt;
	private OffsetDateTime previousStartedCheckingInAt;
	
	public TournamentStartedCheckingInAtChangedEvent(Tournament tournament, Tournament previousTournament, OffsetDateTime startedCheckingInAt, OffsetDateTime previousStartedCheckingInAt) {
		super(tournament, previousTournament);
		this.startedCheckingInAt = startedCheckingInAt;
		this.previousStartedCheckingInAt = previousStartedCheckingInAt;
	}
	
	public OffsetDateTime getStartedCheckingInAt() {
		return startedCheckingInAt;
	}
	
	public OffsetDateTime getPreviousStartedCheckingInAt() {
		return previousStartedCheckingInAt;
	}
}
