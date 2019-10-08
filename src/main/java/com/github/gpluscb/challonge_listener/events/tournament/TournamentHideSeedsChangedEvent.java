package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentHideSeedsChangedEvent extends GenericTournamentChangedEvent {
	private final Boolean hideSeeds;
	private final Boolean previousHideSeeds;
	
	public TournamentHideSeedsChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Boolean hideSeeds, final Boolean previousHideSeeds) {
		super(tournament, previousTournament);
		this.hideSeeds = hideSeeds;
		this.previousHideSeeds = previousHideSeeds;
	}
	
	public Boolean getHideSeeds() {
		return this.hideSeeds;
	}
	
	public Boolean getPreviousHideSeeds() {
		return this.previousHideSeeds;
	}
}
