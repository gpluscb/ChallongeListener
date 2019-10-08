package com.github.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantOnWaitingListChangedEvent extends GenericParticipantChangedEvent {
	private final Boolean onWaitingList;
	private final Boolean previousOnWaitingList;
	
	public ParticipantOnWaitingListChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final Boolean onWaitingList,
			final Boolean previousOnWaitingList) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.onWaitingList = onWaitingList;
		this.previousOnWaitingList = previousOnWaitingList;
	}
	
	public Boolean getOnWaitingList() {
		return this.onWaitingList;
	}
	
	public Boolean getPreviousOnWaitingList() {
		return this.previousOnWaitingList;
	}
}
