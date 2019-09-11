package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public abstract class GenericParticipantChangedEvent extends GenericParticipantEvent {
	private Participant previousParticipant;
	
	public GenericParticipantChangedEvent(Tournament tournament, Tournament previousTournament, Participant participant,
			Participant previousParticipant) {
		super(tournament, previousTournament, participant);
		this.previousParticipant = previousParticipant;
	}
	
	public Participant getPreviousParticipant() {
		return previousParticipant;
	}
}
