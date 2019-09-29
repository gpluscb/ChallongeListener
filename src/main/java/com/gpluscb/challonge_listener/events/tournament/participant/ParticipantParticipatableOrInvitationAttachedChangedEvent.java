package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantParticipatableOrInvitationAttachedChangedEvent extends GenericParticipantChangedEvent {
	private final Boolean participatableOrInvitationAttached;
	private final Boolean previousParticipatableOrInvitationAttached;
	
	public ParticipantParticipatableOrInvitationAttachedChangedEvent(final Tournament tournament,
			final Tournament previousTournament, final Participant participant, final Participant previousParticipant,
			final Boolean participatableOrInvitationAttached,
			final Boolean previousParticipatableOrInvitationAttached) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.participatableOrInvitationAttached = participatableOrInvitationAttached;
		this.previousParticipatableOrInvitationAttached = previousParticipatableOrInvitationAttached;
	}
	
	public Boolean getParticipatableOrInvitationAttached() {
		return this.participatableOrInvitationAttached;
	}
	
	public Boolean getPreviousParticipatableOrInvitationAttached() {
		return this.previousParticipatableOrInvitationAttached;
	}
}
