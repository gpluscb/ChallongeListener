package com.gpluscb.challonge_listener.events.tournament.match.attachment;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;
import com.gpluscb.challonge_listener.events.tournament.match.GenericMatchChangedEvent;

public abstract class GenericAttachmentEvent extends GenericMatchChangedEvent {
	private Attachment attachment;
	
	public GenericAttachmentEvent(Tournament tournament, Tournament previousTournament, Match match, Match previousMatch,
			Attachment attachment) {
		super(tournament, previousTournament, match, previousMatch);
		this.attachment = attachment;
	}
	
	public Attachment getAttachment() {
		return attachment;
	}
}
