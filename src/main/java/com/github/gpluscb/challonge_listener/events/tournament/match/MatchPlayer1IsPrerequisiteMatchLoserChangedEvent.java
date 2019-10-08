package com.github.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchPlayer1IsPrerequisiteMatchLoserChangedEvent extends GenericMatchChangedEvent {
	private final Boolean player1IsPrerequisiteMatchLoser;
	private final Boolean previousPlayer1IsPrerequisiteMatchLoser;
	
	public MatchPlayer1IsPrerequisiteMatchLoserChangedEvent(final Tournament tournament,
			final Tournament previousTournament, final Match match, final Match previousMatch,
			final Boolean player1IsPrerequisiteMatchLoser, final Boolean previousPlayer1IsPrerequisiteMatchLoser) {
		super(tournament, previousTournament, match, previousMatch);
		this.player1IsPrerequisiteMatchLoser = player1IsPrerequisiteMatchLoser;
		this.previousPlayer1IsPrerequisiteMatchLoser = previousPlayer1IsPrerequisiteMatchLoser;
	}
	
	public Boolean getPlayer1IsPrerequisiteMatchLoser() {
		return this.player1IsPrerequisiteMatchLoser;
	}
	
	public Boolean getPreviousPlayer1IsPrerequisiteMatchLoser() {
		return this.previousPlayer1IsPrerequisiteMatchLoser;
	}
}
