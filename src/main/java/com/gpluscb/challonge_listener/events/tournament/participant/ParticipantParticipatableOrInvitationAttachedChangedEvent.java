package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantParticipatableOrInvitationAttachedChangedEvent extends GenericParticipantChangedEvent {
	private Boolean participatableOrInvitationAttached;
	private Boolean previousParticipatableOrInvitationAttached;
	
	public ParticipantParticipatableOrInvitationAttachedChangedEvent(Tournament tournament, Tournament previousTournament, Participant participant,
			Participant previousParticipant, Boolean participatableOrInvitationAttached, Boolean previousParticipatableOrInvitationAttached) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.participatableOrInvitationAttached = participatableOrInvitationAttached;
		this.previousParticipatableOrInvitationAttached = previousParticipatableOrInvitationAttached;
	}
	
	public Boolean getParticipatableOrInvitationAttached() {
		return participatableOrInvitationAttached;
	}
	
	public Boolean getPreviousParticipatableOrInvitationAttached() {
		return previousParticipatableOrInvitationAttached;
	}
}
