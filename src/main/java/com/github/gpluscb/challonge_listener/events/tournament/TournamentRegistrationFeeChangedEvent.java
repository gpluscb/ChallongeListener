package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentRegistrationFeeChangedEvent extends GenericTournamentChangedEvent {
	private final Float registrationFee;
	private final Float previousRegistrationFee;
	
	public TournamentRegistrationFeeChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Float registrationFee, final Float previousRegistrationFee) {
		super(tournament, previousTournament);
		this.registrationFee = registrationFee;
		this.previousRegistrationFee = previousRegistrationFee;
	}
	
	public Float getRegistrationFee() {
		return this.registrationFee;
	}
	
	public Float getPreviousRegistrationFee() {
		return this.previousRegistrationFee;
	}
}
