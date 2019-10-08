package com.github.gpluscb.challonge_listener.events.tournament.match.attachment;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class AttachmentDescriptionChangedEvent extends GenericAttachmentChangedEvent {
	private final String description;
	private final String previousDescription;
	
	public AttachmentDescriptionChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final Attachment attachment,
			final Attachment previousAttachment, final String description, final String previousDescription) {
		super(tournament, previousTournament, match, previousMatch, attachment, previousAttachment);
		this.description = description;
		this.previousDescription = previousDescription;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String getPreviousDescription() {
		return this.previousDescription;
	}
}
