package com.github.gpluscb.challonge_listener.cache;

import at.stefangeyer.challonge.model.Attachment;

/**
 * Manages a single {@link at.stefangeyer.challonge.model.Attachment Attachment}
 * as a cache.
 */
public class AttachmentCache extends Cache<Attachment> {
	private final MatchCache match;
	
	private Attachment attachment;
	
	/**
	 * Creates a new cache of the given attachment.
	 * 
	 * @param match
	 *            The match cache this cache is managed by
	 * @param attachment
	 *            The attachment the cache manages
	 */
	public AttachmentCache(final MatchCache match, final Attachment attachment) {
		this.match = match;
		
		this.attachment = attachment;
	}
	
	/**
	 * Gets the {@link MatchCache} this cache belongs to.
	 * 
	 * @return The {@link MatchCache} that owns this cache
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public MatchCache getMatch() {
		checkValidity();
		return this.match;
	}
	
	/**
	 * Gets the managed {@link at.stefangeyer.challonge.model.Attachment
	 * Attachment}.
	 * 
	 * @return The managed attachment
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public Attachment getAttachment() {
		checkValidity();
		return this.attachment;
	}
	
	/**
	 * Updates this cache with the given attachment.
	 * 
	 * @param attachment
	 *            The new updated list of attachment
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	@Override
	public void update(final Attachment attachment) {
		checkValidity();
		this.attachment = attachment;
	}
}
