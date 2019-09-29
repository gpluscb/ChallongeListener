package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantMatchRemovedEvent extends GenericParticipantChangedEvent {
	private final Match previousMatch;
	
	public ParticipantMatchRemovedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final Match previousMatch) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.previousMatch = previousMatch;
	}
	
	public Match getPreviousMatch() {
		return this.previousMatch;
	}
}
