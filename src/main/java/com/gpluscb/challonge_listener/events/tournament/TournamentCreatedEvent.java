package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentCreatedEvent extends GenericTournamentEvent {
	public TournamentCreatedEvent(Tournament tournament) {
		super(tournament);
	}
}
