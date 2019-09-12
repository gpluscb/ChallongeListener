package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.enumeration.RankedBy;

public class TournamentRankedByChangedEvent extends GenericTournamentChangedEvent {
	private RankedBy rankedBy;
	private RankedBy previousRankedBy;
	
	public TournamentRankedByChangedEvent(Tournament tournament, Tournament previousTournament, RankedBy rankedBy,
			RankedBy previousRankedBy) {
		super(tournament, previousTournament);
		this.rankedBy = rankedBy;
		this.previousRankedBy = previousRankedBy;
	}
	
	public RankedBy getRankedBy() {
		return rankedBy;
	}
	
	public RankedBy getPreviousRankedBy() {
		return previousRankedBy;
	}
}
