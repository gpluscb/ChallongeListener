package com.github.gpluscb.challonge_listener.cache;

import java.util.ArrayList;
import java.util.List;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class TournamentCache {
	private Tournament tournament;
	
	private final List<MatchCache> matches;
	private final List<ParticipantCache> participants;
	
	TournamentCache(final Tournament tournament) {
		this.tournament = tournament;
		this.matches = new ArrayList<>();
		this.participants = new ArrayList<>();
		
		for(final Match match : tournament.getMatches()) {
			this.matches.add(new MatchCache(match));
		}
		
		for(final Participant participant : tournament.getParticipants()) {
			this.participants.add(new ParticipantCache(participant));
		}
	}
}
