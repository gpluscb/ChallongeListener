package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantCanCheckInChangedEvent extends GenericParticipantChangedEvent {
	private Boolean canCheckIn;
	private Boolean previousCanCheckIn;
	
	public ParticipantCanCheckInChangedEvent(Tournament tournament, Tournament previousTournament,
			Participant participant, Participant previousParticipant, Boolean canCheckIn, Boolean previousCanCheckIn) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.canCheckIn = canCheckIn;
		this.previousCanCheckIn = previousCanCheckIn;
	}
	
	public Boolean getCanCheckIn() {
		return canCheckIn;
	}
	
	public Boolean getPreviousCanCheckIn() {
		return previousCanCheckIn;
	}
}
