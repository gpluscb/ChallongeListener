package com.gpluscb.challonge_listener.events.tournament.participant;

import java.util.List;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantMatchesChangedEvent extends GenericParticipantChangedEvent {
	private List<Match> matches;
	private List<Match> previousMatches;
	
	public ParticipantMatchesChangedEvent(Tournament tournament, Tournament previousTournament, Participant participant,
			Participant previousParticipant, List<Match> matches, List<Match> previousMatches) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.matches = matches;
		this.previousMatches = previousMatches;
	}
	
	public List<Match> getMatches() {
		return matches;
	}
	
	public List<Match> getPreviousMatches() {
		return previousMatches;
	}
}
