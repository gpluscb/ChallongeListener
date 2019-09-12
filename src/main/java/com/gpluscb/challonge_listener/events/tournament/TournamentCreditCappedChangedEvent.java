package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentCreditCappedChangedEvent extends GenericTournamentChangedEvent {
	private Boolean creditCapped;
	private Boolean previousCreditCapped;
	
	public TournamentCreditCappedChangedEvent(Tournament tournament, Tournament previousTournament,
			Boolean creditCapped, Boolean previousCreditCapped) {
		super(tournament, previousTournament);
		this.creditCapped = creditCapped;
		this.previousCreditCapped = previousCreditCapped;
	}
	
	public Boolean getCreditCapped() {
		return creditCapped;
	}
	
	public Boolean getPreviousCreditCapped() {
		return previousCreditCapped;
	}
}
