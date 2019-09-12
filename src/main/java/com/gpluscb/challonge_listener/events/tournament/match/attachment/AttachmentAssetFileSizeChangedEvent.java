package com.gpluscb.challonge_listener.events.tournament.match.attachment;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class AttachmentAssetFileSizeChangedEvent extends GenericAttachmentChangedEvent {
	private Long assetFileSize;
	private Long previousAssetFileSize;
	
	public AttachmentAssetFileSizeChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, Attachment attachment, Attachment previousAttachment, Long assetFileSize,
			Long previousAssetFileSize) {
		super(tournament, previousTournament, match, previousMatch, attachment, previousAttachment);
		this.assetFileSize = assetFileSize;
		this.previousAssetFileSize = previousAssetFileSize;
	}
	
	public Long getAssetFileSize() {
		return assetFileSize;
	}
	
	public Long getPreviousAssetFileSize() {
		return previousAssetFileSize;
	}
}
