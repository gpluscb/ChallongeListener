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
public class ParticipantCache {
	private final TournamentCache tournament;
	
	private Participant participant;
	
	private final List<MatchCache> matches;
	
	ParticipantCache(final TournamentCache tournament, final Participant participant) {
		this.tournament = tournament;
		
		this.participant = participant;
		
		this.matches = new ArrayList<>();
		
		for(final Match match : this.participant.getMatches()) {
			final MatchCache cache = this.tournament.getMatchById(match.getId().longValue());
			if(cache == null) {
				throw new IllegalStateException("Match exists in tournament, is not represented in cache");
			}
			this.matches.add(cache);
			cache.addParticipant(this);
		}
	}
	
	/**
	 * Gets the {@link TournamentCache} this cache belongs to.
	 * 
	 * @return The {@link TournamentCache} that owns this cache
	 */
	public TournamentCache getTournament() {
		return this.tournament;
	}
	
	/**
	 * Gets the managed {@link at.stefangeyer.challonge.model.Participant
	 * Participant}.
	 * 
	 * @return The managed participant
	 */
	public Participant getParticipant() {
		return this.participant;
	}
	
	/**
	 * Gets a match of this participant with the given id.
	 * 
	 * @param matchId
	 *            The id of the match
	 * @return The match with the given id or null if no such match exists
	 */
	public MatchCache getMatchById(final long matchId) {
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
	 */
	public List<MatchCache> getMatches() {
		return Collections.unmodifiableList(this.matches);
	}
	
	void update(final Participant participant) {
		this.participant = participant;
		
		for(final Match match : this.participant.getMatches()) {
			final MatchCache cache = this.tournament.getMatchById(match.getId().longValue());
			if(cache == null) {
				throw new IllegalStateException("Match exists in tournament, is not represented in cache");
			}
			this.matches.add(cache);
			cache.addParticipant(this);
		}
	}
}
