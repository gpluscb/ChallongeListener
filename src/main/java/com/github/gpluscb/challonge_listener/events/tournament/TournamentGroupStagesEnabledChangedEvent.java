package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentGroupStagesEnabledChangedEvent extends GenericTournamentChangedEvent {
	private final Boolean groupStagesEnabled;
	private final Boolean previousGroupStagesEnabled;
	
	public TournamentGroupStagesEnabledChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Boolean groupStagesEnabled, final Boolean previousGroupStagesEnabled) {
		super(tournament, previousTournament);
		this.groupStagesEnabled = groupStagesEnabled;
		this.previousGroupStagesEnabled = previousGroupStagesEnabled;
	}
	
	public Boolean getGroupStagesEnabled() {
		return this.groupStagesEnabled;
	}
	
	public Boolean getPreviousGroupStagesEnabled() {
		return this.previousGroupStagesEnabled;
	}
}
