package com.gpluscb.challonge_listener.events.tournament.participant;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantCreatedAtChangedEvent extends GenericParticipantChangedEvent {
	private OffsetDateTime createdAt;
	private OffsetDateTime previousCreatedAt;
	
	public ParticipantCreatedAtChangedEvent(Tournament tournament, Tournament previousTournament,
			Participant participant, Participant previousParticipant, OffsetDateTime createdAt,
			OffsetDateTime previousCreatedAt) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.createdAt = createdAt;
		this.previousCreatedAt = previousCreatedAt;
	}
	
	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}
	
	public OffsetDateTime getPreviousCreatedAt() {
		return previousCreatedAt;
	}
}
