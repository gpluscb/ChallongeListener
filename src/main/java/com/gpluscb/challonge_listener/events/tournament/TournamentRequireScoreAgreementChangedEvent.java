package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentRequireScoreAgreementChangedEvent extends GenericTournamentChangedEvent {
	private Boolean requireScoreAgreement;
	private Boolean previousRequireScoreAgreement;
	
	public TournamentRequireScoreAgreementChangedEvent(Tournament tournament, Tournament previousTournament, Boolean requireScoreAgreement, Boolean previousRequireScoreAgreement) {
		super(tournament, previousTournament);
		this.requireScoreAgreement = requireScoreAgreement;
		this.previousRequireScoreAgreement = previousRequireScoreAgreement;
	}
	
	public Boolean getRequireScoreAgreement() {
		return requireScoreAgreement;
	}
	
	public Boolean getPreviousRequireScoreAgreement() {
		return previousRequireScoreAgreement;
	}
}
