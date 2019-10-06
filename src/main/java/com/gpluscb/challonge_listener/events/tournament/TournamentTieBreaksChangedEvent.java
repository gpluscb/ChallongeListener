package com.gpluscb.challonge_listener.events.tournament;

import java.util.List;

import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.enumeration.TieBreak;

public class TournamentTieBreaksChangedEvent extends GenericTournamentChangedEvent {
	private final List<TieBreak> tieBreaks;
	private final List<TieBreak> previousTieBreaks;
	
	public TournamentTieBreaksChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final List<TieBreak> tieBreaks, final List<TieBreak> previousTieBreaks) {
		super(tournament, previousTournament);
		this.tieBreaks = tieBreaks;
		this.previousTieBreaks = previousTieBreaks;
	}
	
	public List<TieBreak> getTieBreaks() {
		return this.tieBreaks;
	}
	
	public List<TieBreak> getPreviousTieBreaks() {
		return this.previousTieBreaks;
	}
}
