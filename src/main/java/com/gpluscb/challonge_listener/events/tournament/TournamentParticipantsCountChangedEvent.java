package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentParticipantsCountChangedEvent extends GenericTournamentChangedEvent {
	private Integer participantsCount;
	private Integer previousParticipantsCount;
	
	public TournamentParticipantsCountChangedEvent(Tournament tournament, Tournament previousTournament,
			Integer participantsCount, Integer previousParticipantsCount) {
		super(tournament, previousTournament);
		this.participantsCount = participantsCount;
		this.previousParticipantsCount = previousParticipantsCount;
	}
	
	public Integer getParticipantsCount() {
		return participantsCount;
	}
	
	public Integer getPreviousParticipantsCount() {
		return previousParticipantsCount;
	}
}
