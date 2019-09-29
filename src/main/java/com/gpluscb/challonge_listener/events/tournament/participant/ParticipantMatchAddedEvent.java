package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantMatchAddedEvent extends GenericParticipantChangedEvent {
	private final Match match;
	
	public ParticipantMatchAddedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final Match match) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.match = match;
	}
	
	public Match getMatch() {
		return this.match;
	}
}
