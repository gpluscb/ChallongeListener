package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentParticipantsLockedChangedEvent extends GenericTournamentChangedEvent {
	private Boolean participantsLocked;
	private Boolean previousParticipantsLocked;
	
	public TournamentParticipantsLockedChangedEvent(Tournament tournament, Tournament previousTournament,
			Boolean participantsLocked, Boolean previousParticipantsLocked) {
		super(tournament, previousTournament);
		this.participantsLocked = participantsLocked;
		this.previousParticipantsLocked = previousParticipantsLocked;
	}
	
	public Boolean getParticipantsLocked() {
		return participantsLocked;
	}
	
	public Boolean getPreviousParticipantsLocked() {
		return previousParticipantsLocked;
	}
}
