package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentParticipantsSwappableChangedEvent extends GenericTournamentChangedEvent {
	private final Boolean participantsSwappable;
	private final Boolean previousParticipantsSwappable;
	
	public TournamentParticipantsSwappableChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Boolean participantsSwappable, final Boolean previousParticipantsSwappable) {
		super(tournament, previousTournament);
		this.participantsSwappable = participantsSwappable;
		this.previousParticipantsSwappable = previousParticipantsSwappable;
	}
	
	public Boolean getParticipantsSwappable() {
		return this.participantsSwappable;
	}
	
	public Boolean getPreviousParticipantsSwappable() {
		return this.previousParticipantsSwappable;
	}
}
