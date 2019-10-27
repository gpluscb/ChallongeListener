package com.github.gpluscb.challonge_listener.cache;

import at.stefangeyer.challonge.model.Attachment;

public class AttachmentCache {
	private final MatchCache match;
	
	private Attachment attachment;
	
	AttachmentCache(final MatchCache match, final Attachment attachment) {
		this.match = match;
		
		this.attachment = attachment;
	}
	
	public MatchCache getMatch() {
		return this.match;
	}
	
	public Attachment getAttachment() {
		return this.attachment;
	}
	
	void update(Attachment attachment) {
		// TODO
	}
}
