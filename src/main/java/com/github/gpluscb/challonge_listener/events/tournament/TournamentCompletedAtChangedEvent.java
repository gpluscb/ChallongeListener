package com.github.gpluscb.challonge_listener.events.tournament;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentCompletedAtChangedEvent extends GenericTournamentChangedEvent {
	private final OffsetDateTime completedAt;
	private final OffsetDateTime previousCompletedAt;
	
	public TournamentCompletedAtChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final OffsetDateTime completedAt, final OffsetDateTime previousCompletedAt) {
		super(tournament, previousTournament);
		this.completedAt = completedAt;
		this.previousCompletedAt = previousCompletedAt;
	}
	
	public OffsetDateTime getCompletedAt() {
		return this.completedAt;
	}
	
	public OffsetDateTime getPreviousCompletedAt() {
		return this.previousCompletedAt;
	}
}
