package com.gpluscb.challonge_listener.events.tournament.participant;

import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;

public class ParticipantTournamentIdChangedEvent extends GenericParticipantChangedEvent {
	private final Long tournamentTournamentId;
	private final Long previousTournamentId;
	
	public ParticipantTournamentIdChangedEvent(final Tournament tournament, final Tournament previousTournament,
			final Participant participant, final Participant previousParticipant, final Long tournamentTournamentId,
			final Long previousTournamentId) {
		super(tournament, previousTournament, participant, previousParticipant);
		this.tournamentTournamentId = tournamentTournamentId;
		this.previousTournamentId = previousTournamentId;
	}
	
	public Long getTournamentId() {
		return this.tournamentTournamentId;
	}
	
	public Long getPreviousTournamentId() {
		return this.previousTournamentId;
	}
}
