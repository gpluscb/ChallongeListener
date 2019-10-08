package com.github.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantUsernameChangedEvent extends GenericParticipantChangedEvent {
	private final String username;
	private final String previousUsername;
	
	public ParticipantUsernameChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final String username,
			final String previousUsername) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.username = username;
		this.previousUsername = previousUsername;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPreviousUsername() {
		return this.previousUsername;
	}
}
