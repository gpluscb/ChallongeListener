package com.github.gpluscb.challonge_listener.events.tournament;

import at.stefangeyer.challonge.model.Tournament;

public class TournamentAllowParticipantMatchReportingChangedEvent extends GenericTournamentChangedEvent {
	private final Boolean allowParticipantMatchReporting;
	private final Boolean previousAllowParticipantMatchReporting;
	
	public TournamentAllowParticipantMatchReportingChangedEvent(final Tournament tournament,
			final Tournament previousTournament, final Boolean allowParticipantMatchReporting,
			final Boolean previousAllowParticipantMatchReporting) {
		super(tournament, previousTournament);
		this.allowParticipantMatchReporting = allowParticipantMatchReporting;
		this.previousAllowParticipantMatchReporting = previousAllowParticipantMatchReporting;
	}
	
	public Boolean getAllowParticipantMatchReporting() {
		return this.allowParticipantMatchReporting;
	}
	
	public Boolean getPreviousAllowParticipantMatchReporting() {
		return this.previousAllowParticipantMatchReporting;
	}
}
