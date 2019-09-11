package com.gpluscb.challonge_listener.events.tournament.match.attachment;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class AttachmentUpdatedAtChangedEvent extends GenericAttachmentChangedEvent {
	private OffsetDateTime updatedAt;
	private OffsetDateTime previousUpdatedAt;
	
	public AttachmentUpdatedAtChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, Attachment attachment, Attachment previousAttachment, OffsetDateTime updatedAt, OffsetDateTime previousUpdatedAt) {
		super(tournament, previousTournament, match, previousMatch, attachment, previousAttachment);
		this.updatedAt = updatedAt;
		this.previousUpdatedAt = previousUpdatedAt;
	}
	
	public OffsetDateTime getUpdatedAt() {
		return updatedAt;
	}
	
	public OffsetDateTime getPreviousUpdatedAt() {
		return previousUpdatedAt;
	}
}
