package com.gpluscb.challonge_listener.events.tournament.match.attachment;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class AttachmentDescriptionChangedEvent extends GenericAttachmentChangedEvent {
	private String description;
	private String previousDescription;
	
	public AttachmentDescriptionChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, Attachment attachment, Attachment previousAttachment, String description, String previousDescription) {
		super(tournament, previousTournament, match, previousMatch, attachment, previousAttachment);
		this.description = description;
		this.previousDescription = previousDescription;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getPreviousDescription() {
		return previousDescription;
	}
}
