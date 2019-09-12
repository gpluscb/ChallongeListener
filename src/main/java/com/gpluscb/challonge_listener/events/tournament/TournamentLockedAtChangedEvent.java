package com.gpluscb.challonge_listener.events.tournament;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentLockedAtChangedEvent extends GenericTournamentChangedEvent {
	private OffsetDateTime lockedAt;
	private OffsetDateTime previousLockedAt;
	
	public TournamentLockedAtChangedEvent(Tournament tournament, Tournament previousTournament, OffsetDateTime lockedAt,
			OffsetDateTime previousLockedAt) {
		super(tournament, previousTournament);
		this.lockedAt = lockedAt;
		this.previousLockedAt = previousLockedAt;
	}
	
	public OffsetDateTime getLockedAt() {
		return lockedAt;
	}
	
	public OffsetDateTime getPreviousLockedAt() {
		return previousLockedAt;
	}
}
