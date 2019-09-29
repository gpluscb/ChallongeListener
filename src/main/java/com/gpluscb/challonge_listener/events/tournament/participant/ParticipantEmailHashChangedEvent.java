package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantEmailHashChangedEvent extends GenericParticipantChangedEvent {
	private final String emailHash;
	private final String previousEmailHash;
	
	public ParticipantEmailHashChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final String emailHash,
			final String previousEmailHash) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.emailHash = emailHash;
		this.previousEmailHash = previousEmailHash;
	}
	
	public String getEmailHash() {
		return this.emailHash;
	}
	
	public String getPreviousEmailHash() {
		return this.previousEmailHash;
	}
}
