package com.github.gpluscb.challonge_listener.cache;

import at.stefangeyer.challonge.model.Attachment;

/**
 * Manages a single {@link at.stefangeyer.challonge.model.Attachment Attachment}
 * as a cache.
 */
public class AttachmentCache {
	private final MatchCache match;
	
	private Attachment attachment;
	
	AttachmentCache(final MatchCache match, final Attachment attachment) {
		this.match = match;
		
		this.attachment = attachment;
	}
	
	/**
	 * Gets the {@link MatchCache} this cache belongs to.
	 * 
	 * @return The {@link MatchCache} that owns this cache
	 */
	public MatchCache getMatch() {
		return this.match;
	}
	
	/**
	 * Gets the managed {@link at.stefangeyer.challonge.model.Attachment
	 * Attachment}.
	 * 
	 * @return The managed attachment
	 */
	public Attachment getAttachment() {
		return this.attachment;
	}
	
	void update(final Attachment attachment) {
		this.attachment = attachment;
	}
}
