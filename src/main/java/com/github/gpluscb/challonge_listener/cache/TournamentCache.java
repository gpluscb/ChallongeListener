package com.github.gpluscb.challonge_listener.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class TournamentCache {
	private final ChallongeCache challonge;
	
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
	
	public ChallongeCache getChallonge() {
		return this.challonge;
	}
	
	public Tournament getTournament() {
		return this.tournament;
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
	
	public ParticipantCache getParticipantById(final long participantId) {
		for(final ParticipantCache participant : this.participants) {
			if(participant.getParticipant().getId().longValue() == participantId) {
				return participant;
			}
		}
		return null;
	}
	
	public List<ParticipantCache> getParticipants() {
		return Collections.unmodifiableList(this.participants);
	}
	
	void update(final Tournament tournament) {
		this.tournament = tournament;
		
		List<MatchCache> notHandledMatches = new ArrayList<>(this.matches);
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
		
		List<ParticipantCache> notHandledParticipants = new ArrayList<>(this.participants);
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
		}
	}
}
