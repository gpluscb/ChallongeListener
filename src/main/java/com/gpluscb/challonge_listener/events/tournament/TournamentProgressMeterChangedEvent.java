package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentProgressMeterChangedEvent extends GenericTournamentChangedEvent {
	private Integer progressMeter;
	private Integer previousProgressMeter;
	
	public TournamentProgressMeterChangedEvent(Tournament tournament, Tournament previousTournament,
			Integer progressMeter, Integer previousProgressMeter) {
		super(tournament, previousTournament);
		this.progressMeter = progressMeter;
		this.previousProgressMeter = previousProgressMeter;
	}
	
	public Integer getProgressMeter() {
		return progressMeter;
	}
	
	public Integer getPreviousProgressMeter() {
		return previousProgressMeter;
	}
}
