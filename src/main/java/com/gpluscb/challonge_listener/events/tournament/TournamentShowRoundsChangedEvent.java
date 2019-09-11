package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentShowRoundsChangedEvent extends GenericTournamentChangedEvent {
	private Boolean showRounds;
	private Boolean previousShowRounds;
	
	public TournamentShowRoundsChangedEvent(Tournament tournament, Tournament previousTournament, Boolean showRounds, Boolean previousShowRounds) {
		super(tournament, previousTournament);
		this.showRounds = showRounds;
		this.previousShowRounds = previousShowRounds;
	}
	
	public Boolean getShowRounds() {
		return showRounds;
	}
	
	public Boolean getPreviousShowRounds() {
		return previousShowRounds;
	}
}
