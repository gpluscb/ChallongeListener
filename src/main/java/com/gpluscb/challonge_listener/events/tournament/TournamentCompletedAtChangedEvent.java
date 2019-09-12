package com.gpluscb.challonge_listener.events.tournament;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentCompletedAtChangedEvent extends GenericTournamentChangedEvent {
	private OffsetDateTime completedAt;
	private OffsetDateTime previousCompletedAt;
	
	public TournamentCompletedAtChangedEvent(Tournament tournament, Tournament previousTournament,
			OffsetDateTime completedAt, OffsetDateTime previousCompletedAt) {
		super(tournament, previousTournament);
		this.completedAt = completedAt;
		this.previousCompletedAt = previousCompletedAt;
	}
	
	public OffsetDateTime getCompletedAt() {
		return completedAt;
	}
	
	public OffsetDateTime getPreviousCompletedAt() {
		return previousCompletedAt;
	}
}
