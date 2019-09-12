package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantCheckedInChangedEvent extends GenericParticipantChangedEvent {
	private Boolean checkedIn;
	private Boolean previousCheckedIn;
	
	public ParticipantCheckedInChangedEvent(Tournament tournament, Tournament previousTournament,
			Participant participant, Participant previousParticipant, Boolean checkedIn, Boolean previousCheckedIn) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.checkedIn = checkedIn;
		this.previousCheckedIn = previousCheckedIn;
	}
	
	public Boolean getCheckedIn() {
		return checkedIn;
	}
	
	public Boolean getPreviousCheckedIn() {
		return previousCheckedIn;
	}
}
