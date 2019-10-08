package com.github.gpluscb.challonge_listener.events.tournament.match;

import java.util.List;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchAttachmentsChangedEvent extends GenericMatchChangedEvent {
	private final List<Attachment> attachments;
	private final List<Attachment> previousAttachments;
	
	public MatchAttachmentsChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final List<Attachment> attachments,
			final List<Attachment> previousAttachments) {
		super(tournament, previousTournament, match, previousMatch);
		this.attachments = attachments;
		this.previousAttachments = previousAttachments;
	}
	
	public List<Attachment> getAttachments() {
		return this.attachments;
	}
	
	public List<Attachment> getPreviousAttachments() {
		return this.previousAttachments;
	}
}
