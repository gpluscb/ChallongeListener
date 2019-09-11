package com.gpluscb.challonge_listener;

import java.util.ArrayList;
import java.util.List;

import at.stefangeyer.challonge.Challonge;
import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Credentials;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.rest.RestClient;
import at.stefangeyer.challonge.serializer.Serializer;

/**
 * An extension of the {@link Challonge} class with a few utility methods mainly
 * focused on methods to get tournaments with complete data to the Attachment
 * level.<br>
 * Note that those methods need to use multiple api requests and are therefore a
 * little bit more resource consuming.
 */
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
	public ChallongeExtension(Credentials credentials, Serializer serializer, RestClient restClient)
			throws DataAccessException {
		super(credentials, serializer, restClient);
		// Tests whether the connection generally works so we can throw a
		// DataAccessException earlier for easier debugging and more consistent
		// failures.
		getTournaments();
	}
	
	/**
	 * Checks whether a tournament exists
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
	public boolean doesExist(String tournament) throws DataAccessException {
		// TODO: make the check better and safer for api updates, no ideas yet.
		try {
			getTournament(tournament);
			return true;
		} catch(DataAccessException e) {
			if(e.getMessage().contains(
					" was not successful (404) and returned: {\"errors\":[\"Requested tournament not found\"]}"))
				return false;
			throw e;
		}
	}
	
	/**
	 * Checks whether a tournament exists
	 * 
	 * @param tournament
	 *            The tournament to be checked. Must contain id or url with an
	 *            optional subdomain
	 * @return Whether the tournament exists
	 * @throws DataAccessException
	 *             Exchange with the rest api or validation failed in a way that
	 *             does not indicate that the tournament does not exist
	 */
	public boolean doesExist(Tournament tournament) throws DataAccessException {
		// TODO: make the check better and safer for api updates, no ideas yet.
		// Also potential optimization: something like a
		// getTournamentOrNull(String tournament)
		// method to save an api call.
		try {
			getParticipants(tournament);
			return true;
		} catch(DataAccessException e) {
			if(e.getMessage().contains(
					" was not successful (404) and returned: {\"errors\":[\"Requested tournament not found\"]}"))
				return false;
			throw e;
		}
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
	public boolean doesOwn(Tournament tournament) throws DataAccessException {
		List<Tournament> ownedTournaments = getTournaments();
		
		for(Tournament ownedTournament : ownedTournaments) {
			if(ownedTournament.getId().equals(tournament.getId())
					|| (ownedTournament.getUrl().equals(tournament.getUrl())
							&& (ownedTournament.getSubdomain() == null ? tournament.getSubdomain() == null
									: ownedTournament.getSubdomain().equals(tournament.getSubdomain()))))
				return true;
		}
		
		return false;
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
	public Tournament getTournament(String tournament, boolean includeParticipants, boolean includeMatches,
			boolean includeAttachments) throws DataAccessException {
		if(!includeMatches && includeAttachments)
			throw new IllegalArgumentException("Attachments can only be included if matches are included as well.");
		
		Tournament ret = getTournament(tournament, includeParticipants, includeMatches);
		
		// Matches need to be assigned to participants too
		if(includeParticipants && includeMatches) {
			for(Participant participant : ret.getParticipants()) {
				assignMissingData(participant, ret.getMatches());
			}
		}
		
		// Add the attachments to the matches
		if(includeAttachments) {
			for(Match match : ret.getMatches()) {
				addMissingData(match);
			}
		}
		
		return ret;
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
		List<Tournament> tournaments = getTournaments();
		
		for(Tournament tournament : tournaments) {
			addMissingData(tournament);
		}
		
		return tournaments;
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
	public List<Participant> getParticipantsWithFullData(Tournament tournament) throws DataAccessException {
		List<Participant> participants = getParticipants(tournament);
		
		for(Participant participant : participants) {
			addMissingData(tournament, participant);
		}
		
		return participants;
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
	public List<Match> getMatchesWithFullData(Tournament tournament) throws DataAccessException {
		List<Match> matches = getMatches(tournament);
		
		for(Match match : matches) {
			addMissingData(match);
		}
		
		return matches;
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
	private void addMissingData(Tournament tournament) throws DataAccessException {
		// TODO: Make these methods public(?) Positive: (barely) useful
		// convenience method; Negative: should write tests. Can't think
		// of use-cases so it will stay private for now
		
		// Adding all matches
		if(tournament.getMatches() == null || tournament.getMatches().isEmpty()) {
			tournament.setMatches(getMatchesWithFullData(tournament));
		}
		
		// Adding all participants
		if(tournament.getParticipants() == null || tournament.getParticipants().isEmpty()) {
			tournament.setParticipants(getParticipants(tournament));
		}
		
		// Adding all attachments
		for(Match match : tournament.getMatches()) {
			addMissingData(match);
		}
		
		// Adding all matches to the participants
		for(Participant participant : tournament.getParticipants()) {
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
	private void assignMissingData(Participant participant, List<Match> matches) {
		if(participant.getMatches() == null) participant.setMatches(new ArrayList<>());
		if(participant.getMatches().isEmpty()) {
			for(Match match : matches) {
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
	private void addMissingData(Tournament tournament, Participant participant) throws DataAccessException {
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
	private void addMissingData(Match match) throws DataAccessException {
		// Adding all attachments
		if((match.getAttachments() == null || match.getAttachments().isEmpty()) && match.getAttachmentCount() != null
				&& match.getAttachmentCount() > 0) {
			match.setAttachments(getAttachments(match));
		}
	}
}
