package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentIdChangedEvent extends GenericTournamentChangedEvent {
	private final Long id;
	private final Long previousId;
	
	public TournamentIdChangedEvent(final Tournament tournament, final Tournament previousTournament, final Long id,
			final Long previousId) {
		super(tournament, previousTournament);
		this.id = id;
		this.previousId = previousId;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public Long getPreviousId() {
		return this.previousId;
	}
}
