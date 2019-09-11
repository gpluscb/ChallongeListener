package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentLiveImageUrlChangedEvent extends GenericTournamentChangedEvent {
	private String liveImageUrl;
	private String previousLiveImageUrl;
	
	public TournamentLiveImageUrlChangedEvent(Tournament tournament, Tournament previousTournament, String liveImageUrl, String previousLiveImageUrl) {
		super(tournament, previousTournament);
		this.liveImageUrl = liveImageUrl;
		this.previousLiveImageUrl = previousLiveImageUrl;
	}
	
	public String getLiveImageUrl() {
		return liveImageUrl;
	}
	
	public String getPreviousLiveImageUrl() {
		return previousLiveImageUrl;
	}
}
