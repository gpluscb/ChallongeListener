package com.gpluscb.challonge_listener.events.tournament.match.attachment;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class AttachmentAssetContentTypeChangedEvent extends GenericAttachmentChangedEvent {
	private String assetContentType;
	private String previousAssetContentType;
	
	public AttachmentAssetContentTypeChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, Attachment attachment, Attachment previousAttachment, String assetContentType,
			String previousAssetContentType) {
		super(tournament, previousTournament, match, previousMatch, attachment, previousAttachment);
		this.assetContentType = assetContentType;
		this.previousAssetContentType = previousAssetContentType;
	}
	
	public String getAssetContentType() {
		return assetContentType;
	}
	
	public String getPreviousAssetContentType() {
		return previousAssetContentType;
	}
}
