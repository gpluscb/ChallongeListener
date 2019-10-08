package com.github.gpluscb.challonge_listener.events.tournament.match.attachment;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class AttachmentCreatedAtChangedEvent extends GenericAttachmentChangedEvent {
	private final OffsetDateTime createdAt;
	private final OffsetDateTime previousCreatedAt;
	
	public AttachmentCreatedAtChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final Attachment attachment,
			final Attachment previousAttachment, final OffsetDateTime createdAt,
			final OffsetDateTime previousCreatedAt) {
		super(tournament, previousTournament, match, previousMatch, attachment, previousAttachment);
		this.createdAt = createdAt;
		this.previousCreatedAt = previousCreatedAt;
	}
	
	public OffsetDateTime getCreatedAt() {
		return this.createdAt;
	}
	
	public OffsetDateTime getPreviousCreatedAt() {
		return this.previousCreatedAt;
	}
}
