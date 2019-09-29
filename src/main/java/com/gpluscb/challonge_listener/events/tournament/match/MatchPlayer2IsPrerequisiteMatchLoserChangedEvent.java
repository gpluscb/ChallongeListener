package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchPlayer2IsPrerequisiteMatchLoserChangedEvent extends GenericMatchChangedEvent {
	private final Boolean player2IsPrerequisiteMatchLoser;
	private final Boolean previousPlayer2IsPrerequisiteMatchLoser;
	
	public MatchPlayer2IsPrerequisiteMatchLoserChangedEvent(final Tournament tournament,
			final Tournament previousTournament, final Match match, final Match previousMatch,
			final Boolean player2IsPrerequisiteMatchLoser, final Boolean previousPlayer2IsPrerequisiteMatchLoser) {
		super(tournament, previousTournament, match, previousMatch);
		this.player2IsPrerequisiteMatchLoser = player2IsPrerequisiteMatchLoser;
		this.previousPlayer2IsPrerequisiteMatchLoser = previousPlayer2IsPrerequisiteMatchLoser;
	}
	
	public Boolean getPlayer2IsPrerequisiteMatchLoser() {
		return this.player2IsPrerequisiteMatchLoser;
	}
	
	public Boolean getPreviousPlayer2IsPrerequisiteMatchLoser() {
		return this.previousPlayer2IsPrerequisiteMatchLoser;
	}
}
