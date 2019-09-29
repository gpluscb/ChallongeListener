package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public abstract class GenericParticipantChangedEvent extends GenericParticipantEvent {
	private final Participant previousParticipant;
	
	public GenericParticipantChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant) {
		super(tournament, previousTournament, participant);
		this.previousParticipant = previousParticipant;
	}
	
	/**
	 * The participant before it changed.
	 * 
	 * @return the previous participant
	 */
	public Participant getPreviousParticipant() {
		return this.previousParticipant;
	}
}
