package com.github.gpluscb.challonge_listener.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;

/**
 * Manages a single {@link at.stefangeyer.challonge.model.Participant
 * Participant} as a cache.
 */
public class ParticipantCache extends Cache<Participant> {
	private final TournamentCache tournament;
	
	private Participant participant;
	
	private final List<MatchCache> matches;
	
	/**
	 * Creates a new cache of the given participant.
	 * 
	 * @param tournament
	 *            The tournament cache this cache is managed by
	 * @param participant
	 *            The participant the cache manages
	 */
	public ParticipantCache(final TournamentCache tournament, final Participant participant) {
		this.tournament = tournament;
		
		this.participant = participant;
		
		this.matches = new ArrayList<>();
		
		for(final Match match : this.participant.getMatches()) {
			final MatchCache cache = this.tournament.getMatchById(match.getId().longValue());
			if(cache == null) {
				throw new IllegalStateException("Match exists in tournament, is not represented in cache");
			}
			this.matches.add(cache);
			cache.linkParticipant(this);
		}
	}
	
	/**
	 * Gets the {@link TournamentCache} this cache belongs to.
	 * 
	 * @return The {@link TournamentCache} that owns this cache
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public TournamentCache getTournament() {
		checkValidity();
		return this.tournament;
	}
	
	/**
	 * Gets the managed {@link at.stefangeyer.challonge.model.Participant
	 * Participant}.
	 * 
	 * @return The managed participant
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public Participant getParticipant() {
		checkValidity();
		return this.participant;
	}
	
	/**
	 * Gets a match of this participant with the given id.
	 * 
	 * @param matchId
	 *            The id of the match
	 * @return The match with the given id or null if no such match exists
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public MatchCache getMatchById(final long matchId) {
		checkValidity();
		for(final MatchCache match : this.matches) {
			if(match.getMatch().getId().longValue() == matchId) {
				return match;
			}
		}
		return null;
	}
	
	/**
	 * Gets the linked {@link MatchCache MatchCaches}.
	 * 
	 * @return the linked match caches
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public List<MatchCache> getMatches() {
		checkValidity();
		return Collections.unmodifiableList(this.matches);
	}
	
	/**
	 * Links the given match cache to this cache. No link in the other direction
	 * will be initiated.
	 * 
	 * @param match
	 *            The attachment cache to link
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public void linkMatch(final MatchCache match) {
		checkValidity();
		this.matches.add(match);
	}
	
	/**
	 * Unlinks the given match cache from this cache. This will not invalidate
	 * the given cache. The link in the other direction will persist.
	 * 
	 * @param match
	 *            The match cache to remove
	 * @return Whether the given cache was linked to this cache
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public boolean unlinkMatch(final MatchCache match) {
		checkValidity();
		return this.matches.remove(match);
	}
	
	/**
	 * Updates this cache with the given participant.
	 * 
	 * @param participant
	 *            The new updated participant
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	@Override
	public void update(final Participant participant) {
		checkValidity();
		this.participant = participant;
		
		for(final Match match : this.participant.getMatches()) {
			final MatchCache cache = this.tournament.getMatchById(match.getId().longValue());
			if(cache == null) {
				throw new IllegalStateException("Match exists in tournament but is not represented in cache");
			}
			this.matches.add(cache);
			cache.linkParticipant(this);
		}
	}
}
