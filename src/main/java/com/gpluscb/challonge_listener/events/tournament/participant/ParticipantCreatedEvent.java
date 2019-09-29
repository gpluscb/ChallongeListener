package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantCreatedEvent extends GenericParticipantEvent {
	public ParticipantCreatedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant) {
		super(tournament, previousTournament, participant);
	}
}
