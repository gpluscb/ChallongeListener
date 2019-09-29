package com.gpluscb.challonge_listener.events.tournament.match.attachment;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class AttachmentUrlChangedEvent extends GenericAttachmentChangedEvent {
	private final String url;
	private final String previousUrl;
	
	public AttachmentUrlChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final Attachment attachment,
			final Attachment previousAttachment, final String url, final String previousUrl) {
		super(tournament, previousTournament, match, previousMatch, attachment, previousAttachment);
		this.url = url;
		this.previousUrl = previousUrl;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public String getPreviousUrl() {
		return this.previousUrl;
	}
}
