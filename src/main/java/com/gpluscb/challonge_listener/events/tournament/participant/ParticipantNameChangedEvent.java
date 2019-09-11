package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantNameChangedEvent extends GenericParticipantChangedEvent {
	private String name;
	private String previousName;
	
	public ParticipantNameChangedEvent(Tournament tournament, Tournament previousTournament, Participant participant,
			Participant previousParticipant, String name, String previousName) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.name = name;
		this.previousName = previousName;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPreviousName() {
		return previousName;
	}
}
