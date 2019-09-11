package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchAttachmentCountChangedEvent extends GenericMatchChangedEvent {
	private Integer attachmentCount;
	private Integer previousAttachmentCount;
	
	public MatchAttachmentCountChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, Integer attachmentCount, Integer previousAttachmentCount) {
		super(tournament, previousTournament, match, previousMatch);
		this.attachmentCount = attachmentCount;
		this.previousAttachmentCount = previousAttachmentCount;
	}
	
	public Integer getAttachmentCount() {
		return attachmentCount;
	}
	
	public Integer getPreviousAttachmentCount() {
		return previousAttachmentCount;
	}
}
