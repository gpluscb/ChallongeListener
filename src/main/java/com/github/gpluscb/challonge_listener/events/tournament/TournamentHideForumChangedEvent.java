package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentHideForumChangedEvent extends GenericTournamentChangedEvent {
	private final Boolean hideForum;
	private final Boolean previousHideForum;
	
	public TournamentHideForumChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Boolean hideForum, final Boolean previousHideForum) {
		super(tournament, previousTournament);
		this.hideForum = hideForum;
		this.previousHideForum = previousHideForum;
	}
	
	public Boolean getHideForum() {
		return this.hideForum;
	}
	
	public Boolean getPreviousHideForum() {
		return this.previousHideForum;
	}
}
