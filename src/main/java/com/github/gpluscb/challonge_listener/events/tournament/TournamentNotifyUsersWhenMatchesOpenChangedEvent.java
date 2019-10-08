package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentNotifyUsersWhenMatchesOpenChangedEvent extends GenericTournamentChangedEvent {
	private final Boolean notifyUsersWhenMatchesOpen;
	private final Boolean previousNotifyUsersWhenMatchesOpen;
	
	public TournamentNotifyUsersWhenMatchesOpenChangedEvent(final Tournament tournament,
			final Tournament previousTournament, final Boolean notifyUsersWhenMatchesOpen,
			final Boolean previousNotifyUsersWhenMatchesOpen) {
		super(tournament, previousTournament);
		this.notifyUsersWhenMatchesOpen = notifyUsersWhenMatchesOpen;
		this.previousNotifyUsersWhenMatchesOpen = previousNotifyUsersWhenMatchesOpen;
	}
	
	public Boolean getNotifyUsersWhenMatchesOpen() {
		return this.notifyUsersWhenMatchesOpen;
	}
	
	public Boolean getPreviousNotifyUsersWhenMatchesOpen() {
		return this.previousNotifyUsersWhenMatchesOpen;
	}
}
