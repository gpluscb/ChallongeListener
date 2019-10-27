package com.github.gpluscb.challonge_listener.cache;

import java.util.ArrayList;
import java.util.List;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;

public class MatchCache {
	private final TournamentCache tournament;
	
	private Match match;
	
	private final List<AttachmentCache> attachments;
	
	MatchCache(final TournamentCache tournament, final Match match) {
		this.tournament = tournament;
		
		this.match = match;
		
		this.attachments = new ArrayList<>();
		
		for(final Attachment attachment : match.getAttachments()) {
			this.attachments.add(new AttachmentCache(this, attachment));
		}
	}
}
