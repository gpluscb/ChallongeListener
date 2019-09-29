package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantActiveChangedEvent extends GenericParticipantChangedEvent {
	private final Boolean active;
	private final Boolean previousActive;
	
	public ParticipantActiveChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final Boolean active,
			final Boolean previousActive) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.active = active;
		this.previousActive = previousActive;
	}
	
	public Boolean getActive() {
		return this.active;
	}
	
	public Boolean getPreviousActive() {
		return this.previousActive;
	}
}
