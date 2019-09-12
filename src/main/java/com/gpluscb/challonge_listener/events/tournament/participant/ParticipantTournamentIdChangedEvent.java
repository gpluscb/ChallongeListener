package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantTournamentIdChangedEvent extends GenericParticipantChangedEvent {
	private Long tournamentTournamentId;
	private Long previousTournamentId;
	
	public ParticipantTournamentIdChangedEvent(Tournament tournament, Tournament previousTournament,
			Participant participant, Participant previousParticipant, Long tournamentTournamentId,
			Long previousTournamentId) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.tournamentTournamentId = tournamentTournamentId;
		this.previousTournamentId = previousTournamentId;
	}
	
	public Long getTournamentId() {
		return tournamentTournamentId;
	}
	
	public Long getPreviousTournamentId() {
		return previousTournamentId;
	}
}
