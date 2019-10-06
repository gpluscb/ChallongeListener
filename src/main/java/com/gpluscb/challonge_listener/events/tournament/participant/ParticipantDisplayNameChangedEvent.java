package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantDisplayNameChangedEvent extends GenericParticipantChangedEvent {
	private final String displayName;
	private final String previousDisplayName;
	
	public ParticipantDisplayNameChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final String displayName,
			final String previousDisplayName) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.displayName = displayName;
		this.previousDisplayName = previousDisplayName;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
	
	public String getPreviousDisplayName() {
		return this.previousDisplayName;
	}
}
