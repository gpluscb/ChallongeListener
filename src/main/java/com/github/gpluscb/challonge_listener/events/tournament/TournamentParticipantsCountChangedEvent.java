package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentParticipantsCountChangedEvent extends GenericTournamentChangedEvent {
	private final Integer participantsCount;
	private final Integer previousParticipantsCount;
	
	public TournamentParticipantsCountChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Integer participantsCount, final Integer previousParticipantsCount) {
		super(tournament, previousTournament);
		this.participantsCount = participantsCount;
		this.previousParticipantsCount = previousParticipantsCount;
	}
	
	public Integer getParticipantsCount() {
		return this.participantsCount;
	}
	
	public Integer getPreviousParticipantsCount() {
		return this.previousParticipantsCount;
	}
}
