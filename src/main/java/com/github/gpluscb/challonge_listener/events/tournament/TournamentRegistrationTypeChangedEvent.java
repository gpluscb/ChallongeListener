package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentRegistrationTypeChangedEvent extends GenericTournamentChangedEvent {
	private final String registrationType;
	private final String previousRegistrationType;
	
	public TournamentRegistrationTypeChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final String registrationType, final String previousRegistrationType) {
		super(tournament, previousTournament);
		this.registrationType = registrationType;
		this.previousRegistrationType = previousRegistrationType;
	}
	
	public String getRegistrationType() {
		return this.registrationType;
	}
	
	public String getPreviousRegistrationType() {
		return this.previousRegistrationType;
	}
}
