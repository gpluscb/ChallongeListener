package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantIdChangedEvent extends GenericParticipantChangedEvent {
	private Long id;
	private Long previousId;
	
	public ParticipantIdChangedEvent(Tournament tournament, Tournament previousTournament, Participant participant,
			Participant previousParticipant, Long id, Long previousId) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.id = id;
		this.previousId = previousId;
	}
	
	public Long getId() {
		return id;
	}
	
	public Long getPreviousId() {
		return previousId;
	}
}
