package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchHasAttachmentChangedEvent extends GenericMatchChangedEvent {
	private Boolean hasAttachment;
	private Boolean previousHasAttachment;
	
	public MatchHasAttachmentChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, Boolean hasAttachment, Boolean previousHasAttachment) {
		super(tournament, previousTournament, match, previousMatch);
		this.hasAttachment = hasAttachment;
		this.previousHasAttachment = previousHasAttachment;
	}
	
	public Boolean getHasAttachment() {
		return hasAttachment;
	}
	
	public Boolean getPreviousHasAttachment() {
		return previousHasAttachment;
	}
}
