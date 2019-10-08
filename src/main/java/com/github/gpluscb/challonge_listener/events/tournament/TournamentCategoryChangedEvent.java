package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentCategoryChangedEvent extends GenericTournamentChangedEvent {
	private final String category;
	private final String previousCategory;
	
	public TournamentCategoryChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final String category, final String previousCategory) {
		super(tournament, previousTournament);
		this.category = category;
		this.previousCategory = previousCategory;
	}
	
	public String getCategory() {
		return this.category;
	}
	
	public String getPreviousCategory() {
		return this.previousCategory;
	}
}
