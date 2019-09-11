package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantMiscChangedEvent extends GenericParticipantChangedEvent {
	private String misc;
	private String previousMisc;
	
	public ParticipantMiscChangedEvent(Tournament tournament, Tournament previousTournament, Participant participant,
			Participant previousParticipant, String misc, String previousMisc) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.misc = misc;
		this.previousMisc = previousMisc;
	}
	
	public String getMisc() {
		return misc;
	}
	
	public String getPreviousMisc() {
		return previousMisc;
	}
}
