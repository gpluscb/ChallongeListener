package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentHoldThirdPlaceMatchChangedEvent extends GenericTournamentChangedEvent {
	private final Boolean holdThirdPlaceMatch;
	private final Boolean previousHoldThirdPlaceMatch;
	
	public TournamentHoldThirdPlaceMatchChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Boolean holdThirdPlaceMatch, final Boolean previousHoldThirdPlaceMatch) {
		super(tournament, previousTournament);
		this.holdThirdPlaceMatch = holdThirdPlaceMatch;
		this.previousHoldThirdPlaceMatch = previousHoldThirdPlaceMatch;
	}
	
	public Boolean getHoldThirdPlaceMatch() {
		return this.holdThirdPlaceMatch;
	}
	
	public Boolean getPreviousHoldThirdPlaceMatch() {
		return this.previousHoldThirdPlaceMatch;
	}
}
