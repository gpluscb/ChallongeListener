package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentSwissRoundsChangedEvent extends GenericTournamentChangedEvent {
	private final Integer swissRounds;
	private final Integer previousSwissRounds;
	
	public TournamentSwissRoundsChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Integer swissRounds, final Integer previousSwissRounds) {
		super(tournament, previousTournament);
		this.swissRounds = swissRounds;
		this.previousSwissRounds = previousSwissRounds;
	}
	
	public Integer getSwissRounds() {
		return this.swissRounds;
	}
	
	public Integer getPreviousSwissRounds() {
		return this.previousSwissRounds;
	}
}
