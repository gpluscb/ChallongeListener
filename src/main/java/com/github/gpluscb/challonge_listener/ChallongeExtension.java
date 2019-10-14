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
// TODO: Additional methods should support async
public class ChallongeExtension extends Challonge {
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
	
	/**
	 * Checks whether a tournament exists.
	 *
	 * @param tournament
	 *            Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for
	 *            challonge.com/single_elim). If assigned to a subdomain, URL
	 *            format must be :subdomain-:tournament_url (e.g.
	 *            'test-mytourney' for test.challonge.com/mytourney)
	 * @return Whether the tournament exists
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed in a way that
	 *             does not indicate that the tournament does not exist
	 */
	public boolean doesTournamentExist(final String tournament) throws DataAccessException {
		return getTournamentOrNull(tournament) != null;
	}
	
	/**
	 * Checks whether a match exists.
	 *
	 * @param tournament
	 *            The matches tournament. Must contain id or url with an
	 *            optional subdomain
	 * @param matchId
	 *            The id of the match to be checked
	 * @return Whether the match exists
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed in a way that
	 *             does not indicate that the match does not exist
	 */
	public boolean doesMatchExist(final Tournament tournament, final long matchId) throws DataAccessException {
		return getMatchOrNull(tournament, matchId) != null;
	}
	
	/**
	 * Checks whether a participant exists.
	 *
	 * @param tournament
	 *            The matches tournament. Must contain id or url with an
	 *            optional subdomain
	 * @param participantId
	 *            The id of the participant to be checked
	 * @return Whether the participant exists
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed in a way that
	 *             does not indicate that the participant does not exist
	 */
	public boolean doesParticipantExist(final Tournament tournament, final long participantId)
			throws DataAccessException {
		return getParticipantOrNull(tournament, participantId) != null;
	}
	
	/**
	 * Checks whether a attachment exists.
	 *
	 * @param match
	 *            The match. Must contain id and the tournament id
	 * @param attachmentId
	 *            The id of the attachment to be checked
	 * @return Whether the attachment exists
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed in a way that
	 *             does not indicate that the attachment does not exist
	 */
	public boolean doesAttachmentExist(final Match match, final long attachmentId) throws DataAccessException {
		return getAttachmentOrNull(match, attachmentId) != null;
	}
	
	/**
	 * Checks whether the tournament is created or co-owned by your account.
	 *
	 * @param tournament
	 *            The tournament to be checked. Must contain id or url with an
	 *            optional subdomain
	 * @return Whether the tournament is owned or co-owned by the user
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed
	 */
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
	
	/**
	 * Gets a tournament or returns null if it does not exist.
	 *
	 * @param tournament
	 *            Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for
	 *            challonge.com/single_elim). If assigned to a subdomain, URL
	 *            format must be :subdomain-:tournament_url (e.g.
	 *            'test-mytourney' for test.challonge.com/mytourney)
	 * @return The tournament if it exists or null
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed in a way that
	 *             does not indicate that the tournament does not exist
	 */
	public Tournament getTournamentOrNull(final String tournament) throws DataAccessException {
		return getTournamentOrNull(tournament, false, false, false);
	}
	
	/**
	 * Gets a tournament or returns null if it does not exist.
	 *
	 * @param tournament
	 *            Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for
	 *            challonge.com/single_elim). If assigned to a subdomain, URL
	 *            format must be :subdomain-:tournament_url (e.g.
	 *            'test-mytourney' for test.challonge.com/mytourney)
	 * @param includeParticipants
	 *            Include a list of participants in the response
	 * @param includeMatches
	 *            Include a list of matches in the response
	 * @return The tournament if it exists or null
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed in a way that
	 *             does not indicate that the tournament does not exist
	 */
	public Tournament getTournamentOrNull(final String tournament, final boolean includeParticipants,
			final boolean includeMatches) throws DataAccessException {
		return getTournamentOrNull(tournament, includeParticipants, includeMatches, false);
	}
	
	/**
	 * Gets a tournament or returns null if it does not exist.
	 *
	 * @param tournament
	 *            Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for
	 *            challonge.com/single_elim). If assigned to a subdomain, URL
	 *            format must be :subdomain-:tournament_url (e.g.
	 *            'test-mytourney' for test.challonge.com/mytourney)
	 * @param includeParticipants
	 *            Include a list of participants in the response
	 * @param includeMatches
	 *            Include a list of matches in the response
	 * @param includeAttachments
	 *            Include a list of attachments for each match in the response
	 * @return The requested tournament or null
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed in a way that
	 *             does not indicate that the tournament does not exist
	 */
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
	
