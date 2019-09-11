package com.gpluscb.challonge_listener.events.tournament.match.attachment;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class AttachmentCreatedEvent extends GenericAttachmentEvent {
	public AttachmentCreatedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, Attachment attachment) {
		super(tournament, previousTournament, match, previousMatch, attachment);
	}
}
