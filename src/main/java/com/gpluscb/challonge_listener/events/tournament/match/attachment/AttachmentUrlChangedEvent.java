package com.gpluscb.challonge_listener.events.tournament.match.attachment;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class AttachmentUrlChangedEvent extends GenericAttachmentChangedEvent {
	private String url;
	private String previousUrl;
	
	public AttachmentUrlChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, Attachment attachment, Attachment previousAttachment, String url, String previousUrl) {
		super(tournament, previousTournament, match, previousMatch, attachment, previousAttachment);
		this.url = url;
		this.previousUrl = previousUrl;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getPreviousUrl() {
		return previousUrl;
	}
}
