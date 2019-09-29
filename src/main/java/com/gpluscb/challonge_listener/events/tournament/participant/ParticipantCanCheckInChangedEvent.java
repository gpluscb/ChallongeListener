package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantCanCheckInChangedEvent extends GenericParticipantChangedEvent {
	private final Boolean canCheckIn;
	private final Boolean previousCanCheckIn;
	
	public ParticipantCanCheckInChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final Boolean canCheckIn,
			final Boolean previousCanCheckIn) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.canCheckIn = canCheckIn;
		this.previousCanCheckIn = previousCanCheckIn;
	}
	
	public Boolean getCanCheckIn() {
		return this.canCheckIn;
	}
	
	public Boolean getPreviousCanCheckIn() {
		return this.previousCanCheckIn;
	}
}
