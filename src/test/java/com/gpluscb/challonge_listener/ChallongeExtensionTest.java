package com.gpluscb.challonge_listener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.gpluscb.challonge_listener.ChallongeExtension;
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

public class ChallongeExtensionTest {
	private static RetrofitRestClient client;
	private static ChallongeExtension challonge;
	private static Serializer serializer;
	private static String randomUrl;
	private static Tournament owned;
	private static Tournament notOwned;
	
	@BeforeClass
	public static void beforeClass() throws DataAccessException {
		final GsonBuilder builder = new GsonBuilder();
		builder.serializeNulls().setPrettyPrinting();
		serializer = new GsonSerializer(builder);
		client = new RetrofitRestClient();
		final Credentials credentials = new Credentials(System.getenv("ChallongeUsername"),
				System.getenv("ChallongeToken"));
		challonge = new ChallongeExtension(credentials, serializer, client);
		
		randomUrl = generateRandomUrl(10);
		final TournamentQuery tournamentQuery = TournamentQuery.builder().name("TestTournament")
				.description("A test tournament").gameName("Chess").url(randomUrl).acceptAttachments(Boolean.TRUE)
				.build();
		owned = challonge.createTournament(tournamentQuery);
		
		final List<ParticipantQuery> participantQueries = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			participantQueries.add(ParticipantQuery.builder().name(String.valueOf(i)).build());
		}
		challonge.bulkAddParticipants(owned, participantQueries);
		
		owned = challonge.startTournament(owned);
		
		final List<Match> ownedMatches = challonge.getMatches(owned);
		for(int i = 0; i < ownedMatches.size(); i++) {
			challonge.createAttachment(ownedMatches.get(i),
					AttachmentQuery.builder().description(String.valueOf(i)).build());
		}
	}
	
	private static String generateRandomUrl(final int length) {
		// Only gets lower-case [a-z]{length} type strings
		final int a = 97;
		final int z = 122;
		final Random rng = new Random();
		final StringBuilder builder = new StringBuilder(length);
		for(int i = 0; i < length; i++) {
			final int random = rng.nextInt(z + 1 - a) + a;
			
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
		
		final List<Participant> ownedParticipants = challonge.getParticipants(owned);
		for(final Participant participant : ownedParticipants) {
			participant.setMatches(challonge.getMatches(owned, participant));
			for(final Match match : participant.getMatches()) {
				match.setAttachments(challonge.getAttachments(match));
			}
		}
		owned.setParticipants(ownedParticipants);
		
		final List<Match> ownedMatches = challonge.getMatches(owned);
		for(final Match match : ownedMatches) {
			match.setAttachments(challonge.getAttachments(match));
		}
		owned.setMatches(ownedMatches);
		
		notOwned = challonge.getTournament(System.getenv("NotOwnedTournament"));
	}
	
	@Test
	public void testDoesTournamentExist() throws DataAccessException {
		assertTrue(challonge.doesTournamentExist(owned.getUrl()));
		assertTrue(challonge.doesTournamentExist(String.valueOf(owned.getId())));
		assertFalse(challonge.doesTournamentExist("As far as I know impossible to exist due to spaces in url"));
	}
	
	@Test
	public void testDoesMatchExist() throws DataAccessException {
		assertTrue(challonge.doesMatchExist(owned, owned.getMatches().get(0).getId().longValue()));
		assertFalse(challonge.doesMatchExist(owned, -1));
		try {
			owned.setId(Long.valueOf(-1));
			challonge.doesMatchExist(owned, owned.getMatches().get(0).getId().longValue());
			fail();
		} catch(@SuppressWarnings("unused") final DataAccessException e) {
			// Expected
		}
	}
	
	@Test
	public void testDoesParticipantExist() throws DataAccessException {
		assertTrue(challonge.doesParticipantExist(owned, owned.getParticipants().get(0).getId().longValue()));
		assertFalse(challonge.doesParticipantExist(owned, -1));
		try {
			owned.setId(Long.valueOf(-1));
			challonge.doesParticipantExist(owned, owned.getParticipants().get(0).getId().longValue());
			fail();
		} catch(@SuppressWarnings("unused") final DataAccessException e) {
			// Expected
		}
	}
	
	@Test
	public void testDoesAttachmentExist() throws DataAccessException {
		assertTrue(challonge.doesAttachmentExist(owned.getMatches().get(0),
				owned.getMatches().get(0).getAttachments().get(0).getId().longValue()));
		assertFalse(challonge.doesAttachmentExist(owned.getMatches().get(0), -1));
		try {
			owned.getMatches().get(0).setId(Long.valueOf(-1));
			challonge.doesAttachmentExist(owned.getMatches().get(0),
					owned.getMatches().get(0).getAttachments().get(0).getId().longValue());
			fail();
		} catch(@SuppressWarnings("unused") final DataAccessException e) {
			// Expected
		}
	}
	
	@Test
	public void testDoesOwn() throws DataAccessException {
		assertTrue(challonge.doesOwn(owned));
		assertFalse(challonge.doesOwn(notOwned));
	}
	
	@Test
	public void testGetTournamentStringBooleanBooleanBoolean() throws DataAccessException {
		assertEquals(owned, challonge.getTournament(owned.getUrl(), true, true, true));
		for(final Match match : owned.getMatches()) {
			match.setAttachments(null);
		}
		for(final Participant participant : owned.getParticipants()) {
			for(final Match match : participant.getMatches()) {
				match.setAttachments(null);
			}
		}
		assertEquals(owned, challonge.getTournament(owned.getUrl(), true, true, false));
		owned.getMatches().clear();
		for(final Participant participant : owned.getParticipants()) {
			participant.setMatches(null);
		}
		assertEquals(owned, challonge.getTournament(owned.getUrl(), true, false, false));
		owned.getParticipants().clear();
		assertEquals(owned, challonge.getTournament(owned.getUrl(), false, false, false));
		try {
			challonge.getTournament("", true, false, true);
			fail();
		} catch(@SuppressWarnings("unused") final IllegalArgumentException e) {
			// Expected
		}
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
