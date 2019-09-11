package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantActiveChangedEvent extends GenericParticipantChangedEvent {
	private Boolean active;
	private Boolean previousActive;
	
	public ParticipantActiveChangedEvent(Tournament tournament, Tournament previousTournament, Participant participant,
			Participant previousParticipant, Boolean active, Boolean previousActive) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.active = active;
		this.previousActive = previousActive;
	}
	
	public Boolean getActive() {
		return active;
	}
	
	public Boolean getPreviousActive() {
		return previousActive;
	}
}
