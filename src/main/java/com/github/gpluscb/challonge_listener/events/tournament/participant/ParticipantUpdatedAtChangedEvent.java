package com.github.gpluscb.challonge_listener.events.tournament.participant;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantUpdatedAtChangedEvent extends GenericParticipantChangedEvent {
	private final OffsetDateTime updatedAt;
	private final OffsetDateTime previousUpdatedAt;
	
	public ParticipantUpdatedAtChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final OffsetDateTime updatedAt,
			final OffsetDateTime previousUpdatedAt) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.updatedAt = updatedAt;
		this.previousUpdatedAt = previousUpdatedAt;
	}
	
	public OffsetDateTime getUpdatedAt() {
		return this.updatedAt;
	}
	
	public OffsetDateTime getPreviousUpdatedAt() {
		return this.previousUpdatedAt;
	}
}
