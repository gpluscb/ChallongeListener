package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentRankedChangedEvent extends GenericTournamentChangedEvent {
	private final Boolean ranked;
	private final Boolean previousRanked;
	
	public TournamentRankedChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Boolean ranked, final Boolean previousRanked) {
		super(tournament, previousTournament);
		this.ranked = ranked;
		this.previousRanked = previousRanked;
	}
	
	public Boolean getRanked() {
		return this.ranked;
	}
	
	public Boolean getPreviousRanked() {
		return this.previousRanked;
	}
}
