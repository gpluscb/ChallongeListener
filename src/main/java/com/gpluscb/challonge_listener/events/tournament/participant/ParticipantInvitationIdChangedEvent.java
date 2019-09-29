package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantInvitationIdChangedEvent extends GenericParticipantChangedEvent {
	private final Long invitationInvitationId;
	private final Long previousInvitationId;
	
	public ParticipantInvitationIdChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final Long invitationInvitationId,
			final Long previousInvitationId) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.invitationInvitationId = invitationInvitationId;
		this.previousInvitationId = previousInvitationId;
	}
	
	public Long getInvitationId() {
		return this.invitationInvitationId;
	}
	
	public Long getPreviousInvitationId() {
		return this.previousInvitationId;
	}
}
