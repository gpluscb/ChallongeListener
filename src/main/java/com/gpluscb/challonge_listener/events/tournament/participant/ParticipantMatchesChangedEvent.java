package com.gpluscb.challonge_listener.events.tournament.participant;

import java.util.List;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantMatchesChangedEvent extends GenericParticipantChangedEvent {
	private final List<Match> matches;
	private final List<Match> previousMatches;
	
	public ParticipantMatchesChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final List<Match> matches,
			final List<Match> previousMatches) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.matches = matches;
		this.previousMatches = previousMatches;
	}
	
	public List<Match> getMatches() {
		return this.matches;
	}
	
	public List<Match> getPreviousMatches() {
		return this.previousMatches;
	}
}
