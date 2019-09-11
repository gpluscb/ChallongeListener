package com.gpluscb.challonge_listener.events.tournament;

import java.util.List;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class TournamentMatchesChangedEvent extends GenericTournamentChangedEvent {
	private List<Match> matches;
	private List<Match> previousMatches;
	
	public TournamentMatchesChangedEvent(Tournament tournament, Tournament previousTournament, List<Match> matches, List<Match> previousMatches) {
		super(tournament, previousTournament);
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
