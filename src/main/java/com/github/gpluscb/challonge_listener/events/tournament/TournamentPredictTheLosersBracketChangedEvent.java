package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentPredictTheLosersBracketChangedEvent extends GenericTournamentChangedEvent {
	private final Boolean predictTheLosersBracket;
	private final Boolean previousPredictTheLosersBracket;
	
	public TournamentPredictTheLosersBracketChangedEvent(final Tournament tournament,
			final Tournament previousTournament, final Boolean predictTheLosersBracket,
			final Boolean previousPredictTheLosersBracket) {
		super(tournament, previousTournament);
		this.predictTheLosersBracket = predictTheLosersBracket;
		this.previousPredictTheLosersBracket = previousPredictTheLosersBracket;
	}
	
	public Boolean getPredictTheLosersBracket() {
		return this.predictTheLosersBracket;
	}
	
	public Boolean getPreviousPredictTheLosersBracket() {
		return this.previousPredictTheLosersBracket;
	}
}
