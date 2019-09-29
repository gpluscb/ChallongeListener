package com.gpluscb.challonge_listener.events.tournament.match.attachment;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public abstract class GenericAttachmentChangedEvent extends GenericAttachmentEvent {
	private final Attachment previousAttachment;
	
	public GenericAttachmentChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final Attachment attachment,
			final Attachment previousAttachment) {
		super(tournament, previousTournament, match, previousMatch, attachment);
		this.previousAttachment = previousAttachment;
	}
	
	/**
	 * The attachment before it changed.
	 * 
	 * @return the previous attachment
	 */
	public Attachment getPreviousAttachment() {
		return this.previousAttachment;
	}
}
