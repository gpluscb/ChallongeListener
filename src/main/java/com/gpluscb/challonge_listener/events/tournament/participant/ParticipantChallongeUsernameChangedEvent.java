package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantChallongeUsernameChangedEvent extends GenericParticipantChangedEvent {
	private String challongeUsername;
	private String previousChallongeUsername;
	
	public ParticipantChallongeUsernameChangedEvent(Tournament tournament, Tournament previousTournament,
			Participant participant, Participant previousParticipant, String challongeUsername,
			String previousChallongeUsername) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.challongeUsername = challongeUsername;
		this.previousChallongeUsername = previousChallongeUsername;
	}
	
	public String getChallongeUsername() {
		return challongeUsername;
	}
	
	public String getPreviousChallongeUsername() {
		return previousChallongeUsername;
	}
}
