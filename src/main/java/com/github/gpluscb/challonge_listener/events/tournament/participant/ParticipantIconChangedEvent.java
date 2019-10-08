package com.github.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantIconChangedEvent extends GenericParticipantChangedEvent {
	private final String icon;
	private final String previousIcon;
	
	public ParticipantIconChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final String icon,
			final String previousIcon) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.icon = icon;
		this.previousIcon = previousIcon;
	}
	
	public String getIcon() {
		return this.icon;
	}
	
	public String getPreviousIcon() {
		return this.previousIcon;
	}
}
