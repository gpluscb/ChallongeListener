package com.github.gpluscb.challonge_listener.cache;

import at.stefangeyer.challonge.model.Attachment;

public class AttachmentCache {
	private final MatchCache match;
	
	private Attachment attachment;
	
	AttachmentCache(final MatchCache match, final Attachment attachment) {
		this.match = match;
		
		this.attachment = attachment;
	}
}
