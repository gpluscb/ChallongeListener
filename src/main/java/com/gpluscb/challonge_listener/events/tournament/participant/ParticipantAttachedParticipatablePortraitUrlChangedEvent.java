package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantAttachedParticipatablePortraitUrlChangedEvent extends GenericParticipantChangedEvent {
	private final String attachedParticipatablePortraitUrl;
	private final String previousAttachedParticipatablePortraitUrl;
	
	public ParticipantAttachedParticipatablePortraitUrlChangedEvent(final Tournament tournament,
			final Tournament previousTournament, final Participant participant, final Participant previousParticipant,
			final String attachedParticipatablePortraitUrl, final String previousAttachedParticipatablePortraitUrl) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.attachedParticipatablePortraitUrl = attachedParticipatablePortraitUrl;
		this.previousAttachedParticipatablePortraitUrl = previousAttachedParticipatablePortraitUrl;
	}
	
	public String getAttachedParticipatablePortraitUrl() {
		return this.attachedParticipatablePortraitUrl;
	}
	
	public String getPreviousAttachedParticipatablePortraitUrl() {
		return this.previousAttachedParticipatablePortraitUrl;
	}
}
