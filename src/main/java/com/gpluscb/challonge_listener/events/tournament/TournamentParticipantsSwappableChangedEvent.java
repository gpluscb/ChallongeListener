package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentParticipantsSwappableChangedEvent extends GenericTournamentChangedEvent {
	private Boolean participantsSwappable;
	private Boolean previousParticipantsSwappable;
	
	public TournamentParticipantsSwappableChangedEvent(Tournament tournament, Tournament previousTournament, Boolean participantsSwappable, Boolean previousParticipantsSwappable) {
		super(tournament, previousTournament);
		this.participantsSwappable = participantsSwappable;
		this.previousParticipantsSwappable = previousParticipantsSwappable;
	}
	
	public Boolean getParticipantsSwappable() {
		return participantsSwappable;
	}
	
	public Boolean getPreviousParticipantsSwappable() {
		return previousParticipantsSwappable;
	}
}
