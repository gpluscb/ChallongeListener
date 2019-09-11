package com.gpluscb.challonge_listener.events.tournament.participant;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantUpdatedAtChangedEvent extends GenericParticipantChangedEvent {
	private OffsetDateTime updatedAt;
	private OffsetDateTime previousUpdatedAt;
	
	public ParticipantUpdatedAtChangedEvent(Tournament tournament, Tournament previousTournament, Participant participant,
			Participant previousParticipant, OffsetDateTime updatedAt, OffsetDateTime previousUpdatedAt) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.updatedAt = updatedAt;
		this.previousUpdatedAt = previousUpdatedAt;
	}
	
	public OffsetDateTime getUpdatedAt() {
		return updatedAt;
	}
	
	public OffsetDateTime getPreviousUpdatedAt() {
		return previousUpdatedAt;
	}
}
