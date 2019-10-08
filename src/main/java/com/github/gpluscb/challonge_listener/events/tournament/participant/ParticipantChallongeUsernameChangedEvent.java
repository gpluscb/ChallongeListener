package com.github.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantChallongeUsernameChangedEvent extends GenericParticipantChangedEvent {
	private final String challongeUsername;
	private final String previousChallongeUsername;
	
	public ParticipantChallongeUsernameChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final String challongeUsername,
			final String previousChallongeUsername) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.challongeUsername = challongeUsername;
		this.previousChallongeUsername = previousChallongeUsername;
	}
	
	public String getChallongeUsername() {
		return this.challongeUsername;
	}
	
	public String getPreviousChallongeUsername() {
		return this.previousChallongeUsername;
	}
}
