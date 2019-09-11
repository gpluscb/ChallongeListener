package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantConfirmRemoveChangedEvent extends GenericParticipantChangedEvent {
	private Boolean confirmRemove;
	private Boolean previousConfirmRemove;
	
	public ParticipantConfirmRemoveChangedEvent(Tournament tournament, Tournament previousTournament, Participant participant,
			Participant previousParticipant, Boolean confirmRemove, Boolean previousConfirmRemove) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.confirmRemove = confirmRemove;
		this.previousConfirmRemove = previousConfirmRemove;
	}
	
	public Boolean getConfirmRemove() {
		return confirmRemove;
	}
	
	public Boolean getPreviousConfirmRemove() {
		return previousConfirmRemove;
	}
}
