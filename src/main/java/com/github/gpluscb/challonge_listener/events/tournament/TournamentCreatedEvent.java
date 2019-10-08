package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentCreatedEvent extends GenericTournamentEvent {
	public TournamentCreatedEvent(final Tournament tournament) {
		super(tournament);
	}
}
