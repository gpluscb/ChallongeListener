package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantGroupIdChangedEvent extends GenericParticipantChangedEvent {
	private Long groupGroupId;
	private Long previousGroupId;
	
	public ParticipantGroupIdChangedEvent(Tournament tournament, Tournament previousTournament, Participant participant,
			Participant previousParticipant, Long groupGroupId, Long previousGroupId) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.groupGroupId = groupGroupId;
		this.previousGroupId = previousGroupId;
	}
	
	public Long getGroupId() {
		return groupGroupId;
	}
	
	public Long getPreviousGroupId() {
		return previousGroupId;
	}
}
