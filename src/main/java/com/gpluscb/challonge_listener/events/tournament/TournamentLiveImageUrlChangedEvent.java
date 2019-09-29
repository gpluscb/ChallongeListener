package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentLiveImageUrlChangedEvent extends GenericTournamentChangedEvent {
	private final String liveImageUrl;
	private final String previousLiveImageUrl;
	
	public TournamentLiveImageUrlChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final String liveImageUrl, final String previousLiveImageUrl) {
		super(tournament, previousTournament);
		this.liveImageUrl = liveImageUrl;
		this.previousLiveImageUrl = previousLiveImageUrl;
	}
	
	public String getLiveImageUrl() {
		return this.liveImageUrl;
	}
	
	public String getPreviousLiveImageUrl() {
		return this.previousLiveImageUrl;
	}
}
