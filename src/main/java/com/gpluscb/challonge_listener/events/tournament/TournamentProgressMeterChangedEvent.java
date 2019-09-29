package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentProgressMeterChangedEvent extends GenericTournamentChangedEvent {
	private final Integer progressMeter;
	private final Integer previousProgressMeter;
	
	public TournamentProgressMeterChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Integer progressMeter, final Integer previousProgressMeter) {
		super(tournament, previousTournament);
		this.progressMeter = progressMeter;
		this.previousProgressMeter = previousProgressMeter;
	}
	
	public Integer getProgressMeter() {
		return this.progressMeter;
	}
	
	public Integer getPreviousProgressMeter() {
		return this.previousProgressMeter;
	}
}
