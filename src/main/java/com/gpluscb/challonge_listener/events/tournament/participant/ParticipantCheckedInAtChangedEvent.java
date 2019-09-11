package com.gpluscb.challonge_listener.events.tournament.participant;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantCheckedInAtChangedEvent extends GenericParticipantChangedEvent {
	private OffsetDateTime checkedInAt;
	private OffsetDateTime previousCheckedInAt;
	
	public ParticipantCheckedInAtChangedEvent(Tournament tournament, Tournament previousTournament, Participant participant,
			Participant previousParticipant, OffsetDateTime checkedInAt, OffsetDateTime previousCheckedInAt) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.checkedInAt = checkedInAt;
		this.previousCheckedInAt = previousCheckedInAt;
	}
	
	public OffsetDateTime getCheckedInAt() {
		return checkedInAt;
	}
	
	public OffsetDateTime getPreviousCheckedInAt() {
		return previousCheckedInAt;
	}
}
