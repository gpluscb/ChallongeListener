package com.gpluscb.challonge_listener.events.tournament.match.attachment;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class AttachmentUserIdChangedEvent extends GenericAttachmentChangedEvent {
	private final Long userUserId;
	private final Long previousUserId;
	
	public AttachmentUserIdChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final Attachment attachment,
			final Attachment previousAttachment, final Long userUserId, final Long previousUserId) {
		super(tournament, previousTournament, match, previousMatch, attachment, previousAttachment);
		this.userUserId = userUserId;
		this.previousUserId = previousUserId;
	}
	
	public Long getUserId() {
		return this.userUserId;
	}
	
	public Long getPreviousUserId() {
		return this.previousUserId;
	}
}
