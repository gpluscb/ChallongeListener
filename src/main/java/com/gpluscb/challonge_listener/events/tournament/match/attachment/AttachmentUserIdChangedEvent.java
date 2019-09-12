package com.gpluscb.challonge_listener.events.tournament.match.attachment;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class AttachmentUserIdChangedEvent extends GenericAttachmentChangedEvent {
	private Long userUserId;
	private Long previousUserId;
	
	public AttachmentUserIdChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, Attachment attachment, Attachment previousAttachment, Long userUserId,
			Long previousUserId) {
		super(tournament, previousTournament, match, previousMatch, attachment, previousAttachment);
		this.userUserId = userUserId;
		this.previousUserId = previousUserId;
	}
	
	public Long getUserId() {
		return userUserId;
	}
	
	public Long getPreviousUserId() {
		return previousUserId;
	}
}
