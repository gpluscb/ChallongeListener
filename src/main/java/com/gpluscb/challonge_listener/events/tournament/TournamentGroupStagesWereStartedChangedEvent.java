package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentGroupStagesWereStartedChangedEvent extends GenericTournamentChangedEvent {
	private Boolean groupStagesWereStarted;
	private Boolean previousGroupStagesWereStarted;
	
	public TournamentGroupStagesWereStartedChangedEvent(Tournament tournament, Tournament previousTournament, Boolean groupStagesWereStarted, Boolean previousGroupStagesWereStarted) {
		super(tournament, previousTournament);
		this.groupStagesWereStarted = groupStagesWereStarted;
		this.previousGroupStagesWereStarted = previousGroupStagesWereStarted;
	}
	
	public Boolean getGroupStagesWereStarted() {
		return groupStagesWereStarted;
	}
	
	public Boolean getPreviousGroupStagesWereStarted() {
		return previousGroupStagesWereStarted;
	}
}
