package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantConfirmRemoveChangedEvent extends GenericParticipantChangedEvent {
	private final Boolean confirmRemove;
	private final Boolean previousConfirmRemove;
	
	public ParticipantConfirmRemoveChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final Boolean confirmRemove,
			final Boolean previousConfirmRemove) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.confirmRemove = confirmRemove;
		this.previousConfirmRemove = previousConfirmRemove;
	}
	
	public Boolean getConfirmRemove() {
		return this.confirmRemove;
	}
	
	public Boolean getPreviousConfirmRemove() {
		return this.previousConfirmRemove;
	}
}
