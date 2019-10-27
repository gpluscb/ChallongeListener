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
public class MatchCache {
	private final TournamentCache tournament;
	
	private Match match;
	
	private final List<AttachmentCache> attachments;
	private final List<ParticipantCache> participants;
	
	MatchCache(final TournamentCache tournament, final Match match) {
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
	 */
	public TournamentCache getTournament() {
		return this.tournament;
	}
	
	/**
	 * Gets the managed {@link at.stefangeyer.challonge.model.Match Match}.
	 * 
	 * @return The managed match
	 */
	public Match getMatch() {
		return this.match;
	}
	
	/**
	 * Gets an attachment with the given id.
	 * 
	 * @param attachmentId
	 *            The id of the attachment
	 * @return The attachment with the given id or null if no such attachment
	 *             exists
	 */
	public AttachmentCache getAttachmentById(final long attachmentId) {
		for(final AttachmentCache attachment : this.attachments) {
			if(attachment.getAttachment().getId().longValue() == attachmentId) {
				return attachment;
			}
		}
		return null;
	}
	
	/**
	 * Gets the managed {@link AttachmentCache AttachmentCaches}.
	 * 
	 * @return the managed attachment caches
	 */
	public List<AttachmentCache> getAttachments() {
		return this.attachments;
	}
	
	/**
	 * Gets an participant of this match with the given id.
	 * 
	 * @param participantId
	 *            The id of the participant
	 * @return The participant with the given id or null if no such participant
	 *             exists
	 */
	public ParticipantCache getParticipantById(final long participantId) {
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
	 */
	public List<ParticipantCache> getParticipants() {
		return Collections.unmodifiableList(this.participants);
	}
	
	void update(final Match match) {
		this.match = match;
		
		final List<AttachmentCache> notHandledAttachments = new ArrayList<>(this.attachments);
		for(final Attachment attachment : this.match.getAttachments()) {
			final AttachmentCache cache = getAttachmentById(attachment.getId().longValue());
			if(cache == null) {
				// New attachment
				this.attachments.add(new AttachmentCache(this, attachment));
			} else {
				cache.update(attachment);
				
				// Now handled
				notHandledAttachments.remove(cache);
			}
		}
		// Not present in given matches attachments
		for(final AttachmentCache toDelete : notHandledAttachments) {
			this.attachments.remove(toDelete);
		}
		
		// Clearing all links, they are re-initiated in participants update
		this.participants.clear();
	}
	
	void addParticipant(final ParticipantCache participant) {
		this.participants.add(participant);
	}
}
