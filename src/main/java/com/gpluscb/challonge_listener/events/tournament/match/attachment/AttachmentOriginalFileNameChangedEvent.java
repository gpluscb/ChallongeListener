package com.gpluscb.challonge_listener.events.tournament.match.attachment;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class AttachmentOriginalFileNameChangedEvent extends GenericAttachmentChangedEvent {
	private String originalFileName;
	private String previousOriginalFileName;
	
	public AttachmentOriginalFileNameChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, Attachment attachment, Attachment previousAttachment, String originalFileName,
			String previousOriginalFileName) {
		super(tournament, previousTournament, match, previousMatch, attachment, previousAttachment);
		this.originalFileName = originalFileName;
		this.previousOriginalFileName = previousOriginalFileName;
	}
	
	public String getOriginalFileName() {
		return originalFileName;
	}
	
	public String getPreviousOriginalFileName() {
		return previousOriginalFileName;
	}
}
