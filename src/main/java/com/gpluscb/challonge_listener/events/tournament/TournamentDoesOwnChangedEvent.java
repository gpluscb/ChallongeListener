package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentDoesOwnChangedEvent extends GenericTournamentChangedEvent {
	private final Boolean doesOwn;
	private final Boolean previousDoesOwn;
	
	public TournamentDoesOwnChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Boolean doesOwn, final Boolean previousDoesOwn) {
		super(tournament, previousTournament);
		this.doesOwn = doesOwn;
		this.previousDoesOwn = previousDoesOwn;
	}
	
	public Boolean getDoesOwn() {
		return this.doesOwn;
	}
	
	public Boolean getPreviousDoesOwn() {
		return this.previousDoesOwn;
	}
}
