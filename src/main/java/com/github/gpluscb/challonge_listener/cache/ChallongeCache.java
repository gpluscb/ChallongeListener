package com.github.gpluscb.challonge_listener.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import at.stefangeyer.challonge.model.Tournament;

/**
 * Saves the tournaments a
 * {@link com.github.gpluscb.challonge_listener.listener.ListenerManager
 * ListenerManager} manages in a cache. Use this to reduce api calls.
 */
public class ChallongeCache {
	private final List<TournamentCache> tournaments;
	
	/**
	 * Creates a new cache of the given tournaments.
	 * 
	 * @param tournaments
	 *            The tournaments the cache manages
	 */
	public ChallongeCache(final List<Tournament> tournaments) {
		this.tournaments = new ArrayList<>();
		
		for(final Tournament tournament : tournaments) {
			this.tournaments.add(new TournamentCache(this, tournament));
		}
	}
	
	/**
	 * Gets a tournament with the given id.
	 * 
	 * @param tournamentId
	 *            The id of the tournament
	 * @return The tournament with the given id or null if no such tournament
	 *             exists
	 */
	public TournamentCache getTournamentById(final long tournamentId) {
		for(final TournamentCache tournament : this.tournaments) {
			if(tournament.getTournament().getId().longValue() == tournamentId) {
				return tournament;
			}
		}
		return null;
	}
	
	/**
	 * Gets the managed {@link TournamentCache TournamentCaches}.
	 * 
	 * @return the managed tournament caches
	 */
	public List<TournamentCache> getTournaments() {
		return Collections.unmodifiableList(this.tournaments);
	}
	
	/**
	 * Updates this cache with the given tournaments.
	 * 
	 * @param tournaments
	 *            The new updated of tournaments
	 */
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
