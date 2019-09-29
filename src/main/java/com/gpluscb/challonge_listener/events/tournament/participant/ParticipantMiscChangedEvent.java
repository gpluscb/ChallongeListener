package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantMiscChangedEvent extends GenericParticipantChangedEvent {
	private final String misc;
	private final String previousMisc;
	
	public ParticipantMiscChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final String misc,
			final String previousMisc) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.misc = misc;
		this.previousMisc = previousMisc;
	}
	
	public String getMisc() {
		return this.misc;
	}
	
	public String getPreviousMisc() {
		return this.previousMisc;
	}
}
