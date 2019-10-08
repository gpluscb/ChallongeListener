package com.github.gpluscb.challonge_listener.events.tournament;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentLockedAtChangedEvent extends GenericTournamentChangedEvent {
	private final OffsetDateTime lockedAt;
	private final OffsetDateTime previousLockedAt;
	
	public TournamentLockedAtChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final OffsetDateTime lockedAt, final OffsetDateTime previousLockedAt) {
		super(tournament, previousTournament);
		this.lockedAt = lockedAt;
		this.previousLockedAt = previousLockedAt;
	}
	
	public OffsetDateTime getLockedAt() {
		return this.lockedAt;
	}
	
	public OffsetDateTime getPreviousLockedAt() {
		return this.previousLockedAt;
	}
}
