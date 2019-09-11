package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.query.enumeration.GrandFinalsModifier;

public class TournamentGrandFinalsModifierChangedEvent extends GenericTournamentChangedEvent {
	private GrandFinalsModifier grandFinalsModifier;
	private GrandFinalsModifier previousGrandFinalsModifier;
	
	public TournamentGrandFinalsModifierChangedEvent(Tournament tournament, Tournament previousTournament, GrandFinalsModifier grandFinalsModifier, GrandFinalsModifier previousGrandFinalsModifier) {
		super(tournament, previousTournament);
		this.grandFinalsModifier = grandFinalsModifier;
		this.previousGrandFinalsModifier = previousGrandFinalsModifier;
	}
	
	public GrandFinalsModifier getGrandFinalsModifier() {
		return grandFinalsModifier;
	}
	
	public GrandFinalsModifier getPreviousGrandFinalsModifier() {
		return previousGrandFinalsModifier;
	}
}
