package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantInvitationIdChangedEvent extends GenericParticipantChangedEvent {
	private Long invitationInvitationId;
	private Long previousInvitationId;
	
	public ParticipantInvitationIdChangedEvent(Tournament tournament, Tournament previousTournament,
			Participant participant, Participant previousParticipant, Long invitationInvitationId,
			Long previousInvitationId) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.invitationInvitationId = invitationInvitationId;
		this.previousInvitationId = previousInvitationId;
	}
	
	public Long getInvitationId() {
		return invitationInvitationId;
	}
	
	public Long getPreviousInvitationId() {
		return previousInvitationId;
	}
}
