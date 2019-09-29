package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchHasAttachmentChangedEvent extends GenericMatchChangedEvent {
	private final Boolean hasAttachment;
	private final Boolean previousHasAttachment;
	
	public MatchHasAttachmentChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final Boolean hasAttachment,
			final Boolean previousHasAttachment) {
		super(tournament, previousTournament, match, previousMatch);
		this.hasAttachment = hasAttachment;
		this.previousHasAttachment = previousHasAttachment;
	}
	
	public Boolean getHasAttachment() {
		return this.hasAttachment;
	}
	
	public Boolean getPreviousHasAttachment() {
		return this.previousHasAttachment;
	}
}
