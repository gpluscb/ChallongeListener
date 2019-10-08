package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentCreditCappedChangedEvent extends GenericTournamentChangedEvent {
	private final Boolean creditCapped;
	private final Boolean previousCreditCapped;
	
	public TournamentCreditCappedChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Boolean creditCapped, final Boolean previousCreditCapped) {
		super(tournament, previousTournament);
		this.creditCapped = creditCapped;
		this.previousCreditCapped = previousCreditCapped;
	}
	
	public Boolean getCreditCapped() {
		return this.creditCapped;
	}
	
	public Boolean getPreviousCreditCapped() {
		return this.previousCreditCapped;
	}
}
