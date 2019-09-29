package com.gpluscb.challonge_listener.events.tournament.match.attachment;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class AttachmentUpdatedAtChangedEvent extends GenericAttachmentChangedEvent {
	private final OffsetDateTime updatedAt;
	private final OffsetDateTime previousUpdatedAt;
	
	public AttachmentUpdatedAtChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final Attachment attachment,
			final Attachment previousAttachment, final OffsetDateTime updatedAt,
			final OffsetDateTime previousUpdatedAt) {
		super(tournament, previousTournament, match, previousMatch, attachment, previousAttachment);
		this.updatedAt = updatedAt;
		this.previousUpdatedAt = previousUpdatedAt;
	}
	
	public OffsetDateTime getUpdatedAt() {
		return this.updatedAt;
	}
	
	public OffsetDateTime getPreviousUpdatedAt() {
		return this.previousUpdatedAt;
	}
}
