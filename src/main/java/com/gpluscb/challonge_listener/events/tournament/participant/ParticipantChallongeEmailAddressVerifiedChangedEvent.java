package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantChallongeEmailAddressVerifiedChangedEvent extends GenericParticipantChangedEvent {
	private String challongeEmailAddressVerified;
	private String previousChallongeEmailAddressVerified;
	
	public ParticipantChallongeEmailAddressVerifiedChangedEvent(Tournament tournament, Tournament previousTournament,
			Participant participant, Participant previousParticipant, String challongeEmailAddressVerified,
			String previousChallongeEmailAddressVerified) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.challongeEmailAddressVerified = challongeEmailAddressVerified;
		this.previousChallongeEmailAddressVerified = previousChallongeEmailAddressVerified;
	}
	
	public String getChallongeEmailAddressVerified() {
		return challongeEmailAddressVerified;
	}
	
	public String getPreviousChallongeEmailAddressVerified() {
		return previousChallongeEmailAddressVerified;
	}
}
