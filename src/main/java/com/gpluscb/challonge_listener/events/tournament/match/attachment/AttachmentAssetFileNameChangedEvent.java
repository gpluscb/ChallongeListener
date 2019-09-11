package com.gpluscb.challonge_listener.events.tournament.match.attachment;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class AttachmentAssetFileNameChangedEvent extends GenericAttachmentChangedEvent {
	private String assetFileName;
	private String previousAssetFileName;
	
	public AttachmentAssetFileNameChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, Attachment attachment, Attachment previousAttachment, String assetFileName, String previousAssetFileName) {
		super(tournament, previousTournament, match, previousMatch, attachment, previousAttachment);
		this.assetFileName = assetFileName;
		this.previousAssetFileName = previousAssetFileName;
	}
	
	public String getAssetFileName() {
		return assetFileName;
	}
	
	public String getPreviousAssetFileName() {
		return previousAssetFileName;
	}
}
