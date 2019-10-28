package com.github.gpluscb.challonge_listener.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

/**
 * Manages a single {@link at.stefangeyer.challonge.model.Tournament Tournament}
 * as a cache.
 */
public class TournamentCache extends Cache<Tournament> {
	private final ChallongeCache challonge;
	
	private Tournament tournament;
	
	private final List<MatchCache> matches;
	private final List<ParticipantCache> participants;
	
	/**
	 * Creates a new cache of the given tournament.
	 * 
	 * @param challonge
	 *            The challonge cache this cache is managed by
	 * @param tournament
	 *            The tournament the cache manages
	 */
	public TournamentCache(final ChallongeCache challonge, final Tournament tournament) {
		this.challonge = challonge;
		
		this.tournament = tournament;
		
		this.matches = new ArrayList<>();
		this.participants = new ArrayList<>();
		
		for(final Match match : this.tournament.getMatches()) {
			this.matches.add(new MatchCache(this, match));
		}
		
		for(final Participant participant : this.tournament.getParticipants()) {
			this.participants.add(new ParticipantCache(this, participant));
		}
	}
	
	/**
	 * Gets the {@link ChallongeCache} this cache belongs to.
	 * 
	 * @return The {@link ChallongeCache} that owns this cache
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public ChallongeCache getChallonge() {
		checkValidity();
		return this.challonge;
	}
	
	/**
	 * Gets the managed {@link at.stefangeyer.challonge.model.Tournament
	 * Tournament}.
	 * 
	 * @return The managed tournament
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public Tournament getTournament() {
		checkValidity();
		return this.tournament;
	}
	
	/**
	 * Gets a match with the given id.
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
	 * Gets the managed {@link MatchCache MatchCaches}.
	 * 
	 * @return the managed match caches
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public List<MatchCache> getMatches() {
		checkValidity();
		return Collections.unmodifiableList(this.matches);
	}
	
	/**
	 * Adds the given match to this cache.
	 * 
	 * @param match
	 *            The match to manage
	 * @return The created cache
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public MatchCache newMatch(final Match match) {
		checkValidity();
		final MatchCache cache = new MatchCache(this, match);
		this.matches.add(cache);
		return cache;
	}
	
	/**
	 * Removes the given match cache from this cache if it was managed by this
	 * cache. In that case, the given cache will be invalidated.
	 * 
	 * @param match
	 *            The match cache to remove
	 * @return Whether the given cache was managed by this cache
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public boolean deleteMatch(final MatchCache match) {
		checkValidity();
		if(this.matches.remove(match)) {
			match.invalidate();
			return true;
		}
		return false;
	}
	
	/**
	 * Gets a participant with the given id.
	 * 
	 * @param participantId
	 *            The id of the participant
	 * @return The participant with the given id or null if no such participant
	 *             exists
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public ParticipantCache getParticipantById(final long participantId) {
		checkValidity();
		for(final ParticipantCache participant : this.participants) {
			if(participant.getParticipant().getId().longValue() == participantId) {
				return participant;
			}
		}
		return null;
	}
	
	/**
	 * Gets the managed {@link ParticipantCache ParticipantCaches}.
	 * 
	 * @return the managed participant caches
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public List<ParticipantCache> getParticipants() {
		checkValidity();
		return Collections.unmodifiableList(this.participants);
	}
	
	/**
	 * Adds the given participant to this cache.
	 * 
	 * @param participant
	 *            The participant to manage
	 * @return The created cache
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public ParticipantCache newParticipant(final Participant participant) {
		checkValidity();
		final ParticipantCache cache = new ParticipantCache(this, participant);
		this.participants.add(cache);
		return cache;
	}
	
	/**
	 * Removes the given participant cache from this cache if it was managed by
	 * this cache. In that case, the given cache will be invalidated.
	 * 
	 * @param participant
	 *            The participant cache to remove
	 * @return Whether the given cache was managed by this cache
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public boolean deleteParticipant(final ParticipantCache participant) {
		checkValidity();
		if(this.participants.remove(participant)) {
			participant.invalidate();
			return true;
		}
		return false;
	}
	
	/**
	 * Updates this cache with the given tournament.
	 * 
	 * @param tournament
	 *            The new updated tournament
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	@Override
	public void update(final Tournament tournament) {
		checkValidity();
		this.tournament = tournament;
		
		final List<MatchCache> notHandledMatches = new ArrayList<>(this.matches);
		for(final Match match : this.tournament.getMatches()) {
			final MatchCache cache = getMatchById(match.getId().longValue());
			if(cache == null) {
				// New match
				this.matches.add(new MatchCache(this, match));
			} else {
				cache.update(match);
				
				// Now handled
				notHandledMatches.remove(cache);
			}
		}
		// Not present in given tournaments matches
		for(final MatchCache toDelete : notHandledMatches) {
			this.matches.remove(toDelete);
			toDelete.invalidate();
		}
		
		final List<ParticipantCache> notHandledParticipants = new ArrayList<>(this.participants);
		for(final Participant participant : this.tournament.getParticipants()) {
			final ParticipantCache cache = getParticipantById(participant.getId().longValue());
			if(cache == null) {
				// New participant
				this.participants.add(new ParticipantCache(this, participant));
			} else {
				cache.update(participant);
				
				// Now handled
				notHandledParticipants.remove(cache);
			}
		}
		// Not present in given tournaments participants
		for(final ParticipantCache toDelete : notHandledParticipants) {
			this.participants.remove(toDelete);
			toDelete.invalidate();
		}
	}
}
