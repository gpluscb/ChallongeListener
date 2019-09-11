package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchPlayer1IsPrerequisiteMatchLoserChangedEvent extends GenericMatchChangedEvent {
	private Boolean player1IsPrerequisiteMatchLoser;
	private Boolean previousPlayer1IsPrerequisiteMatchLoser;
	
	public MatchPlayer1IsPrerequisiteMatchLoserChangedEvent(Tournament tournament, Tournament previousTournament,
			Match match, Match previousMatch, Boolean player1IsPrerequisiteMatchLoser,
			Boolean previousPlayer1IsPrerequisiteMatchLoser) {
		super(tournament, previousTournament, match, previousMatch);
		this.player1IsPrerequisiteMatchLoser = player1IsPrerequisiteMatchLoser;
		this.previousPlayer1IsPrerequisiteMatchLoser = previousPlayer1IsPrerequisiteMatchLoser;
	}
	
	public Boolean getPlayer1IsPrerequisiteMatchLoser() {
		return player1IsPrerequisiteMatchLoser;
	}
	
	public Boolean getPreviousPlayer1IsPrerequisiteMatchLoser() {
		return previousPlayer1IsPrerequisiteMatchLoser;
	}
}
