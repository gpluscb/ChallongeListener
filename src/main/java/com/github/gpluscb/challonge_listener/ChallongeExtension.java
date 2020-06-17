package com.github.gpluscb.challonge_listener;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import at.stefangeyer.challonge.Challonge;
import at.stefangeyer.challonge.async.Callback;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Credentials;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.enumeration.MatchState;
import at.stefangeyer.challonge.model.enumeration.TournamentType;
import at.stefangeyer.challonge.model.query.enumeration.TournamentQueryState;
import at.stefangeyer.challonge.rest.RestClient;
import at.stefangeyer.challonge.serializer.Serializer;

/**
 * An extension of the {@link at.stefangeyer.challonge.Challonge Challonge}
 * class with a few utility methods mainly focused on methods to get tournaments
 * with complete data to the Attachment level.<br>
 * Note that those methods need to use multiple api requests and are therefore
 * more resource consuming.
 */
// TODO: This extension feature might be ambitious/stand-alone enough to warrant
// an own project
public class ChallongeExtension extends Challonge implements ChallongeExtensionService {
	/**
	 * Creates a completely functional instance. It can be used directly.
	 *
	 * @param credentials
	 *            The credentials used to log into the Challonge api
	 * @param serializer
	 *            The serializer used to deserialize the Challonge api json
	 *            responses
	 * @param restClient
	 *            The restClient to communicate with the Challonge api
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed
	 */
	public ChallongeExtension(final Credentials credentials, final Serializer serializer, final RestClient restClient)
			throws DataAccessException {
		super(credentials, serializer, restClient);
		// Tests whether the connection generally works so we can throw a
		// DataAccessException earlier for easier debugging and more consistent
		// failures.
		getTournaments();
	}
	
