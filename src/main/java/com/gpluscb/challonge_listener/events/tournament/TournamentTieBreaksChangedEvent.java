package com.gpluscb.challonge_listener.events.tournament;

import java.util.List;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentTieBreaksChangedEvent extends GenericTournamentChangedEvent {
	private final List<String> tieBreaks;
	private final List<String> previousTieBreaks;
	
	public TournamentTieBreaksChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final List<String> tieBreaks, final List<String> previousTieBreaks) {
		super(tournament, previousTournament);
		this.tieBreaks = tieBreaks;
		this.previousTieBreaks = previousTieBreaks;
	}
	
	public List<String> getTieBreaks() {
		return this.tieBreaks;
	}
	
	public List<String> getPreviousTieBreaks() {
		return this.previousTieBreaks;
	}
}
