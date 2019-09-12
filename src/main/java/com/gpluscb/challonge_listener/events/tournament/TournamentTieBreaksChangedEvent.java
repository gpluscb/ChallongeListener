package com.gpluscb.challonge_listener.events.tournament;

import java.util.List;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentTieBreaksChangedEvent extends GenericTournamentChangedEvent {
	private List<String> tieBreaks;
	private List<String> previousTieBreaks;
	
	public TournamentTieBreaksChangedEvent(Tournament tournament, Tournament previousTournament, List<String> tieBreaks,
			List<String> previousTieBreaks) {
		super(tournament, previousTournament);
		this.tieBreaks = tieBreaks;
		this.previousTieBreaks = previousTieBreaks;
	}
	
	public List<String> getTieBreaks() {
		return tieBreaks;
	}
	
	public List<String> getPreviousTieBreaks() {
		return previousTieBreaks;
	}
}
