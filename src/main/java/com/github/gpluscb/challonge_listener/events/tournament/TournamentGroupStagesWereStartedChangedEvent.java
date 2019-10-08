package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentGroupStagesWereStartedChangedEvent extends GenericTournamentChangedEvent {
	private final Boolean groupStagesWereStarted;
	private final Boolean previousGroupStagesWereStarted;
	
	public TournamentGroupStagesWereStartedChangedEvent(final Tournament tournament,
			final Tournament previousTournament, final Boolean groupStagesWereStarted,
			final Boolean previousGroupStagesWereStarted) {
		super(tournament, previousTournament);
		this.groupStagesWereStarted = groupStagesWereStarted;
		this.previousGroupStagesWereStarted = previousGroupStagesWereStarted;
	}
	
	public Boolean getGroupStagesWereStarted() {
		return this.groupStagesWereStarted;
	}
	
	public Boolean getPreviousGroupStagesWereStarted() {
		return this.previousGroupStagesWereStarted;
	}
}
