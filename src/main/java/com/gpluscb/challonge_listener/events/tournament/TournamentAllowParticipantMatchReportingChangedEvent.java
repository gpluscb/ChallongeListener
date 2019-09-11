package com.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentAllowParticipantMatchReportingChangedEvent extends GenericTournamentChangedEvent {
	private Boolean allowParticipantMatchReporting;
	private Boolean previousAllowParticipantMatchReporting;
	
	public TournamentAllowParticipantMatchReportingChangedEvent(Tournament tournament, Tournament previousTournament, Boolean allowParticipantMatchReporting, Boolean previousAllowParticipantMatchReporting) {
		super(tournament, previousTournament);
		this.allowParticipantMatchReporting = allowParticipantMatchReporting;
		this.previousAllowParticipantMatchReporting = previousAllowParticipantMatchReporting;
	}
	
	public Boolean getAllowParticipantMatchReporting() {
		return allowParticipantMatchReporting;
	}
	
	public Boolean getPreviousAllowParticipantMatchReporting() {
		return previousAllowParticipantMatchReporting;
	}
}
