package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantChallongeEmailAddressVerifiedChangedEvent extends GenericParticipantChangedEvent {
	private final String challongeEmailAddressVerified;
	private final String previousChallongeEmailAddressVerified;
	
	public ParticipantChallongeEmailAddressVerifiedChangedEvent(final Tournament tournament,
			final Tournament previousTournament, final Participant participant, final Participant previousParticipant,
			final String challongeEmailAddressVerified, final String previousChallongeEmailAddressVerified) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.challongeEmailAddressVerified = challongeEmailAddressVerified;
		this.previousChallongeEmailAddressVerified = previousChallongeEmailAddressVerified;
	}
	
	public String getChallongeEmailAddressVerified() {
		return this.challongeEmailAddressVerified;
	}
	
	public String getPreviousChallongeEmailAddressVerified() {
		return this.previousChallongeEmailAddressVerified;
	}
}
