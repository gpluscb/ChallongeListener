package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentTeamConvertableChangedEvent extends GenericTournamentChangedEvent {
	private Boolean teamConvertable;
	private Boolean previousTeamConvertable;
	
	public TournamentTeamConvertableChangedEvent(Tournament tournament, Tournament previousTournament,
			Boolean teamConvertable, Boolean previousTeamConvertable) {
		super(tournament, previousTournament);
		this.teamConvertable = teamConvertable;
		this.previousTeamConvertable = previousTeamConvertable;
	}
	
	public Boolean getTeamConvertable() {
		return teamConvertable;
	}
	
	public Boolean getPreviousTeamConvertable() {
		return previousTeamConvertable;
	}
}
