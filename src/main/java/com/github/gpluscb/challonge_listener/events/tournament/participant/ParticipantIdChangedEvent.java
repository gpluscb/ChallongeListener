package com.github.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantIdChangedEvent extends GenericParticipantChangedEvent {
	private final Long id;
	private final Long previousId;
	
	public ParticipantIdChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final Long id,
			final Long previousId) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.id = id;
		this.previousId = previousId;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public Long getPreviousId() {
		return this.previousId;
	}
}
