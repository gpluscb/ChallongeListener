package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantMatchRemovedEvent extends GenericParticipantChangedEvent {
	private Match previousMatch;

	public ParticipantMatchRemovedEvent(Tournament tournament, Tournament previousTournament, Participant participant,
			Participant previousParticipant, Match previousMatch) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.previousMatch = previousMatch;
	}
	
	public Match getPreviousMatch() {
		return previousMatch;
	}
}
