package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentHoldThirdPlaceMatchChangedEvent extends GenericTournamentChangedEvent {
	private Boolean holdThirdPlaceMatch;
	private Boolean previousHoldThirdPlaceMatch;
	
	public TournamentHoldThirdPlaceMatchChangedEvent(Tournament tournament, Tournament previousTournament,
			Boolean holdThirdPlaceMatch, Boolean previousHoldThirdPlaceMatch) {
		super(tournament, previousTournament);
		this.holdThirdPlaceMatch = holdThirdPlaceMatch;
		this.previousHoldThirdPlaceMatch = previousHoldThirdPlaceMatch;
	}
	
	public Boolean getHoldThirdPlaceMatch() {
		return holdThirdPlaceMatch;
	}
	
	public Boolean getPreviousHoldThirdPlaceMatch() {
		return previousHoldThirdPlaceMatch;
	}
}
