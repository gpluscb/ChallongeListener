package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentTeamConvertableChangedEvent extends GenericTournamentChangedEvent {
	private final Boolean teamConvertable;
	private final Boolean previousTeamConvertable;
	
	public TournamentTeamConvertableChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Boolean teamConvertable, final Boolean previousTeamConvertable) {
		super(tournament, previousTournament);
		this.teamConvertable = teamConvertable;
		this.previousTeamConvertable = previousTeamConvertable;
	}
	
	public Boolean getTeamConvertable() {
		return this.teamConvertable;
	}
	
	public Boolean getPreviousTeamConvertable() {
		return this.previousTeamConvertable;
	}
}
