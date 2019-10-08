package com.github.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantNameChangedEvent extends GenericParticipantChangedEvent {
	private final String name;
	private final String previousName;
	
	public ParticipantNameChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final String name,
			final String previousName) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.name = name;
		this.previousName = previousName;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPreviousName() {
		return this.previousName;
	}
}
