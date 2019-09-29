package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentQuickAdvanceChangedEvent extends GenericTournamentChangedEvent {
	private final Boolean quickAdvance;
	private final Boolean previousQuickAdvance;
	
	public TournamentQuickAdvanceChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Boolean quickAdvance, final Boolean previousQuickAdvance) {
		super(tournament, previousTournament);
		this.quickAdvance = quickAdvance;
		this.previousQuickAdvance = previousQuickAdvance;
	}
	
	public Boolean getQuickAdvance() {
		return this.quickAdvance;
	}
	
	public Boolean getPreviousQuickAdvance() {
		return this.previousQuickAdvance;
	}
}
