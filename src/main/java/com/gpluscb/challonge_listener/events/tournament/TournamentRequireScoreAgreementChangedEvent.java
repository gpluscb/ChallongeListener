package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentRequireScoreAgreementChangedEvent extends GenericTournamentChangedEvent {
	private final Boolean requireScoreAgreement;
	private final Boolean previousRequireScoreAgreement;
	
	public TournamentRequireScoreAgreementChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Boolean requireScoreAgreement, final Boolean previousRequireScoreAgreement) {
		super(tournament, previousTournament);
		this.requireScoreAgreement = requireScoreAgreement;
		this.previousRequireScoreAgreement = previousRequireScoreAgreement;
	}
	
	public Boolean getRequireScoreAgreement() {
		return this.requireScoreAgreement;
	}
	
	public Boolean getPreviousRequireScoreAgreement() {
		return this.previousRequireScoreAgreement;
	}
}
