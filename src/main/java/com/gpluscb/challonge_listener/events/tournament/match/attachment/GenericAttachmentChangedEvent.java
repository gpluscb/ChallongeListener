package com.gpluscb.challonge_listener.events.tournament.match.attachment;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public abstract class GenericAttachmentChangedEvent extends GenericAttachmentEvent {
	private Attachment previousAttachment;
	
	public GenericAttachmentChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, Attachment attachment, Attachment previousAttachment) {
		super(tournament, previousTournament, match, previousMatch, attachment);
		this.previousAttachment = previousAttachment;
	}
	
	public Attachment getPreviousAttachment() {
		return previousAttachment;
	}
}
