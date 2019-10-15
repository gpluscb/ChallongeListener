package com.github.gpluscb.challonge_listener;

import java.time.OffsetDateTime;
import java.util.List;

import at.stefangeyer.challonge.async.Callback;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Attachment;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.enumeration.MatchState;
import at.stefangeyer.challonge.model.enumeration.TournamentType;
import at.stefangeyer.challonge.model.query.enumeration.TournamentQueryState;
import at.stefangeyer.challonge.service.ChallongeService;

public interface ChallongeExtensionService extends ChallongeService {
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
	default boolean doesTournamentExist(final String tournament) throws DataAccessException {
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
	default boolean doesMatchExist(final Tournament tournament, final long matchId) throws DataAccessException {
		return getMatchOrNull(tournament, matchId, false) != null;
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
	default boolean doesParticipantExist(final Tournament tournament, final long participantId)
			throws DataAccessException {
		return getParticipantOrNull(tournament, participantId, false) != null;
	}
	
	/**
	 * Checks whether a participant exists.
	 *
	 * @param match
	 *            The match to get the attachment from. Must contain the
	 *            tournament- and match id
	 * @param attachmentId
	 *            The id of the attachment to be checked
	 * @return Whether the participant exists
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed in a way that
	 *             does not indicate that the participant does not exist
	 */
	default boolean doesAttachmentExist(final Match match, final long attachmentId) throws DataAccessException {
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
	boolean doesOwn(final Tournament tournament) throws DataAccessException;
	
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
	default Tournament getTournamentOrNull(final String tournament) throws DataAccessException {
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
	default Tournament getTournamentOrNull(final String tournament, final boolean includeParticipants,
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
	Tournament getTournamentOrNull(final String tournament, final boolean includeParticipants,
			final boolean includeMatches, final boolean includeAttachments) throws DataAccessException;
	
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
	default void getTournamentOrNull(final String tournament, final Callback<Tournament> onSuccess,
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
	default void getTournamentOrNull(final String tournament, final boolean includeParticipants,
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
	void getTournamentOrNull(final String tournament, final boolean includeParticipants, final boolean includeMatches,
			final boolean includeAttachments, final Callback<Tournament> onSuccess,
			final Callback<DataAccessException> onFailure);
	
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
	default Participant getParticipantOrNull(final Tournament tournament, final long participantId)
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
	Participant getParticipantOrNull(final Tournament tournament, final long participantId,
			final boolean includeMatches) throws DataAccessException;
	
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
	default void getParticipantOrNull(final Tournament tournament, final long participantId,
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
	void getParticipantOrNull(final Tournament tournament, final long participantId, final boolean includeMatches,
			final Callback<Participant> onSuccess, final Callback<DataAccessException> onFailure);
	
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
	default Match getMatchOrNull(final Tournament tournament, final long matchId) throws DataAccessException {
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
	Match getMatchOrNull(final Tournament tournament, final long matchId, final boolean includeAttachments)
			throws DataAccessException;
	
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
	default void getMatchOrNull(final Tournament tournament, final long matchId, final Callback<Match> onSuccess,
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
	void getMatchOrNull(final Tournament tournament, final long matchId, final boolean includeAttachments,
			final Callback<Match> onSuccess, final Callback<DataAccessException> onFailure);
	
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
	Attachment getAttachmentOrNull(final Match match, final long attachmentId) throws DataAccessException;
	
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
	void getAttachmentOrNull(final Match match, final long attachmentId, final Callback<Attachment> onSuccess,
			final Callback<DataAccessException> onFailure);
	
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
	Tournament getTournament(final String tournament, final boolean includeParticipants, final boolean includeMatches,
			final boolean includeAttachments) throws DataAccessException;
	
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
	void getTournament(final String tournament, final boolean includeParticipants, final boolean includeMatches,
			final boolean includeAttachments, final Callback<Tournament> onSuccess,
			final Callback<DataAccessException> onFailure);
	
	/**
	 * Retrieve all the tournaments created with or co-owned by your account
	 * with all participants, matches and attachments.
	 *
	 * @return The tournaments
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed. Can occur if
	 *             a tournament is significantly changed during the operation
	 */
	default List<Tournament> getTournamentsWithFullData() throws DataAccessException {
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
	List<Tournament> getTournamentsWithFullData(final TournamentQueryState state, final TournamentType type,
			final OffsetDateTime createdAfter, final OffsetDateTime createdBefore, final String subdomain)
			throws DataAccessException;
	
	/**
	 * Retrieve all the tournaments created with or co-owned by your account
	 * with all participants, matches and attachments.
	 * 
	 * @param onSuccess
	 *            Called with result if call was successful
	 * @param onFailure
	 *            Called with exception if call was not successful
	 */
	default void getTournamentsWithFullData(final Callback<List<Tournament>> onSuccess,
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
	void getTournamentsWithFullData(final TournamentQueryState state, final TournamentType type,
			final OffsetDateTime createdAfter, final OffsetDateTime createdBefore, final String subdomain,
			final Callback<List<Tournament>> onSuccess, final Callback<DataAccessException> onFailure);
	
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
	List<Participant> getParticipantsWithFullData(final Tournament tournament) throws DataAccessException;
	
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
	void getParticipantsWithFullData(final Tournament tournament, final Callback<List<Participant>> onSuccess,
			final Callback<DataAccessException> onFailure);
	
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
	default List<Match> getMatchesWithFullData(final Tournament tournament) throws DataAccessException {
		return getMatchesWithFullData(tournament, (Participant) null, null);
	}
	
	/**
	 * Retrieve a set of matches in the tournament with all attachments.
	 *
	 * @param tournament
	 *            The tournament to get the matches from. Must contain id or url
	 *            with an optional subdomain
	 * @param participant
	 *            Only retrieve matches that include the specified participant.
	 *            This parameter is optional. Provide null if you want to skip
	 *            it.
	 * @return The tournament's matches
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed
	 */
	default List<Match> getMatchesWithFullData(final Tournament tournament, final Participant participant)
			throws DataAccessException {
		return getMatchesWithFullData(tournament, participant, null);
	}
	
	/**
	 * Retrieve a set of matches in the tournament with all attachments.
	 *
	 * @param tournament
	 *            The tournament to get the matches from. Must contain id or url
	 *            with an optional subdomain
	 * @param participant
	 *            Only retrieve matches that include the specified participant.
	 *            This parameter is optional. Provide null if you want to skip
	 *            it.
	 * @param state
	 *            all (default), pending, open, complete. This parameter is
	 *            optional. Provide null if you want to skip it.
	 * @return The tournament's matches
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed
	 */
	List<Match> getMatchesWithFullData(final Tournament tournament, final Participant participant,
			final MatchState state) throws DataAccessException;
	
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
	default void getMatchesWithFullData(final Tournament tournament, final Callback<List<Match>> onSuccess,
			final Callback<DataAccessException> onFailure) {
		getMatchesWithFullData(tournament, null, null, onSuccess, onFailure);
	}
	
	/**
	 * Retrieve a set of the matches in the tournament with all attachments.
	 *
	 * @param tournament
	 *            The tournament to get the matches from. Must contain id or url
	 *            with an optional subdomain
	 * @param participant
	 *            Only retrieve matches that include the specified participant.
	 *            This parameter is optional. Provide null if you want to skip
	 *            it.
	 * @param onSuccess
	 *            Called with result if call was successful
	 * @param onFailure
	 *            Called with exception if call was not successful
	 */
	default void getMatchesWithFullData(final Tournament tournament, final Participant participant,
			final Callback<List<Match>> onSuccess, final Callback<DataAccessException> onFailure) {
		getMatchesWithFullData(tournament, participant, null, onSuccess, onFailure);
	}
	
	/**
	 * Retrieve a set of the matches in the tournament with all attachments.
	 *
	 * @param tournament
	 *            The tournament to get the matches from. Must contain id or url
	 *            with an optional subdomain
	 * @param participant
	 *            Only retrieve matches that include the specified participant.
	 *            This parameter is optional. Provide null if you want to skip
	 *            it.
	 * @param state
	 *            all (default), pending, open, complete. This parameter is
	 *            optional. Provide null if you want to skip it.
	 * @param onSuccess
	 *            Called with result if call was successful
	 * @param onFailure
	 *            Called with exception if call was not successful
	 */
	void getMatchesWithFullData(final Tournament tournament, final Participant participant, final MatchState state,
			final Callback<List<Match>> onSuccess, final Callback<DataAccessException> onFailure);
}
