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
public class TournamentCache {
	private ChallongeCache challonge;
	
	private Tournament tournament;
	
	private final List<MatchCache> matches;
	private final List<ParticipantCache> participants;
	
	TournamentCache(final ChallongeCache challonge, final Tournament tournament) {
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
	 */
	public ChallongeCache getChallonge() {
		return this.challonge;
	}
	
	/**
	 * Gets the managed {@link at.stefangeyer.challonge.model.Tournament
	 * Tournament}.
	 * 
	 * @return The managed tournament
	 */
	public Tournament getTournament() {
		return this.tournament;
	}
	
	/**
	 * Gets a match with the given id.
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
	 * Gets the managed {@link MatchCache MatchCaches}.
	 * 
	 * @return the managed match caches
	 */
	public List<MatchCache> getMatches() {
		return Collections.unmodifiableList(this.matches);
	}
	
	/**
	 * Gets a participant with the given id.
	 * 
	 * @param participantId
	 *            The id of the participant
	 * @return The participant with the given id or null if no such participant
	 *             exists
	 */
	public ParticipantCache getParticipantById(final long participantId) {
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
	 */
	public List<ParticipantCache> getParticipants() {
		return Collections.unmodifiableList(this.participants);
	}
	
	/**
	 * Checks whether this cache is valid or if it has been deleted.
	 * 
	 * @return Whether this cache is valid
	 */
	public boolean isValid() {
		return this.challonge != null;
	}
	
	void update(final Tournament tournament) {
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
			toDelete.delete();
		}
	}
	
	void delete() {
		this.challonge = null;
		this.tournament = null;
		for(final ParticipantCache participant : this.participants) {
			participant.delete();
		}
		this.participants.clear();
		for(final MatchCache match : this.matches) {
			match.delete();
		}
		this.matches.clear();
	}
}
