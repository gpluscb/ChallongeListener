package com.gpluscb.challonge_listener.events.tournament;

import java.util.List;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class TournamentMatchesChangedEvent extends GenericTournamentChangedEvent {
	private final List<Match> matches;
	private final List<Match> previousMatches;
	
	public TournamentMatchesChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final List<Match> matches, final List<Match> previousMatches) {
		super(tournament, previousTournament);
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
