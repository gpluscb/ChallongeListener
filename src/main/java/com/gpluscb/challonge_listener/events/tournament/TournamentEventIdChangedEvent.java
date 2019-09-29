package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentEventIdChangedEvent extends GenericTournamentChangedEvent {
	private final Long eventEventId;
	private final Long previousEventId;
	
	public TournamentEventIdChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Long eventEventId, final Long previousEventId) {
		super(tournament, previousTournament);
		this.eventEventId = eventEventId;
		this.previousEventId = previousEventId;
	}
	
	public Long getEventId() {
		return this.eventEventId;
	}
	
	public Long getPreviousEventId() {
		return this.previousEventId;
	}
}
