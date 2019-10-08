package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.query.enumeration.GrandFinalsModifier;

public class TournamentGrandFinalsModifierChangedEvent extends GenericTournamentChangedEvent {
	private final GrandFinalsModifier grandFinalsModifier;
	private final GrandFinalsModifier previousGrandFinalsModifier;
	
	public TournamentGrandFinalsModifierChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final GrandFinalsModifier grandFinalsModifier, final GrandFinalsModifier previousGrandFinalsModifier) {
		super(tournament, previousTournament);
		this.grandFinalsModifier = grandFinalsModifier;
		this.previousGrandFinalsModifier = previousGrandFinalsModifier;
	}
	
	public GrandFinalsModifier getGrandFinalsModifier() {
		return this.grandFinalsModifier;
	}
	
	public GrandFinalsModifier getPreviousGrandFinalsModifier() {
		return this.previousGrandFinalsModifier;
	}
}
