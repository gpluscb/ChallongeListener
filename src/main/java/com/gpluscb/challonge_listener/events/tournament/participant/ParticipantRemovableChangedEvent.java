package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantRemovableChangedEvent extends GenericParticipantChangedEvent {
	private Boolean removable;
	private Boolean previousRemovable;
	
	public ParticipantRemovableChangedEvent(Tournament tournament, Tournament previousTournament,
			Participant participant, Participant previousParticipant, Boolean removable, Boolean previousRemovable) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.removable = removable;
		this.previousRemovable = previousRemovable;
	}
	
	public Boolean getRemovable() {
		return removable;
	}
	
	public Boolean getPreviousRemovable() {
		return previousRemovable;
	}
}
