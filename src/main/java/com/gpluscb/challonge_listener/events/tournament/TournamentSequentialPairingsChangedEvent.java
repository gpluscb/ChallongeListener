package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentSequentialPairingsChangedEvent extends GenericTournamentChangedEvent {
	private Boolean sequentialPairings;
	private Boolean previousSequentialPairings;
	
	public TournamentSequentialPairingsChangedEvent(Tournament tournament, Tournament previousTournament,
			Boolean sequentialPairings, Boolean previousSequentialPairings) {
		super(tournament, previousTournament);
		this.sequentialPairings = sequentialPairings;
		this.previousSequentialPairings = previousSequentialPairings;
	}
	
	public Boolean getSequentialPairings() {
		return sequentialPairings;
	}
	
	public Boolean getPreviousSequentialPairings() {
		return previousSequentialPairings;
	}
}
