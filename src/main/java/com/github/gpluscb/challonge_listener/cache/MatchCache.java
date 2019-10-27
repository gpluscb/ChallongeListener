package com.github.gpluscb.challonge_listener.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;

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
	
	public TournamentCache getTournament() {
		return this.tournament;
	}
	
	public Match getMatch() {
		return this.match;
	}
	
	public AttachmentCache getAttachmentById(final long attachmentId) {
		for(final AttachmentCache attachment : this.attachments) {
			if(attachment.getAttachment().getId().longValue() == attachmentId) {
				return attachment;
			}
		}
		return null;
	}
	
	public List<AttachmentCache> getAttachments() {
		return this.attachments;
	}
	
	public ParticipantCache getParticipantById(final long participantId) {
		for(final ParticipantCache participant : this.participants) {
			if(participant.getParticipant().getId().longValue() == participantId) {
				return participant;
			}
		}
		return null;
	}
	
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
