package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentAcceptAttachmentsChangedEvent extends GenericTournamentChangedEvent {
	private Boolean acceptAttachments;
	private Boolean previousAcceptAttachments;
	
	public TournamentAcceptAttachmentsChangedEvent(Tournament tournament, Tournament previousTournament,
			Boolean acceptAttachments, Boolean previousAcceptAttachments) {
		super(tournament, previousTournament);
		this.acceptAttachments = acceptAttachments;
		this.previousAcceptAttachments = previousAcceptAttachments;
	}
	
	public Boolean getAcceptAttachments() {
		return acceptAttachments;
	}
	
	public Boolean getPreviousAcceptAttachments() {
		return previousAcceptAttachments;
	}
}
