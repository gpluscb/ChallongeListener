package com.github.gpluscb.challonge_listener.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;

/**
 * Manages a single {@link at.stefangeyer.challonge.model.Match Match} as a
 * cache.
 */
public class MatchCache extends Cache<Match> {
	private final TournamentCache tournament;
	
	private Match match;
	
	private final List<AttachmentCache> attachments;
	private final List<ParticipantCache> participants;
	
	/**
	 * Creates a new cache of the given match.
	 * 
	 * @param tournament
	 *            The tournament cache this cache is managed by
	 * @param match
	 *            The match the cache manages
	 */
	public MatchCache(final TournamentCache tournament, final Match match) {
		this.tournament = tournament;
		
		this.match = match;
		
		this.attachments = new ArrayList<>();
		this.participants = new ArrayList<>();
		
		for(final Attachment attachment : this.match.getAttachments()) {
			this.attachments.add(new AttachmentCache(this, attachment));
		}
	}
	
	/**
	 * Gets the {@link TournamentCache} this cache belongs to.
	 * 
	 * @return The {@link TournamentCache} that owns this cache
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public TournamentCache getTournament() {
		checkValidity();
		return this.tournament;
	}
	
	/**
	 * Gets the managed {@link at.stefangeyer.challonge.model.Match Match}.
	 * 
	 * @return The managed match
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public Match getMatch() {
		checkValidity();
		return this.match;
	}
	
	/**
	 * Gets an attachment with the given id.
	 * 
	 * @param attachmentId
	 *            The id of the attachment
	 * @return The attachment with the given id or null if no such attachment
	 *             exists
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public AttachmentCache getAttachmentById(final long attachmentId) {
		checkValidity();
		final int index = getAttachmentIndexById(attachmentId);
		return index < 0 ? null : this.attachments.get(index);
	}
	
	/**
	 * Gets the managed {@link AttachmentCache AttachmentCaches}.
	 * 
	 * @return the managed attachment caches
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public List<AttachmentCache> getAttachments() {
		checkValidity();
		return this.attachments;
	}
	
	/**
	 * Adds the given attachment to this cache.
	 * 
	 * @param attachment
	 *            The attachment to manage
	 * @return The created cache
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public AttachmentCache newAttachment(final Attachment attachment) {
		checkValidity();
		final AttachmentCache cache = new AttachmentCache(this, attachment);
		this.attachments.add(cache);
		return cache;
	}
	
	/**
	 * Removes the given attachment cache from this cache if it was managed by
	 * this cache. In that case, the given cache will be invalidated.
	 * 
	 * @param attachment
	 *            The attachment cache to remove
	 * @return Whether the given cache was managed by this cache
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public boolean deleteAttachment(final AttachmentCache attachment) {
		checkValidity();
		if(this.attachments.remove(attachment)) {
			attachment.invalidate();
			return true;
		}
		return false;
	}
	
	/**
	 * Removes the cache of the given attachment from this cache if it was
	 * managed by this cache. In that case, that cache will be invalidated.
	 * 
	 * @param attachment
	 *            The attachment cache to remove
	 * @return Whether the given cache was managed by this cache
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public boolean deleteAttachment(final Attachment attachment) {
		checkValidity();
		final int index = getAttachmentIndexById(attachment.getId().longValue());
		if(index < 0) {
			return false;
		}
		this.attachments.remove(index);
		return true;
	}
	
	private int getAttachmentIndexById(final long attachmentId) {
		for(int i = 0; i < this.attachments.size(); i++) {
			if(this.attachments.get(i).getAttachment().getId().longValue() == attachmentId) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Gets an participant of this match with the given id.
	 * 
	 * @param participantId
	 *            The id of the participant
	 * @return The participant with the given id or null if no such participant
	 *             exists
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public ParticipantCache getParticipantById(final long participantId) {
		checkValidity();
		for(final ParticipantCache participant : this.participants) {
			if(participant.getParticipant().getId().longValue() == participantId) {
				return participant;
			}
		}
		return null;
	}
	
	/**
	 * Gets the linked {@link ParticipantCache ParticipantCaches}.
	 * 
	 * @return the linked participant caches
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public List<ParticipantCache> getParticipants() {
		checkValidity();
		return Collections.unmodifiableList(this.participants);
	}
	
	/**
	 * Links the given participant cache to this cache.
	 * 
	 * @param participant
	 *            The attachment cache to link
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public void linkParticipant(final ParticipantCache participant) {
		checkValidity();
		this.participants.add(participant);
	}
	
	/**
	 * Unlinks the given participant cache from this cache. This will not
	 * invalidate the given cache.
	 * 
	 * @param participant
	 *            The participant cache to remove
	 * @return Whether the given cache was linked to this cache
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	public boolean unlinkParticipant(final ParticipantCache participant) {
		checkValidity();
		return this.participants.remove(participant);
	}
	
	/**
	 * Updates this cache with the given match.
	 * 
	 * @param match
	 *            The new updated match
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	@Override
	public void update(final Match match) {
		checkValidity();
		this.match = match;
		
		final List<AttachmentCache> notHandledAttachments = new ArrayList<>(this.attachments);
		for(final Attachment attachment : this.match.getAttachments()) {
			final AttachmentCache cache = getAttachmentById(attachment.getId().longValue());
			if(cache == null) {
				// New attachment
				newAttachment(attachment);
			} else {
				cache.update(attachment);
				
				// Now handled
				notHandledAttachments.remove(cache);
			}
		}
		// Not present in given matches attachments
		for(final AttachmentCache toDelete : notHandledAttachments) {
			deleteAttachment(toDelete);
		}
		
		// Clearing all links, they are re-initiated in participants update
		this.participants.clear();
	}
}
