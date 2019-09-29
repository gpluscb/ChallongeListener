package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentNotifyUsersWhenTheTournamentEndsChangedEvent extends GenericTournamentChangedEvent {
	private final Boolean notifyUsersWhenTheTournamentEnds;
	private final Boolean previousNotifyUsersWhenTheTournamentEnds;
	
	public TournamentNotifyUsersWhenTheTournamentEndsChangedEvent(final Tournament tournament,
			final Tournament previousTournament, final Boolean notifyUsersWhenTheTournamentEnds,
			final Boolean previousNotifyUsersWhenTheTournamentEnds) {
		super(tournament, previousTournament);
		this.notifyUsersWhenTheTournamentEnds = notifyUsersWhenTheTournamentEnds;
		this.previousNotifyUsersWhenTheTournamentEnds = previousNotifyUsersWhenTheTournamentEnds;
	}
	
	public Boolean getNotifyUsersWhenTheTournamentEnds() {
		return this.notifyUsersWhenTheTournamentEnds;
	}
	
	public Boolean getPreviousNotifyUsersWhenTheTournamentEnds() {
		return this.previousNotifyUsersWhenTheTournamentEnds;
	}
}
