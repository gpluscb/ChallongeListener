package com.gpluscb.challonge_listener.events.tournament.participant;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantCreatedAtChangedEvent extends GenericParticipantChangedEvent {
	private final OffsetDateTime createdAt;
	private final OffsetDateTime previousCreatedAt;
	
	public ParticipantCreatedAtChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final OffsetDateTime createdAt,
			final OffsetDateTime previousCreatedAt) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.createdAt = createdAt;
		this.previousCreatedAt = previousCreatedAt;
	}
	
	public OffsetDateTime getCreatedAt() {
		return this.createdAt;
	}
	
	public OffsetDateTime getPreviousCreatedAt() {
		return this.previousCreatedAt;
	}
}
