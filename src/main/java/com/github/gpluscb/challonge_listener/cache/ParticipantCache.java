package com.github.gpluscb.challonge_listener.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import at.stefangeyer.challonge.model.Participant;

public class ParticipantCache {
	private final TournamentCache tournament;
	
	private Participant participant;
	
	private final List<MatchCache> matches;
	
	ParticipantCache(final TournamentCache tournament, final Participant participant) {
		this.tournament = tournament;
		
		this.participant = participant;
		
		this.matches = new ArrayList<>();
		
		// TODO: Link
	}
	
	public TournamentCache getTournament() {
		return this.tournament;
	}
	
	public Participant getParticipant() {
		return this.participant;
	}
	
	public MatchCache getMatchById(final long matchId) {
		for(final MatchCache match : this.matches) {
			if(match.getMatch().getId().longValue() == matchId) {
				return match;
			}
		}
		return null;
	}
	
	public List<MatchCache> getMatches() {
		return Collections.unmodifiableList(this.matches);
	}
	
	void update(final Participant participant) {
		// TODO
	}
}
