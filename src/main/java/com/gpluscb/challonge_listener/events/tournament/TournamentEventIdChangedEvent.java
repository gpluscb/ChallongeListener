package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentEventIdChangedEvent extends GenericTournamentChangedEvent {
	private Long eventEventId;
	private Long previousEventId;
	
	public TournamentEventIdChangedEvent(Tournament tournament, Tournament previousTournament, Long eventEventId, Long previousEventId) {
		super(tournament, previousTournament);
		this.eventEventId = eventEventId;
		this.previousEventId = previousEventId;
	}
	
	public Long getEventId() {
		return eventEventId;
	}
	
	public Long getPreviousEventId() {
		return previousEventId;
	}
}
