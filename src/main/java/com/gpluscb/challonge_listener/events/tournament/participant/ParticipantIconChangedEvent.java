package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantIconChangedEvent extends GenericParticipantChangedEvent {
	private String icon;
	private String previousIcon;
	
	public ParticipantIconChangedEvent(Tournament tournament, Tournament previousTournament, Participant participant,
			Participant previousParticipant, String icon, String previousIcon) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.icon = icon;
		this.previousIcon = previousIcon;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public String getPreviousIcon() {
		return previousIcon;
	}
}
