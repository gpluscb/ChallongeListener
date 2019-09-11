package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentHideForumChangedEvent extends GenericTournamentChangedEvent {
	private Boolean hideForum;
	private Boolean previousHideForum;
	
	public TournamentHideForumChangedEvent(Tournament tournament, Tournament previousTournament, Boolean hideForum, Boolean previousHideForum) {
		super(tournament, previousTournament);
		this.hideForum = hideForum;
		this.previousHideForum = previousHideForum;
	}
	
	public Boolean getHideForum() {
		return hideForum;
	}
	
	public Boolean getPreviousHideForum() {
		return previousHideForum;
	}
}
