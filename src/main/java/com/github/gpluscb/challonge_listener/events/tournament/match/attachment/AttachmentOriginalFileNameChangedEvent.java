package com.github.gpluscb.challonge_listener.events.tournament.match.attachment;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class AttachmentOriginalFileNameChangedEvent extends GenericAttachmentChangedEvent {
	private final String originalFileName;
	private final String previousOriginalFileName;
	
	public AttachmentOriginalFileNameChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final Attachment attachment,
			final Attachment previousAttachment, final String originalFileName, final String previousOriginalFileName) {
		super(tournament, previousTournament, match, previousMatch, attachment, previousAttachment);
		this.originalFileName = originalFileName;
		this.previousOriginalFileName = previousOriginalFileName;
	}
	
	public String getOriginalFileName() {
		return this.originalFileName;
	}
	
	public String getPreviousOriginalFileName() {
		return this.previousOriginalFileName;
	}
}
