package com.github.gpluscb.challonge_listener.cache;

import at.stefangeyer.challonge.model.Participant;

public class ParticipantCache {
	private final TournamentCache tournament;
	
	private Participant participant;
	
	ParticipantCache(final TournamentCache tournament, final Participant participant) {
		this.tournament = tournament;
		
		this.participant = participant;
	}
	
	public TournamentCache getTournament() {
		return this.tournament;
	}
	
	public Participant getParticipant() {
		return this.participant;
	}
	
	void update() {
		// TODO
	}
}
