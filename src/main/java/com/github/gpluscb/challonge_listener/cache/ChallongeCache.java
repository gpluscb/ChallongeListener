package com.github.gpluscb.challonge_listener.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import at.stefangeyer.challonge.model.Tournament;

public class ChallongeCache {
	private List<TournamentCache> tournaments;
	
	public ChallongeCache(final List<Tournament> tournaments) {
		this.tournaments = new ArrayList<>();
		
		for(final Tournament tournament : tournaments) {
			this.tournaments.add(new TournamentCache(this, tournament));
		}
	}
	
	public TournamentCache getTournamentById(long tournamentId) {
		for(final TournamentCache tournament : this.tournaments) {
			if(tournament.getTournament().getId().longValue() == tournamentId) {
				return tournament;
			}
		}
		return null;
	}
	
	public List<TournamentCache> getTournaments() {
		return Collections.unmodifiableList(this.tournaments);
	}
	
	public void update() {
		// TODO
	}
}
