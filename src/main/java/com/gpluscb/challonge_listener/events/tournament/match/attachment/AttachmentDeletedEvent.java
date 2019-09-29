package com.gpluscb.challonge_listener.events.tournament.match.attachment;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class AttachmentDeletedEvent extends GenericAttachmentEvent {
	public AttachmentDeletedEvent(final Tournament tournament, final Tournament previousTournament, final Match match,
			final Match previousMatch, final Attachment attachment) {
		super(tournament, previousTournament, match, previousMatch, attachment);
	}
}
