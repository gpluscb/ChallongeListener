package com.gpluscb.challonge_listener.events.tournament.match.attachment;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class AttachmentMatchIdChangedEvent extends GenericAttachmentChangedEvent {
	private Long matchMatchId;
	private Long previousMatchId;
	
	public AttachmentMatchIdChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, Attachment attachment, Attachment previousAttachment, Long matchMatchId,
			Long previousMatchId) {
		super(tournament, previousTournament, match, previousMatch, attachment, previousAttachment);
		this.matchMatchId = matchMatchId;
		this.previousMatchId = previousMatchId;
	}
	
	public Long getMatchId() {
		return matchMatchId;
	}
	
	public Long getPreviousMatchId() {
		return previousMatchId;
	}
}
