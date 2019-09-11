package com.gpluscb.challonge_listener.events.tournament.match.attachment;

import java.time.OffsetDateTime;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class AttachmentCreatedAtChangedEvent extends GenericAttachmentChangedEvent {
	private OffsetDateTime createdAt;
	private OffsetDateTime previousCreatedAt;
	
	public AttachmentCreatedAtChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, Attachment attachment, Attachment previousAttachment, OffsetDateTime createdAt, OffsetDateTime previousCreatedAt) {
		super(tournament, previousTournament, match, previousMatch, attachment, previousAttachment);
		this.createdAt = createdAt;
		this.previousCreatedAt = previousCreatedAt;
	}
	
	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}
	
	public OffsetDateTime getPreviousCreatedAt() {
		return previousCreatedAt;
	}
}
