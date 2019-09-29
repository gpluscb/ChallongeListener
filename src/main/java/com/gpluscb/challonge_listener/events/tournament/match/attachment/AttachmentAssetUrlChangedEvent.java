package com.gpluscb.challonge_listener.events.tournament.match.attachment;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class AttachmentAssetUrlChangedEvent extends GenericAttachmentChangedEvent {
	private final String assetUrl;
	private final String previousAssetUrl;
	
	public AttachmentAssetUrlChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final Attachment attachment,
			final Attachment previousAttachment, final String assetUrl, final String previousAssetUrl) {
		super(tournament, previousTournament, match, previousMatch, attachment, previousAttachment);
		this.assetUrl = assetUrl;
		this.previousAssetUrl = previousAssetUrl;
	}
	
	public String getAssetUrl() {
		return this.assetUrl;
	}
	
	public String getPreviousAssetUrl() {
		return this.previousAssetUrl;
	}
}
