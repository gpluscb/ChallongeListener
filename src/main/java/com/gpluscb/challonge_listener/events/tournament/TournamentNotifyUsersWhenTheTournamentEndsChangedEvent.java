package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentNotifyUsersWhenTheTournamentEndsChangedEvent extends GenericTournamentChangedEvent {
	private Boolean notifyUsersWhenTheTournamentEnds;
	private Boolean previousNotifyUsersWhenTheTournamentEnds;
	
	public TournamentNotifyUsersWhenTheTournamentEndsChangedEvent(Tournament tournament, Tournament previousTournament,
			Boolean notifyUsersWhenTheTournamentEnds, Boolean previousNotifyUsersWhenTheTournamentEnds) {
		super(tournament, previousTournament);
		this.notifyUsersWhenTheTournamentEnds = notifyUsersWhenTheTournamentEnds;
		this.previousNotifyUsersWhenTheTournamentEnds = previousNotifyUsersWhenTheTournamentEnds;
	}
	
	public Boolean getNotifyUsersWhenTheTournamentEnds() {
		return notifyUsersWhenTheTournamentEnds;
	}
	
	public Boolean getPreviousNotifyUsersWhenTheTournamentEnds() {
		return previousNotifyUsersWhenTheTournamentEnds;
	}
}