	/**
	 * Gets a tournament or returns null if it does not exist.
	 *
	 * @param tournament
	 *            Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for
	 *            challonge.com/single_elim). If assigned to a subdomain, URL
	 *            format must be :subdomain-:tournament_url (e.g.
	 *            'test-mytourney' for test.challonge.com/mytourney)
	 * @param onSuccess
	 *            Called with result if call was successful
	 * @param onFailure
	 *            Called with exception if call was not successful
	 */
	public final void getTournamentOrNull(final String tournament, final Callback<Tournament> onSuccess,
			final Callback<DataAccessException> onFailure) {
		getTournamentOrNull(tournament, false, false, false, onSuccess, onFailure);
	}
	
	/**
	 * Gets a tournament or returns null if it does not exist.
	 *
	 * @param tournament
	 *            Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for
	 *            challonge.com/single_elim). If assigned to a subdomain, URL
	 *            format must be :subdomain-:tournament_url (e.g.
	 *            'test-mytourney' for test.challonge.com/mytourney)
	 * @param includeParticipants
	 *            Include a list of participants in the response
	 * @param includeMatches
	 *            Include a list of matches in the response
	 * @param onSuccess
	 *            Called with result if call was successful
	 * @param onFailure
	 *            Called with exception if call was not successful
	 */
	public final void getTournamentOrNull(final String tournament, final boolean includeParticipants,
			final boolean includeMatches, final Callback<Tournament> onSuccess,
			final Callback<DataAccessException> onFailure) {
		getTournamentOrNull(tournament, includeParticipants, includeMatches, false, onSuccess, onFailure);
	}
	
