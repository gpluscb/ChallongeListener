package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantSeedChangedEvent extends GenericParticipantChangedEvent {
	private Integer seed;
	private Integer previousSeed;
	
	public ParticipantSeedChangedEvent(Tournament tournament, Tournament previousTournament, Participant participant,
			Participant previousParticipant, Integer seed, Integer previousSeed) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.seed = seed;
		this.previousSeed = previousSeed;
	}
	
	public Integer getSeed() {
		return seed;
	}
	
	public Integer getPreviousSeed() {
		return previousSeed;
	}
}
