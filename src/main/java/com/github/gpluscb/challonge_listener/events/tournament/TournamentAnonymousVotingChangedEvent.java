package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentAnonymousVotingChangedEvent extends GenericTournamentChangedEvent {
	private final Boolean anonymousVoting;
	private final Boolean previousAnonymousVoting;
	
	public TournamentAnonymousVotingChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Boolean anonymousVoting, final Boolean previousAnonymousVoting) {
		super(tournament, previousTournament);
		this.anonymousVoting = anonymousVoting;
		this.previousAnonymousVoting = previousAnonymousVoting;
	}
	
	public Boolean getAnonymousVoting() {
		return this.anonymousVoting;
	}
	
	public Boolean getPreviousAnonymousVoting() {
		return this.previousAnonymousVoting;
	}
}
