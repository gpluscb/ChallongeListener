package com.github.gpluscb.challonge_listener.events.tournament;

import java.util.List;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class TournamentParticipantsChangedEvent extends GenericTournamentChangedEvent {
	private final List<Participant> participants;
	private final List<Participant> previousParticipants;
	
	public TournamentParticipantsChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final List<Participant> participants, final List<Participant> previousParticipants) {
		super(tournament, previousTournament);
		this.participants = participants;
		this.previousParticipants = previousParticipants;
	}
	
	public List<Participant> getParticipants() {
		return this.participants;
	}
	
	public List<Participant> getPreviousParticipants() {
		return this.previousParticipants;
	}
}
