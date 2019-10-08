package com.github.gpluscb.challonge_listener.events.tournament.match.attachment;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class AttachmentAssetFileNameChangedEvent extends GenericAttachmentChangedEvent {
	private final String assetFileName;
	private final String previousAssetFileName;
	
	public AttachmentAssetFileNameChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final Attachment attachment,
			final Attachment previousAttachment, final String assetFileName, final String previousAssetFileName) {
		super(tournament, previousTournament, match, previousMatch, attachment, previousAttachment);
		this.assetFileName = assetFileName;
		this.previousAssetFileName = previousAssetFileName;
	}
	
	public String getAssetFileName() {
		return this.assetFileName;
	}
	
	public String getPreviousAssetFileName() {
		return this.previousAssetFileName;
	}
}
