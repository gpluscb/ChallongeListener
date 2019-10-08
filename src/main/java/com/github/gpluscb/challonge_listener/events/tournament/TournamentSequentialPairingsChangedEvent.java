package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentSequentialPairingsChangedEvent extends GenericTournamentChangedEvent {
	private final Boolean sequentialPairings;
	private final Boolean previousSequentialPairings;
	
	public TournamentSequentialPairingsChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Boolean sequentialPairings, final Boolean previousSequentialPairings) {
		super(tournament, previousTournament);
		this.sequentialPairings = sequentialPairings;
		this.previousSequentialPairings = previousSequentialPairings;
	}
	
	public Boolean getSequentialPairings() {
		return this.sequentialPairings;
	}
	
	public Boolean getPreviousSequentialPairings() {
		return this.previousSequentialPairings;
	}
}
