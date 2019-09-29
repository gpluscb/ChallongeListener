package com.gpluscb.challonge_listener.events.tournament.match.attachment;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class AttachmentAssetFileSizeChangedEvent extends GenericAttachmentChangedEvent {
	private final Long assetFileSize;
	private final Long previousAssetFileSize;
	
	public AttachmentAssetFileSizeChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final Attachment attachment,
			final Attachment previousAttachment, final Long assetFileSize, final Long previousAssetFileSize) {
		super(tournament, previousTournament, match, previousMatch, attachment, previousAttachment);
		this.assetFileSize = assetFileSize;
		this.previousAssetFileSize = previousAssetFileSize;
	}
	
	public Long getAssetFileSize() {
		return this.assetFileSize;
	}
	
	public Long getPreviousAssetFileSize() {
		return this.previousAssetFileSize;
	}
}
