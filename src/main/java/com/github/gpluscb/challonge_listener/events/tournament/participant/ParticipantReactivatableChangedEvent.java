package com.github.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantReactivatableChangedEvent extends GenericParticipantChangedEvent {
	private final Boolean reactivatable;
	private final Boolean previousReactivatable;
	
	public ParticipantReactivatableChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final Boolean reactivatable,
			final Boolean previousReactivatable) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.reactivatable = reactivatable;
		this.previousReactivatable = previousReactivatable;
	}
	
	public Boolean getReactivatable() {
		return this.reactivatable;
	}
	
	public Boolean getPreviousReactivatable() {
		return this.previousReactivatable;
	}
}
