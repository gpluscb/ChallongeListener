package com.gpluscb.challonge_listener.events.tournament.match.attachment;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class AttachmentAssetUrlChangedEvent extends GenericAttachmentChangedEvent {
	private String assetUrl;
	private String previousAssetUrl;
	
	public AttachmentAssetUrlChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, Attachment attachment, Attachment previousAttachment, String assetUrl, String previousAssetUrl) {
		super(tournament, previousTournament, match, previousMatch, attachment, previousAttachment);
		this.assetUrl = assetUrl;
		this.previousAssetUrl = previousAssetUrl;
	}
	
	public String getAssetUrl() {
		return assetUrl;
	}
	
	public String getPreviousAssetUrl() {
		return previousAssetUrl;
	}
}
