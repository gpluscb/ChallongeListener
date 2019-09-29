package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantCheckedInChangedEvent extends GenericParticipantChangedEvent {
	private final Boolean checkedIn;
	private final Boolean previousCheckedIn;
	
	public ParticipantCheckedInChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final Boolean checkedIn,
			final Boolean previousCheckedIn) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.checkedIn = checkedIn;
		this.previousCheckedIn = previousCheckedIn;
	}
	
	public Boolean getCheckedIn() {
		return this.checkedIn;
	}
	
	public Boolean getPreviousCheckedIn() {
		return this.previousCheckedIn;
	}
}
