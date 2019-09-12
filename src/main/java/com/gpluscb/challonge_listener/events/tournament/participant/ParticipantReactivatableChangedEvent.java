package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantReactivatableChangedEvent extends GenericParticipantChangedEvent {
	private Boolean reactivatable;
	private Boolean previousReactivatable;
	
	public ParticipantReactivatableChangedEvent(Tournament tournament, Tournament previousTournament,
			Participant participant, Participant previousParticipant, Boolean reactivatable,
			Boolean previousReactivatable) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.reactivatable = reactivatable;
		this.previousReactivatable = previousReactivatable;
	}
	
	public Boolean getReactivatable() {
		return reactivatable;
	}
	
	public Boolean getPreviousReactivatable() {
		return previousReactivatable;
	}
}
