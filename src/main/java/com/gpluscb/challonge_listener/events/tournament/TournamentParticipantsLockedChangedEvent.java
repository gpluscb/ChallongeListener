package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentParticipantsLockedChangedEvent extends GenericTournamentChangedEvent {
	private final Boolean participantsLocked;
	private final Boolean previousParticipantsLocked;
	
	public TournamentParticipantsLockedChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Boolean participantsLocked, final Boolean previousParticipantsLocked) {
		super(tournament, previousTournament);
		this.participantsLocked = participantsLocked;
		this.previousParticipantsLocked = previousParticipantsLocked;
	}
	
	public Boolean getParticipantsLocked() {
		return this.participantsLocked;
	}
	
	public Boolean getPreviousParticipantsLocked() {
		return this.previousParticipantsLocked;
	}
}
