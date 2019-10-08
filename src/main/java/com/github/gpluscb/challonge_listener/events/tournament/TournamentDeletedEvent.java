package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentDeletedEvent extends GenericTournamentEvent {
	public TournamentDeletedEvent(final Tournament tournament) {
		super(tournament);
	}
}
