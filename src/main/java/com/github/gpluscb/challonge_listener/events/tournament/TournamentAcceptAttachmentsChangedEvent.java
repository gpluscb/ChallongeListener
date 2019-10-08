package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentAcceptAttachmentsChangedEvent extends GenericTournamentChangedEvent {
	private final Boolean acceptAttachments;
	private final Boolean previousAcceptAttachments;
	
	public TournamentAcceptAttachmentsChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Boolean acceptAttachments, final Boolean previousAcceptAttachments) {
		super(tournament, previousTournament);
		this.acceptAttachments = acceptAttachments;
		this.previousAcceptAttachments = previousAcceptAttachments;
	}
	
	public Boolean getAcceptAttachments() {
		return this.acceptAttachments;
	}
	
	public Boolean getPreviousAcceptAttachments() {
		return this.previousAcceptAttachments;
	}
}
