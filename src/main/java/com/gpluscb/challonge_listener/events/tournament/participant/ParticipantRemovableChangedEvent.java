package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantRemovableChangedEvent extends GenericParticipantChangedEvent {
	private final Boolean removable;
	private final Boolean previousRemovable;
	
	public ParticipantRemovableChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final Boolean removable,
			final Boolean previousRemovable) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.removable = removable;
		this.previousRemovable = previousRemovable;
	}
	
	public Boolean getRemovable() {
		return this.removable;
	}
	
	public Boolean getPreviousRemovable() {
		return this.previousRemovable;
	}
}
