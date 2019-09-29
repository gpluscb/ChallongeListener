package com.gpluscb.challonge_listener.events.tournament.participant;

import com.gpluscb.challonge_listener.events.tournament.GenericTournamentChangedEvent;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public abstract class GenericParticipantEvent extends GenericTournamentChangedEvent {
	private final Participant participant;
	
	public GenericParticipantEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant) {
		super(tournament, previousTournament);
		this.participant = participant;
	}
	
	/**
	 * The participant primarily associated with this event.
	 * 
	 * @return The primary participant of this event
	 */
	public Participant getParticipant() {
		return this.participant;
	}
}
