package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantGroupIdChangedEvent extends GenericParticipantChangedEvent {
	private final Long groupGroupId;
	private final Long previousGroupId;
	
	public ParticipantGroupIdChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final Long groupGroupId,
			final Long previousGroupId) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.groupGroupId = groupGroupId;
		this.previousGroupId = previousGroupId;
	}
	
	public Long getGroupId() {
		return this.groupGroupId;
	}
	
	public Long getPreviousGroupId() {
		return this.previousGroupId;
	}
}
