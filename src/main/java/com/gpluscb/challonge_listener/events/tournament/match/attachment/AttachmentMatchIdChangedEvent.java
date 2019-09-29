package com.gpluscb.challonge_listener.events.tournament.match.attachment;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class AttachmentMatchIdChangedEvent extends GenericAttachmentChangedEvent {
	private final Long matchMatchId;
	private final Long previousMatchId;
	
	public AttachmentMatchIdChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Match match, final Match previousMatch, final Attachment attachment,
			final Attachment previousAttachment, final Long matchMatchId, final Long previousMatchId) {
		super(tournament, previousTournament, match, previousMatch, attachment, previousAttachment);
		this.matchMatchId = matchMatchId;
		this.previousMatchId = previousMatchId;
	}
	
	public Long getMatchId() {
		return this.matchMatchId;
	}
	
	public Long getPreviousMatchId() {
		return this.previousMatchId;
	}
}
