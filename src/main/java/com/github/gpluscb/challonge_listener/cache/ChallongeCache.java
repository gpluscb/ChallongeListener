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
public class ChallongeCache extends Cache<List<Tournament>> {
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
	 * @throws IllegalStateException
	 *             if the cache is invalid
	 */
	public TournamentCache getTournamentById(final long tournamentId) {
		checkValidity();
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
	 * @throws IllegalStateException
	 *             if the cache is invalid
	 */
	public List<TournamentCache> getTournaments() {
		checkValidity();
		return Collections.unmodifiableList(this.tournaments);
	}
	
	/**
	 * Adds the given tournament cache to this cache.
	 * 
	 * @param tournament
	 *            The tournament cache to manage
	 * @throws IllegalStateException
	 *             if the cache is invalid
	 */
	public void addTournament(final TournamentCache tournament) {
		checkValidity();
		this.tournaments.add(tournament);
	}
	
	/**
	 * Removes the given tournament cache from this cache. This will not
	 * invalidate the given cache.
	 * 
	 * @param tournament
	 *            The tournament cache to remove
	 * @return Whether the given cache was managed by this cache
	 * @throws IllegalStateException
	 *             if the cache is invalid
	 */
	public boolean removeTournament(final TournamentCache tournament) {
		checkValidity();
		return this.tournaments.remove(tournament);
	}
	
	/**
	 * Updates this cache with the given tournaments.
	 * 
	 * @param tournaments
	 *            The new updated list of tournaments
	 * @throws IllegalStateException
	 *             if the cache is invalid
	 */
	@Override
	public void update(final List<Tournament> tournaments) {
		checkValidity();
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
			toDelete.invalidate();
		}
	}
}
