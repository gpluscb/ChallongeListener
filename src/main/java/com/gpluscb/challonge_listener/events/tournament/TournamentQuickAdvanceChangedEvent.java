package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentQuickAdvanceChangedEvent extends GenericTournamentChangedEvent {
	private Boolean quickAdvance;
	private Boolean previousQuickAdvance;
	
	public TournamentQuickAdvanceChangedEvent(Tournament tournament, Tournament previousTournament, Boolean quickAdvance, Boolean previousQuickAdvance) {
		super(tournament, previousTournament);
		this.quickAdvance = quickAdvance;
		this.previousQuickAdvance = previousQuickAdvance;
	}
	
	public Boolean getQuickAdvance() {
		return quickAdvance;
	}
	
	public Boolean getPreviousQuickAdvance() {
		return previousQuickAdvance;
	}
}
