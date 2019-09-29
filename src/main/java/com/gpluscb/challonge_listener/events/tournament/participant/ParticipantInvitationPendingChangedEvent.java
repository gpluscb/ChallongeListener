package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantInvitationPendingChangedEvent extends GenericParticipantChangedEvent {
	private final Boolean invitationPending;
	private final Boolean previousInvitationPending;
	
	public ParticipantInvitationPendingChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final Boolean invitationPending,
			final Boolean previousInvitationPending) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.invitationPending = invitationPending;
		this.previousInvitationPending = previousInvitationPending;
	}
	
	public Boolean getInvitationPending() {
		return this.invitationPending;
	}
	
	public Boolean getPreviousInvitationPending() {
		return this.previousInvitationPending;
	}
}
