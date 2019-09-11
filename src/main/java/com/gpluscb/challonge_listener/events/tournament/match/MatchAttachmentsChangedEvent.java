package com.gpluscb.challonge_listener.events.tournament.match;

import java.util.List;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Tournament;

public class MatchAttachmentsChangedEvent extends GenericMatchChangedEvent {
	private List<Attachment> attachments;
	private List<Attachment> previousAttachments;
	
	public MatchAttachmentsChangedEvent(Tournament tournament, Tournament previousTournament, Match match,
			Match previousMatch, List<Attachment> attachments, List<Attachment> previousAttachments) {
		super(tournament, previousTournament, match, previousMatch);
		this.attachments = attachments;
		this.previousAttachments = previousAttachments;
	}
	
	public List<Attachment> getAttachments() {
		return attachments;
	}
	
	public List<Attachment> getPreviousAttachments() {
		return previousAttachments;
	}
}
