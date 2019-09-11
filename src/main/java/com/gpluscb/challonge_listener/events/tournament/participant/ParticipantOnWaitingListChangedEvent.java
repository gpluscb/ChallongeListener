package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantOnWaitingListChangedEvent extends GenericParticipantChangedEvent {
	private Boolean onWaitingList;
	private Boolean previousOnWaitingList;
	
	public ParticipantOnWaitingListChangedEvent(Tournament tournament, Tournament previousTournament, Participant participant,
			Participant previousParticipant, Boolean onWaitingList, Boolean previousOnWaitingList) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.onWaitingList = onWaitingList;
		this.previousOnWaitingList = previousOnWaitingList;
	}
	
	public Boolean getOnWaitingList() {
		return onWaitingList;
	}
	
	public Boolean getPreviousOnWaitingList() {
		return previousOnWaitingList;
	}
}
