package com.github.gpluscb.challonge_listener.events.tournament.match.attachment;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class AttachmentAssetContentTypeChangedEvent extends GenericAttachmentChangedEvent {
	private final String assetContentType;
	private final String previousAssetContentType;
	
	public AttachmentAssetContentTypeChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final Attachment attachment,
			final Attachment previousAttachment, final String assetContentType, final String previousAssetContentType) {
		super(tournament, previousTournament, match, previousMatch, attachment, previousAttachment);
		this.assetContentType = assetContentType;
		this.previousAssetContentType = previousAssetContentType;
	}
	
	public String getAssetContentType() {
		return this.assetContentType;
	}
	
	public String getPreviousAssetContentType() {
		return this.previousAssetContentType;
	}
}
