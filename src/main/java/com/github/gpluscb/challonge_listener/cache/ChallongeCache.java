package com.github.gpluscb.challonge_listener.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import at.stefangeyer.challonge.model.Tournament;

public class ChallongeCache {
	private final List<TournamentCache> tournaments;
	
	public ChallongeCache(final List<Tournament> tournaments) {
		this.tournaments = new ArrayList<>();
		
		for(final Tournament tournament : tournaments) {
			this.tournaments.add(new TournamentCache(this, tournament));
		}
	}
	
	public TournamentCache getTournamentById(final long tournamentId) {
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
	
	public void update(final List<Tournament> tournaments) {
		final List<TournamentCache> notHandled = new ArrayList<>(this.tournaments);
		for(final Tournament tournament : tournaments) {
			final TournamentCache cache = getTournamentById(tournament.getId().longValue());
			if(cache == null) {
				// New tournament
				this.tournaments.add(new TournamentCache(this, tournament));
			} else {
				cache.update(tournament);
				
				// Now handled
				notHandled.remove(cache);
			}
		}
		// Not present in given tournaments
		for(final TournamentCache toDelete : notHandled) {
			this.tournaments.remove(toDelete);
		}
	}
}
