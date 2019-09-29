package com.gpluscb.challonge_listener.events.tournament.match.attachment;

import com.gpluscb.challonge_listener.events.tournament.match.GenericMatchChangedEvent;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public abstract class GenericAttachmentEvent extends GenericMatchChangedEvent {
	private final Attachment attachment;
	
	public GenericAttachmentEvent(final Tournament tournament, final Tournament previousTournament, final Match match,
			final Match previousMatch, final Attachment attachment) {
		super(tournament, previousTournament, match, previousMatch);
		this.attachment = attachment;
	}
	
	/**
	 * The attachment primarily associated with this event.
	 * 
	 * @return The primary attachment of this event
	 */
	public Attachment getAttachment() {
		return this.attachment;
	}
}
