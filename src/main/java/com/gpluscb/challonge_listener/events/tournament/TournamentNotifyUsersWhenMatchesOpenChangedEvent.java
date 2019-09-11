package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentNotifyUsersWhenMatchesOpenChangedEvent extends GenericTournamentChangedEvent {
	private Boolean notifyUsersWhenMatchesOpen;
	private Boolean previousNotifyUsersWhenMatchesOpen;
	
	public TournamentNotifyUsersWhenMatchesOpenChangedEvent(Tournament tournament, Tournament previousTournament, Boolean notifyUsersWhenMatchesOpen, Boolean previousNotifyUsersWhenMatchesOpen) {
		super(tournament, previousTournament);
		this.notifyUsersWhenMatchesOpen = notifyUsersWhenMatchesOpen;
		this.previousNotifyUsersWhenMatchesOpen = previousNotifyUsersWhenMatchesOpen;
	}
	
	public Boolean getNotifyUsersWhenMatchesOpen() {
		return notifyUsersWhenMatchesOpen;
	}
	
	public Boolean getPreviousNotifyUsersWhenMatchesOpen() {
		return previousNotifyUsersWhenMatchesOpen;
	}
}
