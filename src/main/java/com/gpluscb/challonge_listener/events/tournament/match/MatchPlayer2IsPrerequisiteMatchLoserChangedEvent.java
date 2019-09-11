package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchPlayer2IsPrerequisiteMatchLoserChangedEvent extends GenericMatchChangedEvent {
	private Boolean player2IsPrerequisiteMatchLoser;
	private Boolean previousPlayer2IsPrerequisiteMatchLoser;
	
	public MatchPlayer2IsPrerequisiteMatchLoserChangedEvent(Tournament tournament, Tournament previousTournament,
			Match match, Match previousMatch, Boolean player2IsPrerequisiteMatchLoser,
			Boolean previousPlayer2IsPrerequisiteMatchLoser) {
		super(tournament, previousTournament, match, previousMatch);
		this.player2IsPrerequisiteMatchLoser = player2IsPrerequisiteMatchLoser;
		this.previousPlayer2IsPrerequisiteMatchLoser = previousPlayer2IsPrerequisiteMatchLoser;
	}
	
	public Boolean getPlayer2IsPrerequisiteMatchLoser() {
		return player2IsPrerequisiteMatchLoser;
	}
	
	public Boolean getPreviousPlayer2IsPrerequisiteMatchLoser() {
		return previousPlayer2IsPrerequisiteMatchLoser;
	}
}
