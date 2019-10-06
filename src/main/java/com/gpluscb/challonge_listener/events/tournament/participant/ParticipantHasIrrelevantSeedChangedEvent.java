package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantHasIrrelevantSeedChangedEvent extends GenericParticipantChangedEvent {
	private final Boolean hasIrrelevantSeed;
	private final Boolean previousHasIrrelevantSeed;
	
	public ParticipantHasIrrelevantSeedChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final Boolean hasIrrelevantSeed,
			final Boolean previousHasIrrelevantSeed) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.hasIrrelevantSeed = hasIrrelevantSeed;
		this.previousHasIrrelevantSeed = previousHasIrrelevantSeed;
	}
	
	public Boolean getHasIrrelevantSeed() {
		return this.hasIrrelevantSeed;
	}
	
	public Boolean getPreviousHasIrrelevantSeed() {
		return this.previousHasIrrelevantSeed;
	}
}
