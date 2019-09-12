package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentHideSeedsChangedEvent extends GenericTournamentChangedEvent {
	private Boolean hideSeeds;
	private Boolean previousHideSeeds;
	
	public TournamentHideSeedsChangedEvent(Tournament tournament, Tournament previousTournament, Boolean hideSeeds,
			Boolean previousHideSeeds) {
		super(tournament, previousTournament);
		this.hideSeeds = hideSeeds;
		this.previousHideSeeds = previousHideSeeds;
	}
	
	public Boolean getHideSeeds() {
		return hideSeeds;
	}
	
	public Boolean getPreviousHideSeeds() {
		return previousHideSeeds;
	}
}
