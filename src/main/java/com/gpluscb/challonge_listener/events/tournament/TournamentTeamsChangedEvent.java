package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentTeamsChangedEvent extends GenericTournamentChangedEvent {
	private Boolean teams;
	private Boolean previousTeams;
	
	public TournamentTeamsChangedEvent(Tournament tournament, Tournament previousTournament, Boolean teams, Boolean previousTeams) {
		super(tournament, previousTournament);
		this.teams = teams;
		this.previousTeams = previousTeams;
	}
	
	public Boolean getTeams() {
		return teams;
	}
	
	public Boolean getPreviousTeams() {
		return previousTeams;
	}
}
