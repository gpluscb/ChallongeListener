package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentTeamsChangedEvent extends GenericTournamentChangedEvent {
	private final Boolean teams;
	private final Boolean previousTeams;
	
	public TournamentTeamsChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Boolean teams, final Boolean previousTeams) {
		super(tournament, previousTournament);
		this.teams = teams;
		this.previousTeams = previousTeams;
	}
	
	public Boolean getTeams() {
		return this.teams;
	}
	
	public Boolean getPreviousTeams() {
		return this.previousTeams;
	}
}
