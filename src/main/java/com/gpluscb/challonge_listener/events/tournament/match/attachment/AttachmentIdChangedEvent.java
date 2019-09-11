package com.gpluscb.challonge_listener.events.tournament.match.attachment;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class AttachmentIdChangedEvent extends GenericAttachmentChangedEvent {
	private Long id;
	private Long previousId;
	
	public AttachmentIdChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, Attachment attachment, Attachment previousAttachment, Long id, Long previousId) {
		super(tournament, previousTournament, match, previousMatch, attachment, previousAttachment);
		this.id = id;
		this.previousId = previousId;
	}
	
	public Long getId() {
		return id;
	}
	
	public Long getPreviousId() {
		return previousId;
	}
}
