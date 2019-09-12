package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentAnonymousVotingChangedEvent extends GenericTournamentChangedEvent {
	private Boolean anonymousVoting;
	private Boolean previousAnonymousVoting;
	
	public TournamentAnonymousVotingChangedEvent(Tournament tournament, Tournament previousTournament,
			Boolean anonymousVoting, Boolean previousAnonymousVoting) {
		super(tournament, previousTournament);
		this.anonymousVoting = anonymousVoting;
		this.previousAnonymousVoting = previousAnonymousVoting;
	}
	
	public Boolean getAnonymousVoting() {
		return anonymousVoting;
	}
	
	public Boolean getPreviousAnonymousVoting() {
		return previousAnonymousVoting;
	}
}
