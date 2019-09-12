package com.gpluscb.challonge_listener;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.GsonBuilder;

import at.stefangeyer.challonge.exception.DataAccessException;
import at.stefangeyer.challonge.model.Credentials;
import at.stefangeyer.challonge.model.Match;
import at.stefangeyer.challonge.model.Participant;
import at.stefangeyer.challonge.model.Tournament;
import at.stefangeyer.challonge.model.query.AttachmentQuery;
import at.stefangeyer.challonge.model.query.ParticipantQuery;
import at.stefangeyer.challonge.model.query.TournamentQuery;
import at.stefangeyer.challonge.rest.retrofit.RetrofitRestClient;
import at.stefangeyer.challonge.serializer.Serializer;
import at.stefangeyer.challonge.serializer.gson.GsonSerializer;

// TODO: Investigate issue and annoy Challonge support about weird match/attachment_count and match/has_attachment behavior
public class ChallongeExtensionTest {
	private static RetrofitRestClient client;
	private static ChallongeExtension challonge;
	private static Serializer serializer;
	private static String randomUrl;
	private static Tournament owned;
	private static Tournament notOwned;
	
	@BeforeClass
	public static void beforeClass() throws DataAccessException {
		GsonBuilder builder = new GsonBuilder();
		builder.serializeNulls().setPrettyPrinting();
		serializer = new GsonSerializer(builder);
		client = new RetrofitRestClient();
		Credentials credentials = new Credentials(System.getenv("ChallongeUsername"), System.getenv("ChallongeToken"));
		challonge = new ChallongeExtension(credentials, serializer, client);
		
		randomUrl = generateRandomUrl(10);		
		TournamentQuery tournamentQuery = TournamentQuery.builder().name("TestTournament")
				.description("A test tournament").gameName("Chess").url(randomUrl).acceptAttachments(true)
				.build();
		owned = challonge.createTournament(tournamentQuery);
		
		List<ParticipantQuery> participantQueries = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			participantQueries.add(ParticipantQuery.builder().name(String.valueOf(i)).build());
		}
		challonge.bulkAddParticipants(owned, participantQueries);
		
		owned = challonge.startTournament(owned);
		
		List<Match> ownedMatches = challonge.getMatches(owned);
		for(int i = 0; i < ownedMatches.size(); i++) {
			challonge.createAttachment(ownedMatches.get(i),
					AttachmentQuery.builder().description(String.valueOf(i)).build());
		}
	}
	
	private static String generateRandomUrl(int length) {
		// Only gets lower-case [a-z]{length} type strings
		int a = 97;
		int z = 122;
		Random rng = new Random();
		StringBuilder builder = new StringBuilder(length);
		for(int i = 0; i < length; i++) {
			int random = rng.nextInt(z + 1 - a) + a;
			
			builder.append((char) random);
		}
		
		return builder.toString();
	}
	
	@AfterClass
	public static void afterClass() throws DataAccessException {
		owned = challonge.getTournament(randomUrl);
		challonge.deleteTournament(owned);
		client.close();
	}
	
	@Before
	public void beforeTest() throws DataAccessException {
		owned = challonge.getTournament(randomUrl);
		
		List<Participant> ownedParticipants = challonge.getParticipants(owned);
		for(Participant participant : ownedParticipants) {
			participant.setMatches(challonge.getMatches(owned, participant));
			for(Match match : participant.getMatches()) {
				match.setAttachments(challonge.getAttachments(match));
			}
		}
		owned.setParticipants(ownedParticipants);
		
		List<Match> ownedMatches = challonge.getMatches(owned);
		for(Match match : ownedMatches) {
			match.setAttachments(challonge.getAttachments(match));
		}
		owned.setMatches(ownedMatches);
		
		notOwned = challonge.getTournament(System.getenv("NotOwnedTournament"));
	}
	
	@Test
	public void testDoesExist() throws DataAccessException {
		assertTrue(challonge.doesExist(owned.getUrl()));
		assertTrue(challonge.doesExist(String.valueOf(owned.getId())));
		assertTrue(challonge.doesExist(owned));
		owned.setId(-1l); // An impossible id as far as I know
		owned.setUrl("Impossible to exist");
		assertFalse(challonge.doesExist(owned));
		assertFalse(challonge.doesExist("As far as I know impossible to exist due to spaces in url"));
	}
	
	@Test
	public void testDoesOwn() throws DataAccessException {
		assertTrue(challonge.doesOwn(owned));
		assertFalse(challonge.doesOwn(notOwned));
	}
	
	@Test
	public void testGetTournamentStringBooleanBooleanBoolean() throws DataAccessException {
		assertEquals(owned, challonge.getTournament(owned.getUrl(), true, true, true));
		for(Match match : owned.getMatches()) {
			match.setAttachments(null);
		}
		for(Participant participant : owned.getParticipants()) {
			for(Match match : participant.getMatches()) {
				match.setAttachments(null);
			}
		}
		assertEquals(owned, challonge.getTournament(owned.getUrl(), true, true, false));
		owned.getMatches().clear();
		for(Participant participant : owned.getParticipants()) {
			participant.setMatches(null);
		}
		assertEquals(owned, challonge.getTournament(owned.getUrl(), true, false, false));
		owned.getParticipants().clear();
		assertEquals(owned, challonge.getTournament(owned.getUrl(), false, false, false));
	}
	
	@Test
	public void testGetTournamentsWithFullData() throws DataAccessException {
		assertTrue(challonge.getTournamentsWithFullData().contains(owned));
	}
	
	@Test
	public void testGetMatchesWithFullData() throws DataAccessException {
		assertEquals(owned.getMatches(), challonge.getMatchesWithFullData(owned));
	}
}
