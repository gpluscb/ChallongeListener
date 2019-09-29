package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantFinalRankChangedEvent extends GenericParticipantChangedEvent {
	private final Integer finalRank;
	private final Integer previousFinalRank;
	
	public ParticipantFinalRankChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final Integer finalRank,
			final Integer previousFinalRank) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.finalRank = finalRank;
		this.previousFinalRank = previousFinalRank;
	}
	
	public Integer getFinalRank() {
		return this.finalRank;
	}
	
	public Integer getPreviousFinalRank() {
		return this.previousFinalRank;
	}
}
