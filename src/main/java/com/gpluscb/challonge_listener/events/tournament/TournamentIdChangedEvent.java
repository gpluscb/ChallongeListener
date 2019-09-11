package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentIdChangedEvent extends GenericTournamentChangedEvent {
	private Long id;
	private Long previousId;
	
	public TournamentIdChangedEvent(Tournament tournament, Tournament previousTournament, Long id, Long previousId) {
		super(tournament, previousTournament);
		this.id = id;
		this.previousId = previousId;
	}
	
	public Long getId() {
		return id;
	}
	
	public Long getPreviousId() {
		return previousId;
	}
}
