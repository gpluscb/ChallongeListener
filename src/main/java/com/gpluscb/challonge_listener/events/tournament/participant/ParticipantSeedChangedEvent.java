package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantSeedChangedEvent extends GenericParticipantChangedEvent {
	private final Integer seed;
	private final Integer previousSeed;
	
	public ParticipantSeedChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final Integer seed,
			final Integer previousSeed) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.seed = seed;
		this.previousSeed = previousSeed;
	}
	
	public Integer getSeed() {
		return this.seed;
	}
	
	public Integer getPreviousSeed() {
		return this.previousSeed;
	}
}
