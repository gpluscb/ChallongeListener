package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantInviteEmailChangedEvent extends GenericParticipantChangedEvent {
	private String inviteEmail;
	private String previousInviteEmail;
	
	public ParticipantInviteEmailChangedEvent(Tournament tournament, Tournament previousTournament,
			Participant participant, Participant previousParticipant, String inviteEmail, String previousInviteEmail) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.inviteEmail = inviteEmail;
		this.previousInviteEmail = previousInviteEmail;
	}
	
	public String getInviteEmail() {
		return inviteEmail;
	}
	
	public String getPreviousInviteEmail() {
		return previousInviteEmail;
	}
}
