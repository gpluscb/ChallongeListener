package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantDisplayNameWithInvitationEmailAddressChangedEvent extends GenericParticipantChangedEvent {
	private String displayNameWithInvitationEmailAddress;
	private String previousDisplayNameWithInvitationEmailAddress;
	
	public ParticipantDisplayNameWithInvitationEmailAddressChangedEvent(Tournament tournament,
			Tournament previousTournament, Participant participant, Participant previousParticipant,
			String displayNameWithInvitationEmailAddress, String previousDisplayNameWithInvitationEmailAddress) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.displayNameWithInvitationEmailAddress = displayNameWithInvitationEmailAddress;
		this.previousDisplayNameWithInvitationEmailAddress = previousDisplayNameWithInvitationEmailAddress;
	}
	
	public String getDisplayNameWithInvitationEmailAddress() {
		return displayNameWithInvitationEmailAddress;
	}
	
	public String getPreviousDisplayNameWithInvitationEmailAddress() {
		return previousDisplayNameWithInvitationEmailAddress;
	}
}