	/**
	 * Gets a tournament or returns null if it does not exist.
	 *
	 * @param tournament
	 *            Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for
	 *            challonge.com/single_elim). If assigned to a subdomain, URL
	 *            format must be :subdomain-:tournament_url (e.g.
	 *            'test-mytourney' for test.challonge.com/mytourney)
	 * @param includeParticipants
	 *            Include a list of participants in the response
	 * @param includeMatches
	 *            Include a list of matches in the response
	 * @param includeAttachments
	 *            Include a list of attachments for each match in the response
	 * @param onSuccess
	 *            Called with result if call was successful
	 * @param onFailure
	 *            Called with exception if call was not successful
	 */
	public final void getTournamentOrNull(final String tournament, final boolean includeParticipants,
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
	
	/**
	 * Gets a participant or returns null if it does not exist.
	 *
	 * @param tournament
	 *            The tournament to get the participant from. Must contain
	 *            tournament id
	 * @param participantId
	 *            The participant's unique ID
	 * @return The requested participant
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed
	 */
	public Participant getParticipantOrNull(final Tournament tournament, final long participantId)
			throws DataAccessException {
		return getParticipantOrNull(tournament, participantId, false);
	}
	
	/**
	 * Gets a participant or returns null if it does not exist.
	 *
	 * @param tournament
	 *            The tournament to get the participant from. Must contain
	 *            tournament id
	 * @param participantId
	 *            The participant's unique ID
	 * @param includeMatches
	 *            Includes an array of associated match records
	 * @return The requested participant
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed
	 */
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
	
	/**
	 * Gets a participant or returns null if it does not exist.
	 *
	 * @param tournament
	 *            The tournament to get the participant from. Must contain
	 *            tournament id
	 * @param participantId
	 *            The participant's unique ID
	 * @param onSuccess
	 *            Called with result if call was successful
	 * @param onFailure
	 *            Called with exception if call was not successful
	 */
	public void getParticipantOrNull(final Tournament tournament, final long participantId,
			final Callback<Participant> onSuccess, final Callback<DataAccessException> onFailure) {
		getParticipantOrNull(tournament, participantId, false, onSuccess, onFailure);
	}
	
	/**
	 * Gets a participant or returns null if it does not exist.
	 *
	 * @param tournament
	 *            The tournament to get the participant from. Must contain
	 *            tournament id
	 * @param participantId
	 *            The participant's unique ID
	 * @param includeMatches
	 *            Includes an array of associated match records
	 * @param onSuccess
	 *            Called with result if call was successful
	 * @param onFailure
	 *            Called with exception if call was not successful
	 */
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
	
	/**
	 * Gets a match or returns null if it does not exist.
	 *
	 * @param tournament
	 *            The tournament to get the match from. Must contain tournament
	 *            id
	 * @param matchId
	 *            The match's unique ID
	 * @return The requested match or null
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed in a way that
	 *             does not indicate that the tournament does not exist
	 */
	public Match getMatchOrNull(final Tournament tournament, final long matchId) throws DataAccessException {
		return getMatchOrNull(tournament, matchId, false);
	}
	
	/**
	 * Gets a match or returns null if it does not exist.
	 *
	 * @param tournament
	 *            The tournament to get the match from. Must contain tournament
	 *            id
	 * @param matchId
	 *            The match's unique ID
	 * @param includeAttachments
	 *            Include an array of associated attachment records
	 * @return The requested match or null
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed in a way that
	 *             does not indicate that the tournament does not exist
	 */
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
	
	/**
	 * Gets a match or returns null if it does not exist.
	 *
	 * @param tournament
	 *            The tournament to get the match from. Must contain tournament
	 *            id
	 * @param matchId
	 *            The match's unique ID
	 * @param onSuccess
	 *            Called with result if call was successful
	 * @param onFailure
	 *            Called with exception if call was not successful
	 */
	public void getMatchOrNull(final Tournament tournament, final long matchId, final Callback<Match> onSuccess,
			final Callback<DataAccessException> onFailure) {
		getMatchOrNull(tournament, matchId, false, onSuccess, onFailure);
	}
	
	/**
	 * Gets a match or returns null if it does not exist.
	 *
	 * @param tournament
	 *            The tournament to get the match from. Must contain tournament
	 *            id
	 * @param matchId
	 *            The match's unique ID
	 * @param includeAttachments
	 *            Include an array of associated attachment records
	 * @param onSuccess
	 *            Called with result if call was successful
	 * @param onFailure
	 *            Called with exception if call was not successful
	 */
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
	
	/**
	 * Gets an attachment or returns null if it does not exist.
	 *
	 * @param match
	 *            The match to get the attachment from. Must contain the
	 *            tournament- and match id
	 * @param attachmentId
	 *            The attachment's unique ID
	 * @return The requested attachment or null
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed in a way that
	 *             does not indicate that the tournament does not exist
	 */
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
	
	/**
	 * Gets an attachment or returns null if it does not exist.
	 *
	 * @param match
	 *            The match to get the attachment from. Must contain the
	 *            tournament- and match id
	 * @param attachmentId
	 *            The attachment's unique ID
	 * @param onSuccess
	 *            Called with result if call was successful
	 * @param onFailure
	 *            Called with exception if call was not successful
	 */
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
	
	/**
	 * Retrieve a single tournament record created with or co-owned by your
	 * account.
	 *
	 * @param tournament
	 *            Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for
	 *            challonge.com/single_elim). If assigned to a subdomain, URL
	 *            format must be :subdomain-:tournament_url (e.g.
	 *            'test-mytourney' for test.challonge.com/mytourney)
	 * @param includeParticipants
	 *            Include a list of participants in the response
	 * @param includeMatches
	 *            Include a list of matches in the response
	 * @param includeAttachments
	 *            Include a list of attachments for each match in the response
	 * @return The matching tournament
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed
	 * @throws IllegalArgumentException
	 *             includeAttachments is true but includeMatches is false
	 */
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
	
	/**
	 * Retrieve a single tournament record created with or co-owned by your
	 * account.
	 *
	 * @param tournament
	 *            Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for
	 *            challonge.com/single_elim). If assigned to a subdomain, URL
	 *            format must be :subdomain-:tournament_url (e.g.
	 *            'test-mytourney' for test.challonge.com/mytourney)
	 * @param includeParticipants
	 *            Include a list of participants in the response
	 * @param includeMatches
	 *            Include a list of matches in the response
	 * @param includeAttachments
	 *            Include a list of attachments for each match in the response
	 *            Exchange with the rest api or validation failed
	 * @param onSuccess
	 *            Called with result if call was successful
	 * @param onFailure
	 *            Called with exception if call was not successful
	 * @throws IllegalArgumentException
	 *             includeAttachments is true but includeMatches is false
	 */
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
			} catch(final DataAccessException e) {
				onFailure.accept(e);
			}
		}, onFailure);
	}
	
	/**
	 * Retrieve all the tournaments created with or co-owned by your account
	 * with all participants, matches and attachments.
	 *
	 * @return The tournaments
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed. Can occur if
	 *             a tournament is significantly changed during the operation
	 */
	public List<Tournament> getTournamentsWithFullData() throws DataAccessException {
		return getTournamentsWithFullData(null, null, null, null, null);
	}
	
	/**
	 * Retrieve a set of tournaments created with or co-owned by your account
	 * with all participants, matches and attachments.
	 *
	 * @param state
	 *            Only get tournaments with this state
	 * @param type
	 *            Only get tournaments with this type
	 * @param createdAfter
	 *            Get tournaments created after this date
	 * @param createdBefore
	 *            Get tournaments created before this date
	 * @param subdomain
	 *            Only get tournaments with this subdomain
	 * @return The tournaments
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed. Can occur if
	 *             a tournament is significantly changed during the operation
	 */
	public List<Tournament> getTournamentsWithFullData(final TournamentQueryState state, final TournamentType type,
			final OffsetDateTime createdAfter, final OffsetDateTime createdBefore, final String subdomain)
			throws DataAccessException {
		final List<Tournament> tournaments = getTournaments(state, type, createdAfter, createdBefore, subdomain);
		
		for(final Tournament tournament : tournaments) {
			addMissingData(tournament);
		}
		
		return tournaments;
	}
	
	/**
	 * Retrieve all the tournaments created with or co-owned by your account
	 * with all participants, matches and attachments.
	 * 
	 * @param onSuccess
	 *            Called with result if call was successful
	 * @param onFailure
	 *            Called with exception if call was not successful
	 */
	public void getTournamentsWithFullData(final Callback<List<Tournament>> onSuccess,
			final Callback<DataAccessException> onFailure) {
		getTournamentsWithFullData(null, null, null, null, null, onSuccess, onFailure);
	}
	
	/**
	 * Retrieve a set of tournaments created with or co-owned by your account
	 * with all participants, matches and attachments.
	 * 
	 * @param state
	 *            Only get tournaments with this state
	 * @param type
	 *            Only get tournaments with this type
	 * @param createdAfter
	 *            Get tournaments created after this date
	 * @param createdBefore
	 *            Get tournaments created before this date
	 * @param subdomain
	 *            Only get tournaments with this subdomain
	 * @param onSuccess
	 *            Called with result if call was successful
	 * @param onFailure
	 *            Called with exception if call was not successful
	 */
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
	
	/**
	 * Retrieve all the participants in the tournament with all matches.
	 *
	 * @param tournament
	 *            The tournament to get the matches from. Must contain id or url
	 *            with an optional subdomain
	 * @return The tournament's participants
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed. Can occur if
	 *             the tournament is significantly during the operation
	 */
	public List<Participant> getParticipantsWithFullData(final Tournament tournament) throws DataAccessException {
		final List<Participant> participants = getParticipants(tournament);
		
		for(final Participant participant : participants) {
			addMissingData(tournament, participant);
		}
		
		return participants;
	}
	
	/**
	 * Retrieve all the participants in the tournament with all matches.
	 *
	 * @param tournament
	 *            The tournament to get the matches from. Must contain id or url
	 *            with an optional subdomain
	 * @param onSuccess
	 *            Called with result if call was successful
	 * @param onFailure
	 *            Called with exception if call was not successful
	 */
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
	
	/**
	 * Retrieve all the matches in the tournament with all attachments.
	 *
	 * @param tournament
	 *            The tournament to get the matches from. Must contain id or url
	 *            with an optional subdomain
	 * @return The tournament's matches
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed. Can occur if
	 *             the tournament is significantly changed during the operation
	 */
	public List<Match> getMatchesWithFullData(final Tournament tournament) throws DataAccessException {
		final List<Match> matches = getMatches(tournament);
		
		for(final Match match : matches) {
			addMissingData(match);
		}
		
		return matches;
	}
	
	/**
	 * Retrieve all the matches in the tournament with all attachments.
	 *
	 * @param tournament
	 *            The tournament to get the matches from. Must contain id or url
	 *            with an optional subdomain
	 * @param onSuccess
	 *            Called with result if call was successful
	 * @param onFailure
	 *            Called with exception if call was not successful
	 */
	public void getMatchesWithFullData(final Tournament tournament, final Callback<List<Match>> onSuccess,
			final Callback<DataAccessException> onFailure) {
		getMatches(tournament, matches -> {
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
