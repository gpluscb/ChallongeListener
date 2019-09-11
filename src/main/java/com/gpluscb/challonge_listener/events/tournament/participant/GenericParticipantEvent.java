package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;
import com.gpluscb.challonge_listener.events.tournament.GenericTournamentChangedEvent;

public abstract class GenericParticipantEvent extends GenericTournamentChangedEvent {
	private Participant participant;
	
	public GenericParticipantEvent(Tournament tournament, Tournament previousTournament, Participant participant) {
		super(tournament, previousTournament);
		this.participant = participant;
	}
	
	public Participant getParticipant() {
		return participant;
	}
}
