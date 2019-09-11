package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantEmailHashChangedEvent extends GenericParticipantChangedEvent {
	private String emailHash;
	private String previousEmailHash;
	
	public ParticipantEmailHashChangedEvent(Tournament tournament, Tournament previousTournament, Participant participant,
			Participant previousParticipant, String emailHash, String previousEmailHash) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.emailHash = emailHash;
		this.previousEmailHash = previousEmailHash;
	}
	
	public String getEmailHash() {
		return emailHash;
	}
	
	public String getPreviousEmailHash() {
		return previousEmailHash;
	}
}
