package com.github.gpluscb.challonge_listener.events.tournament.match.attachment;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class AttachmentIdChangedEvent extends GenericAttachmentChangedEvent {
	private final Long id;
	private final Long previousId;
	
	public AttachmentIdChangedEvent(final Tournament tournament, final Tournament previousTournament, final Match match,
			final Match previousMatch, final Attachment attachment, final Attachment previousAttachment, final Long id,
			final Long previousId) {
		super(tournament, previousTournament, match, previousMatch, attachment, previousAttachment);
		this.id = id;
		this.previousId = previousId;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public Long getPreviousId() {
		return this.previousId;
	}
}
