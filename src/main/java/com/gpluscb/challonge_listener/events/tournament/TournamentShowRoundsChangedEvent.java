package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentShowRoundsChangedEvent extends GenericTournamentChangedEvent {
	private final Boolean showRounds;
	private final Boolean previousShowRounds;
	
	public TournamentShowRoundsChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Boolean showRounds, final Boolean previousShowRounds) {
		super(tournament, previousTournament);
		this.showRounds = showRounds;
		this.previousShowRounds = previousShowRounds;
	}
	
	public Boolean getShowRounds() {
		return this.showRounds;
	}
	
	public Boolean getPreviousShowRounds() {
		return this.previousShowRounds;
	}
}
