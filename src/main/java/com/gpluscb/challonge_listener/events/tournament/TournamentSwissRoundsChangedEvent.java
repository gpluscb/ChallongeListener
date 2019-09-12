package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentSwissRoundsChangedEvent extends GenericTournamentChangedEvent {
	private Integer swissRounds;
	private Integer previousSwissRounds;
	
	public TournamentSwissRoundsChangedEvent(Tournament tournament, Tournament previousTournament, Integer swissRounds,
			Integer previousSwissRounds) {
		super(tournament, previousTournament);
		this.swissRounds = swissRounds;
		this.previousSwissRounds = previousSwissRounds;
	}
	
	public Integer getSwissRounds() {
		return swissRounds;
	}
	
	public Integer getPreviousSwissRounds() {
		return previousSwissRounds;
	}
}
