package com.gpluscb.challonge_listener.events.tournament;

import java.util.List;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class TournamentParticipantsChangedEvent extends GenericTournamentChangedEvent {
	private List<Participant> participants;
	private List<Participant> previousParticipants;
	
	public TournamentParticipantsChangedEvent(Tournament tournament, Tournament previousTournament, List<Participant> participants, List<Participant> previousParticipants) {
		super(tournament, previousTournament);
		this.participants = participants;
		this.previousParticipants = previousParticipants;
	}
	
	public List<Participant> getParticipants() {
		return participants;
	}
	
	public List<Participant> getPreviousParticipants() {
		return previousParticipants;
	}
}