	@Override
	public boolean doesOwn(final Tournament tournament) throws DataAccessException {
		for(final Tournament ownedTournament : getTournaments()) {
			if(ownedTournament.getId().equals(tournament.getId())
					|| ownedTournament.getUrl().equals(tournament.getUrl())
							&& (ownedTournament.getSubdomain() == null ? tournament.getSubdomain() == null
									: ownedTournament.getSubdomain().equals(tournament.getSubdomain()))) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void doesOwn(final Tournament tournament, final Callback<Boolean> onSuccess,
			final Callback<DataAccessException> onFailure) {
		getTournaments(tournaments -> {
			for(final Tournament ownedTournament : tournaments) {
				if(ownedTournament.getId().equals(tournament.getId())
						|| ownedTournament.getUrl().equals(tournament.getUrl())
								&& (ownedTournament.getSubdomain() == null ? tournament.getSubdomain() == null
										: ownedTournament.getSubdomain().equals(tournament.getSubdomain()))) {
					onSuccess.accept(Boolean.TRUE);
					return;
				}
			}
			onSuccess.accept(Boolean.FALSE);
		}, onFailure);
	}
	
	@Override
	public Tournament getTournamentOrNull(final String tournament, final boolean includeParticipants,
			final boolean includeMatches, final boolean includeAttachments) throws DataAccessException {
		// TODO: make the check better and safer for api updates, no ideas yet.
		// getCause() with instanceof checks and casts so that there might be
		// some kind of getErrorCode() method?
		try {
			return getTournament(tournament, includeParticipants, includeMatches, includeAttachments);
		} catch(final DataAccessException e) {
			if(e.getMessage().contains(
					" was not successful (404) and returned: {\"errors\":[\"Requested tournament not found\"]}")) {
				return null;
			}
			throw e;
		}
	}
	
	@Override
	public void getTournamentOrNull(final String tournament, final boolean includeParticipants,
			final boolean includeMatches, final boolean includeAttachments, final Callback<Tournament> onSuccess,
			final Callback<DataAccessException> onFailure) {
		// TODO: make the check better and safer for api updates, no ideas yet.
		// getCause() with instanceof checks and casts so that there might be
		// some kind of getErrorCode() method?
		getTournament(tournament, includeParticipants, includeMatches, includeAttachments, onSuccess, e -> {
			if(e.getMessage().contains(
					" was not successful (404) and returned: {\"errors\":[\"Requested tournament not found\"]}")) {
				onSuccess.accept(null);
			} else {
				onFailure.accept(e);
			}
		});
	}
	
	@Override
	public Participant getParticipantOrNull(final Tournament tournament, final long participantId,
			final boolean includeMatches) throws DataAccessException {
		// TODO: make the check better and safer for api updates, no ideas yet.
		// getCause() with instanceof checks and casts so that there might be
		// some kind of getErrorCode() method?
		try {
			return getParticipant(tournament, participantId, includeMatches);
		} catch(final DataAccessException e) {
			if(e.getMessage().contains(
					" was not successful (404) and returned: {\"errors\":[\"Participant not found for tournament ID ")) {
				return null;
			}
			throw e;
		}
	}
	
	@Override
	public void getParticipantOrNull(final Tournament tournament, final long participantId,
			final boolean includeMatches, final Callback<Participant> onSuccess,
			final Callback<DataAccessException> onFailure) {
		// TODO: make the check better and safer for api updates, no ideas yet.
		// getCause() with instanceof checks and casts so that there might be
		// some kind of getErrorCode() method?
		getParticipant(tournament, participantId, includeMatches, onSuccess, e -> {
			if(e.getMessage().contains(
					" was not successful (404) and returned: {\"errors\":[\"Participant not found for tournament ID ")) {
				onSuccess.accept(null);
			} else {
				onFailure.accept(e);
			}
		});
	}
	
	@Override
	public Match getMatchOrNull(final Tournament tournament, final long matchId, final boolean includeAttachments)
			throws DataAccessException {
		// TODO: make the check better and safer for api updates, no ideas yet.
		// getCause() with instanceof checks and casts so that there might be
		// some kind of getErrorCode() method?
		try {
			return getMatch(tournament, matchId, includeAttachments);
		} catch(final DataAccessException e) {
			if(e.getMessage().contains(
					" was not successful (404) and returned: {\"errors\":[\"Match not found for tournament ID ")) {
				return null;
			}
			throw e;
		}
	}
	
	@Override
	public void getMatchOrNull(final Tournament tournament, final long matchId, final boolean includeAttachments,
			final Callback<Match> onSuccess, final Callback<DataAccessException> onFailure) {
		// TODO: make the check better and safer for api updates, no ideas yet.
		// getCause() with instanceof checks and casts so that there might be
		// some kind of getErrorCode() method?
		getMatch(tournament, matchId, includeAttachments, onSuccess, e -> {
			if(e.getMessage().contains(
					" was not successful (404) and returned: {\"errors\":[\"Match not found for tournament ID ")) {
				onSuccess.accept(null);
			} else {
				onFailure.accept(e);
			}
		});
	}
	
	@Override
	public Attachment getAttachmentOrNull(final Match match, final long attachmentId) throws DataAccessException {
		// TODO: make the check better and safer for api updates, no ideas yet.
		// getCause() with instanceof checks and casts so that there might be
		// some kind of getErrorCode() method?
		try {
			return getAttachment(match, attachmentId);
		} catch(final DataAccessException e) {
			if(e.getMessage().contains(
					" was not successful (404) and returned: {\"errors\":[\"Attachment with that ID was not found for match ID ")) {
				return null;
			}
			throw e;
		}
	}
	
	@Override
	public void getAttachmentOrNull(final Match match, final long attachmentId, final Callback<Attachment> onSuccess,
			final Callback<DataAccessException> onFailure) {
		getAttachment(match, attachmentId, onSuccess, e -> {
			if(e.getMessage().contains(
					" was not successful (404) and returned: {\"errors\":[\"Attachment with that ID was not found for match ID ")) {
				onSuccess.accept(null);
			} else {
				onFailure.accept(e);
			}
		});
	}
	
	@Override
	public Tournament getTournament(final String tournament, final boolean includeParticipants,
			final boolean includeMatches, final boolean includeAttachments) throws DataAccessException {
		if(!includeMatches && includeAttachments) {
			throw new IllegalArgumentException("Attachments can only be included if matches are included as well.");
		}
		
		final Tournament ret = getTournament(tournament, includeParticipants, includeMatches);
		
		// Matches need to be assigned to participants too
		if(includeParticipants && includeMatches) {
			for(final Participant participant : ret.getParticipants()) {
				assignMissingData(participant, ret.getMatches());
			}
		}
		
		// Add the attachments to the matches
		if(includeAttachments) {
			for(final Match match : ret.getMatches()) {
				addMissingData(match);
			}
		}
		
		return ret;
	}
	
	@Override
	public void getTournament(final String tournament, final boolean includeParticipants, final boolean includeMatches,
			final boolean includeAttachments, final Callback<Tournament> onSuccess,
			final Callback<DataAccessException> onFailure) {
		if(!includeMatches && includeAttachments) {
			throw new IllegalArgumentException("Attachments can only be included if matches are included as well.");
		}
		
		getTournament(tournament, includeParticipants, includeMatches, ret -> {
			try {
				// Matches need to be assigned to participants too
				if(includeParticipants && includeMatches) {
					for(final Participant participant : ret.getParticipants()) {
						assignMissingData(participant, ret.getMatches());
					}
				}
				
				// Add the attachments to the matches
				if(includeAttachments) {
					for(final Match match : ret.getMatches()) {
						addMissingData(match);
					}
				}

				onSuccess.accept(ret);
			} catch(final DataAccessException e) {
				onFailure.accept(e);
			}
		}, onFailure);
	}
	
	@Override
	public List<Tournament> getTournamentsWithFullData(final TournamentQueryState state, final TournamentType type,
			final OffsetDateTime createdAfter, final OffsetDateTime createdBefore, final String subdomain)
			throws DataAccessException {
		final List<Tournament> tournaments = getTournaments(state, type, createdAfter, createdBefore, subdomain);
		
		for(final Tournament tournament : tournaments) {
			addMissingData(tournament);
		}
		
		return tournaments;
	}
	
	@Override
	public void getTournamentsWithFullData(final TournamentQueryState state, final TournamentType type,
			final OffsetDateTime createdAfter, final OffsetDateTime createdBefore, final String subdomain,
			final Callback<List<Tournament>> onSuccess, final Callback<DataAccessException> onFailure) {
		getTournaments(state, type, createdAfter, createdBefore, tournaments -> {
			try {
				for(final Tournament tournament : tournaments) {
					addMissingData(tournament);
				}
				
				onSuccess.accept(tournaments);
			} catch(final DataAccessException e) {
				onFailure.accept(e);
			}
		}, onFailure);
	}
	
	@Override
	public List<Participant> getParticipantsWithFullData(final Tournament tournament) throws DataAccessException {
		final List<Participant> participants = getParticipants(tournament);
		
		for(final Participant participant : participants) {
			addMissingData(tournament, participant);
		}
		
		return participants;
	}
	
	@Override
	public void getParticipantsWithFullData(final Tournament tournament, final Callback<List<Participant>> onSuccess,
			final Callback<DataAccessException> onFailure) {
		getParticipants(tournament, participants -> {
			try {
				for(final Participant participant : participants) {
					addMissingData(tournament, participant);
				}
				
				onSuccess.accept(participants);
			} catch(final DataAccessException e) {
				onFailure.accept(e);
			}
		}, onFailure);
	}
	
	@Override
	public List<Match> getMatchesWithFullData(final Tournament tournament, final Participant participant,
			final MatchState state) throws DataAccessException {
		final List<Match> matches = getMatches(tournament, participant, state);
		
		for(final Match match : matches) {
			addMissingData(match);
		}
		
		return matches;
	}
	
	@Override
	public void getMatchesWithFullData(final Tournament tournament, final Participant participant,
			final MatchState state, final Callback<List<Match>> onSuccess,
			final Callback<DataAccessException> onFailure) {
		getMatches(tournament, participant, state, matches -> {
			try {
				for(final Match match : matches) {
					addMissingData(match);
				}
				
				onSuccess.accept(matches);
			} catch(final DataAccessException e) {
				onFailure.accept(e);
			}
		}, onFailure);
	}
	
	/**
	 * Adds missing participant, match and attachment information to the
	 * tournament
	 *
	 * @param tournament
	 *            The tournament to be added to. Must contain id or url with an
	 *            optional subdomain
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed. Can occur if
	 *             the tournament is significantly changed during the operation
	 */
	private void addMissingData(final Tournament tournament) throws DataAccessException {
		// Adding all matches
		if(tournament.getMatches() == null || tournament.getMatches().isEmpty()) {
			tournament.setMatches(getMatchesWithFullData(tournament));
		}
		
		// Adding all participants
		if(tournament.getParticipants() == null || tournament.getParticipants().isEmpty()) {
			tournament.setParticipants(getParticipants(tournament));
		}
		
		// Adding all attachments
		for(final Match match : tournament.getMatches()) {
			addMissingData(match);
		}
		
		// Adding all matches to the participants
		for(final Participant participant : tournament.getParticipants()) {
			assignMissingData(participant, tournament.getMatches());
		}
	}
	
	/**
	 * Applies the relevant of the given matches to the participant
	 *
	 * @param participant
	 *            The participant to be added to. Must contain the tournament-
	 *            and participant id
	 * @param matches
	 *            The matches to be filtered and applied to the participant
	 */
	private static void assignMissingData(final Participant participant, final List<Match> matches) {
		if(participant.getMatches() == null) {
			participant.setMatches(new ArrayList<>());
		}
		
		if(participant.getMatches().isEmpty()) {
			for(final Match match : matches) {
				if(participant.getId().equals(match.getPlayer1Id())
						|| participant.getId().equals(match.getPlayer2Id())) {
					participant.getMatches().add(match);
				}
			}
		}
	}
	
	/**
	 * Adds missing match information to the participant
	 *
	 * @param participant
	 *            The participant to be added to. Must contain the tournament-
	 *            and participant id
	 * @param tournament
	 *            The tournament of the participant
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed. Can occur if
	 *             the tournament is significantly changed during the operation
	 */
	private void addMissingData(final Tournament tournament, final Participant participant) throws DataAccessException {
		// Adding all matches
		if(participant.getMatches() == null || participant.getMatches().isEmpty()) {
			participant.setMatches(getMatches(tournament, participant));
		}
	}
	
	/**
	 * Adds missing attachment information to the match
	 *
	 * @param match
	 *            The match to be added to. Must contain the tournament- and
	 *            match id
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed
	 */
	private void addMissingData(final Match match) throws DataAccessException {
		// Adding all attachments
		if(match.getAttachments() == null) {
			match.setAttachments(new ArrayList<>());
		}
		
		if(match.getAttachments().isEmpty() && match.getAttachmentCount() != null
				&& match.getAttachmentCount().intValue() > 0) {
			match.setAttachments(getAttachments(match));
		}
	}
}
