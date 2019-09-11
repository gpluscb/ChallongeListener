package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantAttachedParticipatablePortraitUrlChangedEvent extends GenericParticipantChangedEvent {
	private String attachedParticipatablePortraitUrl;
	private String previousAttachedParticipatablePortraitUrl;
	
	public ParticipantAttachedParticipatablePortraitUrlChangedEvent(Tournament tournament, Tournament previousTournament, Participant participant,
			Participant previousParticipant, String attachedParticipatablePortraitUrl, String previousAttachedParticipatablePortraitUrl) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.attachedParticipatablePortraitUrl = attachedParticipatablePortraitUrl;
		this.previousAttachedParticipatablePortraitUrl = previousAttachedParticipatablePortraitUrl;
	}
	
	public String getAttachedParticipatablePortraitUrl() {
		return attachedParticipatablePortraitUrl;
	}
	
	public String getPreviousAttachedParticipatablePortraitUrl() {
		return previousAttachedParticipatablePortraitUrl;
	}
}
