package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantInviteEmailChangedEvent extends GenericParticipantChangedEvent {
	private final String inviteEmail;
	private final String previousInviteEmail;
	
	public ParticipantInviteEmailChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final String inviteEmail,
			final String previousInviteEmail) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.inviteEmail = inviteEmail;
		this.previousInviteEmail = previousInviteEmail;
	}
	
	public String getInviteEmail() {
		return this.inviteEmail;
	}
	
	public String getPreviousInviteEmail() {
		return this.previousInviteEmail;
	}
}
