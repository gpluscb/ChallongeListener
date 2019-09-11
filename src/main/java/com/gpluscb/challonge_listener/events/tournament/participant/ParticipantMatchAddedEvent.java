package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantMatchAddedEvent extends GenericParticipantChangedEvent {
	private Match match;
	
	public ParticipantMatchAddedEvent(Tournament tournament, Tournament previousTournament, Participant participant,
			Participant previousParticipant, Match match) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.match = match;
	}
	
	public Match getMatch() {
		return match;
	}
}
