package com.gpluscb.challonge_listener.events.tournament.match;

import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchAttachmentCountChangedEvent extends GenericMatchChangedEvent {
	private final Integer attachmentCount;
	private final Integer previousAttachmentCount;
	
	public MatchAttachmentCountChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final Integer attachmentCount,
			final Integer previousAttachmentCount) {
		super(tournament, previousTournament, match, previousMatch);
		this.attachmentCount = attachmentCount;
		this.previousAttachmentCount = previousAttachmentCount;
	}
	
	public Integer getAttachmentCount() {
		return this.attachmentCount;
	}
	
	public Integer getPreviousAttachmentCount() {
		return this.previousAttachmentCount;
	}
}
