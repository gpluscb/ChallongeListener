package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.enumeration.RankedBy;

public class TournamentRankedByChangedEvent extends GenericTournamentChangedEvent {
	private final RankedBy rankedBy;
	private final RankedBy previousRankedBy;
	
	public TournamentRankedByChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final RankedBy rankedBy, final RankedBy previousRankedBy) {
		super(tournament, previousTournament);
		this.rankedBy = rankedBy;
		this.previousRankedBy = previousRankedBy;
	}
	
	public RankedBy getRankedBy() {
		return this.rankedBy;
	}
	
	public RankedBy getPreviousRankedBy() {
		return this.previousRankedBy;
	}
}
