package com.gpluscb.challonge_listener.events.tournament.participant;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantCheckedInAtChangedEvent extends GenericParticipantChangedEvent {
	private final OffsetDateTime checkedInAt;
	private final OffsetDateTime previousCheckedInAt;
	
	public ParticipantCheckedInAtChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final OffsetDateTime checkedInAt,
			final OffsetDateTime previousCheckedInAt) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.checkedInAt = checkedInAt;
		this.previousCheckedInAt = previousCheckedInAt;
	}
	
	public OffsetDateTime getCheckedInAt() {
		return this.checkedInAt;
	}
	
	public OffsetDateTime getPreviousCheckedInAt() {
		return this.previousCheckedInAt;
	}
}
