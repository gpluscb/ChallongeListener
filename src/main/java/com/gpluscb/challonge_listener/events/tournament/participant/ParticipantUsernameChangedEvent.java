package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantUsernameChangedEvent extends GenericParticipantChangedEvent {
	private String username;
	private String previousUsername;
	
	public ParticipantUsernameChangedEvent(Tournament tournament, Tournament previousTournament, Participant participant,
			Participant previousParticipant, String username, String previousUsername) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.username = username;
		this.previousUsername = previousUsername;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPreviousUsername() {
		return previousUsername;
	}
}
