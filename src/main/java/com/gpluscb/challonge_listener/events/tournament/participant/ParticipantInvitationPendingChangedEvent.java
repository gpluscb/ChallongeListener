package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantInvitationPendingChangedEvent extends GenericParticipantChangedEvent {
	private Boolean invitationPending;
	private Boolean previousInvitationPending;
	
	public ParticipantInvitationPendingChangedEvent(Tournament tournament, Tournament previousTournament,
			Participant participant, Participant previousParticipant, Boolean invitationPending,
			Boolean previousInvitationPending) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.invitationPending = invitationPending;
		this.previousInvitationPending = previousInvitationPending;
	}
	
	public Boolean getInvitationPending() {
		return invitationPending;
	}
	
	public Boolean getPreviousInvitationPending() {
		return previousInvitationPending;
	}
}
