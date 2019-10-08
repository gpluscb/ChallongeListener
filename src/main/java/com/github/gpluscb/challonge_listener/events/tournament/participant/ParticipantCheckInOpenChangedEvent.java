package com.github.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantCheckInOpenChangedEvent extends GenericParticipantChangedEvent {
	private final Boolean checkInOpen;
	private final Boolean previousCheckInOpen;
	
	public ParticipantCheckInOpenChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final Boolean checkInOpen,
			final Boolean previousCheckInOpen) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.checkInOpen = checkInOpen;
		this.previousCheckInOpen = previousCheckInOpen;
	}
	
	public Boolean getCheckInOpen() {
		return this.checkInOpen;
	}
	
	public Boolean getPreviousCheckInOpen() {
		return this.previousCheckInOpen;
	}
}
