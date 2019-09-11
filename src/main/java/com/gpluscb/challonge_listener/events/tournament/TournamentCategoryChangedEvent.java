package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentCategoryChangedEvent extends GenericTournamentChangedEvent {
	private String category;
	private String previousCategory;
	
	public TournamentCategoryChangedEvent(Tournament tournament, Tournament previousTournament, String category, String previousCategory) {
		super(tournament, previousTournament);
		this.category = category;
		this.previousCategory = previousCategory;
	}
	
	public String getCategory() {
		return category;
	}
	
	public String getPreviousCategory() {
		return previousCategory;
	}
}
