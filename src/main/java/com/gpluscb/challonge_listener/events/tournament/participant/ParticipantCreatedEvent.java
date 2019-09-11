package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantCreatedEvent extends GenericParticipantEvent {
	public ParticipantCreatedEvent(Tournament tournament, Tournament previousTournament, Participant participant) {
		super(tournament, previousTournament, participant);
	}
}
