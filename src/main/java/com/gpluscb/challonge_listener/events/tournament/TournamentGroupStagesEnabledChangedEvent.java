package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentGroupStagesEnabledChangedEvent extends GenericTournamentChangedEvent {
	private Boolean groupStagesEnabled;
	private Boolean previousGroupStagesEnabled;
	
	public TournamentGroupStagesEnabledChangedEvent(Tournament tournament, Tournament previousTournament, Boolean groupStagesEnabled, Boolean previousGroupStagesEnabled) {
		super(tournament, previousTournament);
		this.groupStagesEnabled = groupStagesEnabled;
		this.previousGroupStagesEnabled = previousGroupStagesEnabled;
	}
	
	public Boolean getGroupStagesEnabled() {
		return groupStagesEnabled;
	}
	
	public Boolean getPreviousGroupStagesEnabled() {
		return previousGroupStagesEnabled;
	}
}
