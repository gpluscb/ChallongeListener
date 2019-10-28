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
	 *             If the cache is invalid
	 */
	public TournamentCache getTournamentById(final long tournamentId) {
		checkValidity();
		final int index = getTournamentIndexById(tournamentId);
		return index < 0 ? null : this.tournaments.get(index);
	}
	
	/**
	 * Gets the managed {@link TournamentCache TournamentCaches}.
	 * 
	 * @return the managed tournament caches
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public List<TournamentCache> getTournaments() {
		checkValidity();
		return Collections.unmodifiableList(this.tournaments);
	}
	
	/**
	 * Adds the given tournament to this cache.
	 * 
	 * @param tournament
	 *            The tournament to manage
	 * @return The created cache
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public TournamentCache newTournament(final Tournament tournament) {
		checkValidity();
		final TournamentCache cache = new TournamentCache(this, tournament);
		this.tournaments.add(cache);
		return cache;
	}
	
	/**
	 * Removes the given tournament cache from this cache if it was managed by
	 * this cache. In that case, the given cache will be invalidated.
	 * 
	 * @param tournament
	 *            The tournament cache to remove
	 * @return Whether the given cache was managed by this cache
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public boolean deleteTournament(final TournamentCache tournament) {
		checkValidity();
		if(this.tournaments.remove(tournament)) {
			tournament.invalidate();
			return true;
		}
		return false;
	}
	
	/**
	 * Removes the cache of the given tournament from this cache if it was
	 * managed by this cache. In that case, that cache will be invalidated.
	 * 
	 * @param tournament
	 *            The tournament cache to remove
	 * @return Whether the given cache was managed by this cache
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public boolean deleteTournament(final Tournament tournament) {
		checkValidity();
		final int index = getTournamentIndexById(tournament.getId().longValue());
		if(index < 0) {
			return false;
		}
		this.tournaments.get(index);
		return true;
	}
	
	private int getTournamentIndexById(final long tournamentId) {
		for(int i = 0; i < this.tournaments.size(); i++) {
			if(this.tournaments.get(i).getTournament().getId().longValue() == tournamentId) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Updates this cache with the given tournaments.
	 * 
	 * @param tournaments
	 *            The new updated list of tournaments
	 * @throws IllegalStateException
	 *             If the cache is invalid
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
