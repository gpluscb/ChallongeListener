package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantFinalRankChangedEvent extends GenericParticipantChangedEvent {
	private Integer finalRank;
	private Integer previousFinalRank;
	
	public ParticipantFinalRankChangedEvent(Tournament tournament, Tournament previousTournament, Participant participant,
			Participant previousParticipant, Integer finalRank, Integer previousFinalRank) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.finalRank = finalRank;
		this.previousFinalRank = previousFinalRank;
	}
	
	public Integer getFinalRank() {
		return finalRank;
	}
	
	public Integer getPreviousFinalRank() {
		return previousFinalRank;
	}
}
