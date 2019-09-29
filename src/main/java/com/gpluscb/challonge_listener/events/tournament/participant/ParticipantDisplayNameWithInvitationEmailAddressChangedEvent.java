package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantDisplayNameWithInvitationEmailAddressChangedEvent extends GenericParticipantChangedEvent {
	private final String displayNameWithInvitationEmailAddress;
	private final String previousDisplayNameWithInvitationEmailAddress;
	
	public ParticipantDisplayNameWithInvitationEmailAddressChangedEvent(final Tournament tournament,
			final Tournament previousTournament, final Participant participant, final Participant previousParticipant,
			final String displayNameWithInvitationEmailAddress,
			final String previousDisplayNameWithInvitationEmailAddress) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.displayNameWithInvitationEmailAddress = displayNameWithInvitationEmailAddress;
		this.previousDisplayNameWithInvitationEmailAddress = previousDisplayNameWithInvitationEmailAddress;
	}
	
	public String getDisplayNameWithInvitationEmailAddress() {
		return this.displayNameWithInvitationEmailAddress;
	}
	
	public String getPreviousDisplayNameWithInvitationEmailAddress() {
		return this.previousDisplayNameWithInvitationEmailAddress;
	}
}
