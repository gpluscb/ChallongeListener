package com.gpluscb.challonge_listener.listener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.gpluscb.challonge_listener.events.GenericEvent;
import com.gpluscb.challonge_listener.events.tournament.GenericTournamentChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.GenericTournamentEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentAcceptAttachmentsChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentAcceptingPredictionsChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentAllowParticipantMatchReportingChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentAnonymousVotingChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentCategoryChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentCheckInDurationChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentCompletedAtChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentCreatedAtChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentCreatedByApiChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentCreatedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentCreditCappedChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentDeletedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentDescriptionChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentDescriptionSourceChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentEventIdChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentFullChallongeUrlChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentGameIdChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentGameNameChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentGrandFinalsModifierChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentGroupStagesEnabledChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentGroupStagesWereStartedChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentHideForumChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentHideSeedsChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentHoldThirdPlaceMatchChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentIdChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentLiveImageUrlChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentLockedAtChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentMatchesChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentMaxPredictionsPerUserChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentNameChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentNotifyUsersWhenMatchesOpenChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentNotifyUsersWhenTheTournamentEndsChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentOpenSignupChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentParticipantsChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentParticipantsCountChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentParticipantsLockedChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentParticipantsSwappableChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentPointsForByeChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentPointsForGameTieChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentPointsForGameWinChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentPointsForMatchTieChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentPointsForMatchWinChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentPredictionMethodChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentPredictionsOpenedAtChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentPrivateOnlyChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentProgressMeterChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentPublicPredictionsBeforeStartTimeChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentQuickAdvanceChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentRankedByChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentRequireScoreAgreementChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentReviewBeforeFinalizingChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentRoundRobinPointsForGameTieChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentRoundRobinPointsForGameWinChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentRoundRobinPointsForMatchTieChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentRoundRobinPointsForMatchWinChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentSequentialPairingsChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentShowRoundsChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentSignUpUrlChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentSignupCapChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentStartAtChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentStartedAtChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentStartedCheckingInAtChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentStateChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentSubdomainChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentSwissRoundsChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentTeamConvertableChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentTeamsChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentTieBreaksChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentTournamentTypeChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentUpdatedAtChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.TournamentUrlChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.GenericMatchChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.GenericMatchEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchAttachmentCountChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchAttachmentsChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchCreatedAtChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchCreatedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchDeletedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchGroupIdChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchHasAttachmentChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchIdChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchIdentifierChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchLocationChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchLoserIdChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchPlayer1IdChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchPlayer1IsPrerequisiteMatchLoserChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchPlayer1PrerequisiteMatchIdChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchPlayer1VotesChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchPlayer2IdChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchPlayer2IsPrerequisiteMatchLoserChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchPlayer2PrerequisiteMatchIdChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchPlayer2VotesChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchPrerequisiteMatchIdsCsvChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchRoundChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchScheduledTimeChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchScoresCsvChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchStartedAtChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchStateChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchTournamentIdChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchUnderwayAtChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchUpdatedAtChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.MatchWinnerIdChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentAssetContentTypeChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentAssetFileNameChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentAssetFileSizeChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentAssetUrlChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentCreatedAtChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentCreatedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentDeletedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentDescriptionChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentIdChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentMatchIdChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentOriginalFileNameChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentUpdatedAtChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentUrlChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentUserIdChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.attachment.GenericAttachmentChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.match.attachment.GenericAttachmentEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.GenericParticipantChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.GenericParticipantEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantActiveChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantAttachedParticipatablePortraitUrlChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantCanCheckInChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantChallongeEmailAddressVerifiedChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantChallongeUsernameChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantCheckedInAtChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantCheckedInChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantConfirmRemoveChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantCreatedAtChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantCreatedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantDeletedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantDisplayNameWithInvitationEmailAddressChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantEmailHashChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantFinalRankChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantGroupIdChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantIconChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantIdChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantInvitationIdChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantInvitationPendingChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantInviteEmailChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantMatchAddedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantMatchRemovedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantMatchesChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantMiscChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantNameChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantOnWaitingListChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantParticipatableOrInvitationAttachedChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantReactivatableChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantRemovableChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantSeedChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantTournamentIdChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantUpdatedAtChangedEvent;
import com.gpluscb.challonge_listener.events.tournament.participant.ParticipantUsernameChangedEvent;

/**
 * Breaks up the {@link com.gpluscb.challonge_listener.listener.EventListener
 * EventListener} to feature inheritable methods specific to one type of
 * {@link com.gpluscb.challonge_listener.events.GenericEvent GenericEvent}.
 *
 * @see com.gpluscb.challonge_listener.listener.EventListener EventListener
 */
public abstract class ListenerAdapter implements EventListener {
	
	private final List<Long> subscribedTournamentIds;
	
	/**
	 * Creates an instance.
	 */
	public ListenerAdapter() {
		this.subscribedTournamentIds = new ArrayList<>();
	}
	
	/**
	 * After subscribing this listener to a tournament, it will receive all
	 * events for that tournament.
	 * 
	 * @param tournamentId
	 *            The id(s) of the tournament(s) to subscribe to
	 */
	public void subscribeTo(final long... tournamentId) {
		for(final long id : tournamentId) {
			this.subscribedTournamentIds.add(Long.valueOf(id));
		}
	}
	
	/**
	 * Reverts a subscription to a tournament. Events from these tournaments
	 * will not be received anymore.
	 * 
	 * @param tournamentId
	 *            The id(s) of the tournament(s) to unsubscribe from
	 * @return Whether at least one tournament has been unsubscribed from
	 */
	public boolean unsubscribeFrom(final long... tournamentId) {
		boolean removed = false;
		
		for(final long id : tournamentId) {
			if(this.subscribedTournamentIds.remove(Long.valueOf(id))) {
				removed = true;
			}
		}
		
		return removed;
	}
	
	@Override
	public List<Long> getSubscribedTournamentIds() {
		return this.subscribedTournamentIds;
	}
	
	/**
	 * Fires an event to all of the applying methods.
	 *
	 * @param event
	 *            The event to be fired
	 */
	// TODO: Give ParticipantMatchChangedEvents own abstract class
	@Override
	public final void onEvent(final GenericEvent event) {
		onGenericEvent(event);
		if(event instanceof GenericTournamentEvent) {
			onGenericTournamentEvent((GenericTournamentEvent) event);
			if(event instanceof TournamentCreatedEvent) {
				onTournamentCreatedEvent((TournamentCreatedEvent) event);
			} else if(event instanceof TournamentDeletedEvent) {
				onTournamentDeletedEvent((TournamentDeletedEvent) event);
			} else if(event instanceof GenericTournamentChangedEvent) {
				onGenericTournamentChangedEvent((GenericTournamentChangedEvent) event);
				if(event instanceof GenericParticipantEvent) {
					onGenericParticipantEvent((GenericParticipantEvent) event);
					if(event instanceof ParticipantCreatedEvent) {
						onParticipantCreatedEvent((ParticipantCreatedEvent) event);
					} else if(event instanceof ParticipantDeletedEvent) {
						onParticipantDeletedEvent((ParticipantDeletedEvent) event);
					} else if(event instanceof GenericParticipantChangedEvent) {
						onGenericParticipantChangedEvent((GenericParticipantChangedEvent) event);
						if(event instanceof ParticipantMatchAddedEvent) {
							onParticipantMatchAddedEvent((ParticipantMatchAddedEvent) event);
						} else if(event instanceof ParticipantMatchRemovedEvent) {
							onParticipantMatchRemovedEvent((ParticipantMatchRemovedEvent) event);
						} else {
							// Is normal participant property changed event
							handleParticipantPropertyChangedEvent((GenericParticipantChangedEvent) event);
						}
					}
				} else if(event instanceof GenericMatchEvent) {
					onGenericMatchEvent((GenericMatchEvent) event);
					if(event instanceof MatchCreatedEvent) {
						onMatchCreatedEvent((MatchCreatedEvent) event);
					} else if(event instanceof MatchDeletedEvent) {
						onMatchDeletedEvent((MatchDeletedEvent) event);
					} else if(event instanceof GenericMatchChangedEvent) {
						onGenericMatchChangedEvent((GenericMatchChangedEvent) event);
						if(event instanceof GenericAttachmentEvent) {
							onGenericAttachmentEvent((GenericAttachmentEvent) event);
							if(event instanceof AttachmentCreatedEvent) {
								onAttachmentCreatedEvent((AttachmentCreatedEvent) event);
							} else if(event instanceof AttachmentDeletedEvent) {
								onAttachmentDeletedEvent((AttachmentDeletedEvent) event);
							} else if(event instanceof GenericAttachmentChangedEvent) {
								onAttachmentChangedEvent((GenericAttachmentChangedEvent) event);
								// Is normal attachment property changed event
								handleAttachmentPropertyChangedEvent((GenericAttachmentChangedEvent) event);
							}
						} else {
							// Is normal match property changed event
							handleMatchPropertyChangedEvent((GenericMatchChangedEvent) event);
						}
					}
				} else {
					// Is normal tournament property changed event
					handleTournamentPropertyChangedEvent((GenericTournamentChangedEvent) event);
				}
			}
		}
	}
	
	private void handleTournamentPropertyChangedEvent(final GenericTournamentChangedEvent event) {
		try {
			// Find the corresponding
			// onTournamentPropertyChangedEvent(TournamentPropertyChangedEvent)
			// method
			final Method onTournamentPropertyChangedEvent = ListenerAdapter.class
					.getDeclaredMethod("on" + event.getClass().getSimpleName(), event.getClass());
			
			// Cast and fire the event
			onTournamentPropertyChangedEvent.invoke(this, event.getClass().cast(event));
		} catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// If all
			// onTournamentPropertyChangedEvent(TournamentPropertyChangedEvent)
			// exist, this should never occur
			e.printStackTrace();
		}
	}
	
	private void handleMatchPropertyChangedEvent(final GenericMatchChangedEvent event) {
		try {
			// Find the corresponding
			// onMatchPropertyChangedEvent(MatchPropertyChangedEvent) method
			final Method onMatchPropertyChangedEvent = ListenerAdapter.class
					.getDeclaredMethod("on" + event.getClass().getSimpleName(), event.getClass());
			
			// Cast and fire the event
			onMatchPropertyChangedEvent.invoke(this, event.getClass().cast(event));
		} catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// If all onMatchPropertyChangedEvent(MatchPropertyChangedEvent)
			// exist, this should never occur
			e.printStackTrace();
		}
	}
	
	private void handleParticipantPropertyChangedEvent(final GenericParticipantChangedEvent event) {
		try {
			// Find the corresponding
			// onParticipantPropertyChangedEvent(ParticipantPropertyChangedEvent)
			// method
			final Method onParticipantPropertyChangedEvent = ListenerAdapter.class
					.getDeclaredMethod("on" + event.getClass().getSimpleName(), event.getClass());
			
			// Cast and fire the event
			onParticipantPropertyChangedEvent.invoke(this, event.getClass().cast(event));
		} catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// If all
			// onParticipantPropertyChangedEvent(ParticipantPropertyChangedEvent)
			// exist, this should never occur
			e.printStackTrace();
		}
	}
	
	private void handleAttachmentPropertyChangedEvent(final GenericAttachmentChangedEvent event) {
		try {
			// Find the corresponding
			// onAttachmentPropertyChangedEvent(AttachmentPropertyChangedEvent)
			// method
			final Method onAttachmentPropertyChangedEvent = ListenerAdapter.class
					.getDeclaredMethod("on" + event.getClass().getSimpleName(), event.getClass());
			
			// Cast and fire the event
			onAttachmentPropertyChangedEvent.invoke(this, event.getClass().cast(event));
		} catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// If all
			// onAttachmentPropertyChangedEvent(AttachmentPropertyChangedEvent)
			// exist, this should never occur
			e.printStackTrace();
		}
	}
	
	protected void onGenericEvent(@SuppressWarnings("unused") final GenericEvent event) {}
	
	// Tournament methods
	protected void onGenericTournamentEvent(@SuppressWarnings("unused") final GenericTournamentEvent event) {}
	
	protected void onTournamentCreatedEvent(@SuppressWarnings("unused") final TournamentCreatedEvent event) {}
	
	protected void onTournamentDeletedEvent(@SuppressWarnings("unused") final TournamentDeletedEvent event) {}
	
	protected void onGenericTournamentChangedEvent(
			@SuppressWarnings("unused") final GenericTournamentChangedEvent event) {}
	
	protected void onTournamentIdChangedEvent(@SuppressWarnings("unused") final TournamentIdChangedEvent event) {}
	
	protected void onTournamentUrlChangedEvent(@SuppressWarnings("unused") final TournamentUrlChangedEvent event) {}
	
	protected void onTournamentNameChangedEvent(@SuppressWarnings("unused") final TournamentNameChangedEvent event) {}
	
	protected void onTournamentTournamentTypeChangedEvent(
			@SuppressWarnings("unused") final TournamentTournamentTypeChangedEvent event) {}
	
	protected void onTournamentSubdomainChangedEvent(
			@SuppressWarnings("unused") final TournamentSubdomainChangedEvent event) {}
	
	protected void onTournamentDescriptionChangedEvent(
			@SuppressWarnings("unused") final TournamentDescriptionChangedEvent event) {}
	
	protected void onTournamentOpenSignupChangedEvent(
			@SuppressWarnings("unused") final TournamentOpenSignupChangedEvent event) {}
	
	protected void onTournamentHoldThirdPlaceMatchChangedEvent(
			@SuppressWarnings("unused") final TournamentHoldThirdPlaceMatchChangedEvent event) {}
	
	protected void onTournamentPointsForMatchWinChangedEvent(
			@SuppressWarnings("unused") final TournamentPointsForMatchWinChangedEvent event) {}
	
	protected void onTournamentPointsForMatchTieChangedEvent(
			@SuppressWarnings("unused") final TournamentPointsForMatchTieChangedEvent event) {}
	
	protected void onTournamentPointsForGameWinChangedEvent(
			@SuppressWarnings("unused") final TournamentPointsForGameWinChangedEvent event) {}
	
	protected void onTournamentPointsForGameTieChangedEvent(
			@SuppressWarnings("unused") final TournamentPointsForGameTieChangedEvent event) {}
	
	protected void onTournamentPointsForByeChangedEvent(
			@SuppressWarnings("unused") final TournamentPointsForByeChangedEvent event) {}
	
	protected void onTournamentSwissRoundsChangedEvent(
			@SuppressWarnings("unused") final TournamentSwissRoundsChangedEvent event) {}
	
	protected void onTournamentRankedByChangedEvent(
			@SuppressWarnings("unused") final TournamentRankedByChangedEvent event) {}
	
	protected void onTournamentRoundRobinPointsForGameWinChangedEvent(
			@SuppressWarnings("unused") final TournamentRoundRobinPointsForGameWinChangedEvent event) {}
	
	protected void onTournamentRoundRobinPointsForGameTieChangedEvent(
			@SuppressWarnings("unused") final TournamentRoundRobinPointsForGameTieChangedEvent event) {}
	
	protected void onTournamentRoundRobinPointsForMatchWinChangedEvent(
			@SuppressWarnings("unused") final TournamentRoundRobinPointsForMatchWinChangedEvent event) {}
	
	protected void onTournamentRoundRobinPointsForMatchTieChangedEvent(
			@SuppressWarnings("unused") final TournamentRoundRobinPointsForMatchTieChangedEvent event) {}
	
	protected void onTournamentAcceptAttachmentsChangedEvent(
			@SuppressWarnings("unused") final TournamentAcceptAttachmentsChangedEvent event) {}
	
	protected void onTournamentHideForumChangedEvent(
			@SuppressWarnings("unused") final TournamentHideForumChangedEvent event) {}
	
	protected void onTournamentShowRoundsChangedEvent(
			@SuppressWarnings("unused") final TournamentShowRoundsChangedEvent event) {}
	
	protected void onTournamentPrivateOnlyChangedEvent(
			@SuppressWarnings("unused") final TournamentPrivateOnlyChangedEvent event) {}
	
	protected void onTournamentNotifyUsersWhenTheTournamentEndsChangedEvent(
			@SuppressWarnings("unused") final TournamentNotifyUsersWhenTheTournamentEndsChangedEvent event) {}
	
	protected void onTournamentSequentialPairingsChangedEvent(
			@SuppressWarnings("unused") final TournamentSequentialPairingsChangedEvent event) {}
	
	protected void onTournamentSignupCapChangedEvent(
			@SuppressWarnings("unused") final TournamentSignupCapChangedEvent event) {}
	
	protected void onTournamentStartAtChangedEvent(
			@SuppressWarnings("unused") final TournamentStartAtChangedEvent event) {}
	
	protected void onTournamentCheckInDurationChangedEvent(
			@SuppressWarnings("unused") final TournamentCheckInDurationChangedEvent event) {}
	
	protected void onTournamentAllowParticipantMatchReportingChangedEvent(
			@SuppressWarnings("unused") final TournamentAllowParticipantMatchReportingChangedEvent event) {}
	
	protected void onTournamentAnonymousVotingChangedEvent(
			@SuppressWarnings("unused") final TournamentAnonymousVotingChangedEvent event) {}
	
	protected void onTournamentCategoryChangedEvent(
			@SuppressWarnings("unused") final TournamentCategoryChangedEvent event) {}
	
	protected void onTournamentCompletedAtChangedEvent(
			@SuppressWarnings("unused") final TournamentCompletedAtChangedEvent event) {}
	
	protected void onTournamentCreatedAtChangedEvent(
			@SuppressWarnings("unused") final TournamentCreatedAtChangedEvent event) {}
	
	protected void onTournamentCreatedByApiChangedEvent(
			@SuppressWarnings("unused") final TournamentCreatedByApiChangedEvent event) {}
	
	protected void onTournamentCreditCappedChangedEvent(
			@SuppressWarnings("unused") final TournamentCreditCappedChangedEvent event) {}
	
	protected void onTournamentGameIdChangedEvent(
			@SuppressWarnings("unused") final TournamentGameIdChangedEvent event) {}
	
	protected void onTournamentGroupStagesEnabledChangedEvent(
			@SuppressWarnings("unused") final TournamentGroupStagesEnabledChangedEvent event) {}
	
	protected void onTournamentHideSeedsChangedEvent(
			@SuppressWarnings("unused") final TournamentHideSeedsChangedEvent event) {}
	
	protected void onTournamentMaxPredictionsPerUserChangedEvent(
			@SuppressWarnings("unused") final TournamentMaxPredictionsPerUserChangedEvent event) {}
	
	protected void onTournamentNotifyUsersWhenMatchesOpenChangedEvent(
			@SuppressWarnings("unused") final TournamentNotifyUsersWhenMatchesOpenChangedEvent event) {}
	
	protected void onTournamentParticipantsCountChangedEvent(
			@SuppressWarnings("unused") final TournamentParticipantsCountChangedEvent event) {}
	
	protected void onTournamentPredictionMethodChangedEvent(
			@SuppressWarnings("unused") final TournamentPredictionMethodChangedEvent event) {}
	
	protected void onTournamentPredictionsOpenedAtChangedEvent(
			@SuppressWarnings("unused") final TournamentPredictionsOpenedAtChangedEvent event) {}
	
	protected void onTournamentProgressMeterChangedEvent(
			@SuppressWarnings("unused") final TournamentProgressMeterChangedEvent event) {}
	
	protected void onTournamentQuickAdvanceChangedEvent(
			@SuppressWarnings("unused") final TournamentQuickAdvanceChangedEvent event) {}
	
	protected void onTournamentRequireScoreAgreementChangedEvent(
			@SuppressWarnings("unused") final TournamentRequireScoreAgreementChangedEvent event) {}
	
	protected void onTournamentStartedAtChangedEvent(
			@SuppressWarnings("unused") final TournamentStartedAtChangedEvent event) {}
	
	protected void onTournamentStartedCheckingInAtChangedEvent(
			@SuppressWarnings("unused") final TournamentStartedCheckingInAtChangedEvent event) {}
	
	protected void onTournamentStateChangedEvent(@SuppressWarnings("unused") final TournamentStateChangedEvent event) {}
	
	protected void onTournamentTeamsChangedEvent(@SuppressWarnings("unused") final TournamentTeamsChangedEvent event) {}
	
	protected void onTournamentTieBreaksChangedEvent(
			@SuppressWarnings("unused") final TournamentTieBreaksChangedEvent event) {}
	
	protected void onTournamentUpdatedAtChangedEvent(
			@SuppressWarnings("unused") final TournamentUpdatedAtChangedEvent event) {}
	
	protected void onTournamentDescriptionSourceChangedEvent(
			@SuppressWarnings("unused") final TournamentDescriptionSourceChangedEvent event) {}
	
	protected void onTournamentFullChallongeUrlChangedEvent(
			@SuppressWarnings("unused") final TournamentFullChallongeUrlChangedEvent event) {}
	
	protected void onTournamentLiveImageUrlChangedEvent(
			@SuppressWarnings("unused") final TournamentLiveImageUrlChangedEvent event) {}
	
	protected void onTournamentSignUpUrlChangedEvent(
			@SuppressWarnings("unused") final TournamentSignUpUrlChangedEvent event) {}
	
	protected void onTournamentReviewBeforeFinalizingChangedEvent(
			@SuppressWarnings("unused") final TournamentReviewBeforeFinalizingChangedEvent event) {}
	
	protected void onTournamentAcceptingPredictionsChangedEvent(
			@SuppressWarnings("unused") final TournamentAcceptingPredictionsChangedEvent event) {}
	
	protected void onTournamentParticipantsLockedChangedEvent(
			@SuppressWarnings("unused") final TournamentParticipantsLockedChangedEvent event) {}
	
	protected void onTournamentGameNameChangedEvent(
			@SuppressWarnings("unused") final TournamentGameNameChangedEvent event) {}
	
	protected void onTournamentParticipantsSwappableChangedEvent(
			@SuppressWarnings("unused") final TournamentParticipantsSwappableChangedEvent event) {}
	
	protected void onTournamentTeamConvertableChangedEvent(
			@SuppressWarnings("unused") final TournamentTeamConvertableChangedEvent event) {}
	
	protected void onTournamentGroupStagesWereStartedChangedEvent(
			@SuppressWarnings("unused") final TournamentGroupStagesWereStartedChangedEvent event) {}
	
	protected void onTournamentLockedAtChangedEvent(
			@SuppressWarnings("unused") final TournamentLockedAtChangedEvent event) {}
	
	protected void onTournamentEventIdChangedEvent(
			@SuppressWarnings("unused") final TournamentEventIdChangedEvent event) {}
	
	protected void onTournamentprotectedPredictionsBeforeStartTimeChangedEvent(
			@SuppressWarnings("unused") final TournamentPublicPredictionsBeforeStartTimeChangedEvent event) {}
	
	protected void onTournamentGrandFinalsModifierChangedEvent(
			@SuppressWarnings("unused") final TournamentGrandFinalsModifierChangedEvent event) {}
	
	protected void onTournamentParticipantsChangedEvent(
			@SuppressWarnings("unused") final TournamentParticipantsChangedEvent event) {}
	
	protected void onTournamentMatchesChangedEvent(
			@SuppressWarnings("unused") final TournamentMatchesChangedEvent event) {}
	
	// Participant methods
	protected void onGenericParticipantEvent(@SuppressWarnings("unused") final GenericParticipantEvent event) {}
	
	protected void onParticipantCreatedEvent(@SuppressWarnings("unused") final ParticipantCreatedEvent event) {}
	
	protected void onParticipantDeletedEvent(@SuppressWarnings("unused") final ParticipantDeletedEvent event) {}
	
	protected void onGenericParticipantChangedEvent(
			@SuppressWarnings("unused") final GenericParticipantChangedEvent event) {}
	
	protected void onParticipantIdChangedEvent(@SuppressWarnings("unused") final ParticipantIdChangedEvent event) {}
	
	protected void onParticipantTournamentIdChangedEvent(
			@SuppressWarnings("unused") final ParticipantTournamentIdChangedEvent event) {}
	
	protected void onParticipantNameChangedEvent(@SuppressWarnings("unused") final ParticipantNameChangedEvent event) {}
	
	protected void onParticipantChallongeUsernameChangedEvent(
			@SuppressWarnings("unused") final ParticipantChallongeUsernameChangedEvent event) {}
	
	protected void onParticipantSeedChangedEvent(@SuppressWarnings("unused") final ParticipantSeedChangedEvent event) {}
	
	protected void onParticipantMiscChangedEvent(@SuppressWarnings("unused") final ParticipantMiscChangedEvent event) {}
	
	protected void onParticipantActiveChangedEvent(
			@SuppressWarnings("unused") final ParticipantActiveChangedEvent event) {}
	
	protected void onParticipantCheckedInAtChangedEvent(
			@SuppressWarnings("unused") final ParticipantCheckedInAtChangedEvent event) {}
	
	protected void onParticipantCreatedAtChangedEvent(
			@SuppressWarnings("unused") final ParticipantCreatedAtChangedEvent event) {}
	
	protected void onParticipantFinalRankChangedEvent(
			@SuppressWarnings("unused") final ParticipantFinalRankChangedEvent event) {}
	
	protected void onParticipantGroupIdChangedEvent(
			@SuppressWarnings("unused") final ParticipantGroupIdChangedEvent event) {}
	
	protected void onParticipantIconChangedEvent(@SuppressWarnings("unused") final ParticipantIconChangedEvent event) {}
	
	protected void onParticipantInvitationIdChangedEvent(
			@SuppressWarnings("unused") final ParticipantInvitationIdChangedEvent event) {}
	
	protected void onParticipantInviteEmailChangedEvent(
			@SuppressWarnings("unused") final ParticipantInviteEmailChangedEvent event) {}
	
	protected void onParticipantOnWaitingListChangedEvent(
			@SuppressWarnings("unused") final ParticipantOnWaitingListChangedEvent event) {}
	
	protected void onParticipantUpdatedAtChangedEvent(
			@SuppressWarnings("unused") final ParticipantUpdatedAtChangedEvent event) {}
	
	protected void onParticipantChallongeEmailAddressVerifiedChangedEvent(
			@SuppressWarnings("unused") final ParticipantChallongeEmailAddressVerifiedChangedEvent event) {}
	
	protected void onParticipantRemovableChangedEvent(
			@SuppressWarnings("unused") final ParticipantRemovableChangedEvent event) {}
	
	protected void onParticipantParticipatableOrInvitationAttachedChangedEvent(
			@SuppressWarnings("unused") final ParticipantParticipatableOrInvitationAttachedChangedEvent event) {}
	
	protected void onParticipantConfirmRemoveChangedEvent(
			@SuppressWarnings("unused") final ParticipantConfirmRemoveChangedEvent event) {}
	
	protected void onParticipantInvitationPendingChangedEvent(
			@SuppressWarnings("unused") final ParticipantInvitationPendingChangedEvent event) {}
	
	protected void onParticipantDisplayNameWithInvitationEmailAddressChangedEvent(
			@SuppressWarnings("unused") final ParticipantDisplayNameWithInvitationEmailAddressChangedEvent event) {}
	
	protected void onParticipantEmailHashChangedEvent(
			@SuppressWarnings("unused") final ParticipantEmailHashChangedEvent event) {}
	
	protected void onParticipantUsernameChangedEvent(
			@SuppressWarnings("unused") final ParticipantUsernameChangedEvent event) {}
	
	protected void onParticipantAttachedParticipatablePortraitUrlChangedEvent(
			@SuppressWarnings("unused") final ParticipantAttachedParticipatablePortraitUrlChangedEvent event) {}
	
	protected void onParticipantCanCheckInChangedEvent(
			@SuppressWarnings("unused") final ParticipantCanCheckInChangedEvent event) {}
	
	protected void onParticipantCheckedInChangedEvent(
			@SuppressWarnings("unused") final ParticipantCheckedInChangedEvent event) {}
	
	protected void onParticipantReactivatableChangedEvent(
			@SuppressWarnings("unused") final ParticipantReactivatableChangedEvent event) {}
	
	protected void onParticipantMatchesChangedEvent(
			@SuppressWarnings("unused") final ParticipantMatchesChangedEvent event) {}
	
	protected void onParticipantMatchAddedEvent(@SuppressWarnings("unused") final ParticipantMatchAddedEvent event) {}
	
	protected void onParticipantMatchRemovedEvent(
			@SuppressWarnings("unused") final ParticipantMatchRemovedEvent event) {}
	
	// Match methods
	protected void onGenericMatchEvent(@SuppressWarnings("unused") final GenericMatchEvent event) {}
	
	protected void onMatchCreatedEvent(@SuppressWarnings("unused") final MatchCreatedEvent event) {}
	
	protected void onMatchDeletedEvent(@SuppressWarnings("unused") final MatchDeletedEvent event) {}
	
	protected void onGenericMatchChangedEvent(@SuppressWarnings("unused") final GenericMatchChangedEvent event) {}
	
	protected void onMatchIdChangedEvent(@SuppressWarnings("unused") final MatchIdChangedEvent event) {}
	
	protected void onMatchTournamentIdChangedEvent(
			@SuppressWarnings("unused") final MatchTournamentIdChangedEvent event) {}
	
	protected void onMatchAttachmentCountChangedEvent(
			@SuppressWarnings("unused") final MatchAttachmentCountChangedEvent event) {}
	
	protected void onMatchCreatedAtChangedEvent(@SuppressWarnings("unused") final MatchCreatedAtChangedEvent event) {}
	
	protected void onMatchGroupIdChangedEvent(@SuppressWarnings("unused") final MatchGroupIdChangedEvent event) {}
	
	protected void onMatchHasAttachmentChangedEvent(
			@SuppressWarnings("unused") final MatchHasAttachmentChangedEvent event) {}
	
	protected void onMatchIdentifierChangedEvent(@SuppressWarnings("unused") final MatchIdentifierChangedEvent event) {}
	
	protected void onMatchLocationChangedEvent(@SuppressWarnings("unused") final MatchLocationChangedEvent event) {}
	
	protected void onMatchLoserIdChangedEvent(@SuppressWarnings("unused") final MatchLoserIdChangedEvent event) {}
	
	protected void onMatchWinnerIdChangedEvent(@SuppressWarnings("unused") final MatchWinnerIdChangedEvent event) {}
	
	protected void onMatchPlayer1IdChangedEvent(@SuppressWarnings("unused") final MatchPlayer1IdChangedEvent event) {}
	
	protected void onMatchPlayer1IsPrerequisiteMatchLoserChangedEvent(
			@SuppressWarnings("unused") final MatchPlayer1IsPrerequisiteMatchLoserChangedEvent event) {}
	
	protected void onMatchPlayer1PrerequisiteMatchIdChangedEvent(
			@SuppressWarnings("unused") final MatchPlayer1PrerequisiteMatchIdChangedEvent event) {}
	
	protected void onMatchPlayer1VotesChangedEvent(
			@SuppressWarnings("unused") final MatchPlayer1VotesChangedEvent event) {}
	
	protected void onMatchPlayer2IdChangedEvent(@SuppressWarnings("unused") final MatchPlayer2IdChangedEvent event) {}
	
	protected void onMatchPlayer2IsPrerequisiteMatchLoserChangedEvent(
			@SuppressWarnings("unused") final MatchPlayer2IsPrerequisiteMatchLoserChangedEvent event) {}
	
	protected void onMatchPlayer2PrerequisiteMatchIdChangedEvent(
			@SuppressWarnings("unused") final MatchPlayer2PrerequisiteMatchIdChangedEvent event) {}
	
	protected void onMatchPlayer2VotesChangedEvent(
			@SuppressWarnings("unused") final MatchPlayer2VotesChangedEvent event) {}
	
	protected void onMatchRoundChangedEvent(@SuppressWarnings("unused") final MatchRoundChangedEvent event) {}
	
	protected void onMatchScheduledTimeChangedEvent(
			@SuppressWarnings("unused") final MatchScheduledTimeChangedEvent event) {}
	
	protected void onMatchStartedAtChangedEvent(@SuppressWarnings("unused") final MatchStartedAtChangedEvent event) {}
	
	protected void onMatchStateChangedEvent(@SuppressWarnings("unused") final MatchStateChangedEvent event) {}
	
	protected void onMatchUnderwayAtChangedEvent(@SuppressWarnings("unused") final MatchUnderwayAtChangedEvent event) {}
	
	protected void onMatchUpdatedAtChangedEvent(@SuppressWarnings("unused") final MatchUpdatedAtChangedEvent event) {}
	
	protected void onMatchPrerequisiteMatchIdsCsvChangedEvent(
			@SuppressWarnings("unused") final MatchPrerequisiteMatchIdsCsvChangedEvent event) {}
	
	protected void onMatchScoresCsvChangedEvent(@SuppressWarnings("unused") final MatchScoresCsvChangedEvent event) {}
	
	protected void onMatchAttachmentsChangedEvent(
			@SuppressWarnings("unused") final MatchAttachmentsChangedEvent event) {}
	
	// Attachment methods
	protected void onGenericAttachmentEvent(@SuppressWarnings("unused") final GenericAttachmentEvent event) {}
	
	protected void onAttachmentCreatedEvent(@SuppressWarnings("unused") final AttachmentCreatedEvent event) {}
	
	protected void onAttachmentDeletedEvent(@SuppressWarnings("unused") final AttachmentDeletedEvent event) {}
	
	protected void onAttachmentChangedEvent(@SuppressWarnings("unused") final GenericAttachmentChangedEvent event) {}
	
	protected void onAttachmentIdChangedEvent(@SuppressWarnings("unused") final AttachmentIdChangedEvent event) {}
	
	protected void onAttachmentMatchIdChangedEvent(
			@SuppressWarnings("unused") final AttachmentMatchIdChangedEvent event) {}
	
	protected void onAttachmentUserIdChangedEvent(
			@SuppressWarnings("unused") final AttachmentUserIdChangedEvent event) {}
	
	protected void onAttachmentDescriptionChangedEvent(
			@SuppressWarnings("unused") final AttachmentDescriptionChangedEvent event) {}
	
	protected void onAttachmentUrlChangedEvent(@SuppressWarnings("unused") final AttachmentUrlChangedEvent event) {}
	
	protected void onAttachmentOriginalFileNameChangedEvent(
			@SuppressWarnings("unused") final AttachmentOriginalFileNameChangedEvent event) {}
	
	protected void onAttachmentCreatedAtChangedEvent(
			@SuppressWarnings("unused") final AttachmentCreatedAtChangedEvent event) {}
	
	protected void onAttachmentUpdatedAtChangedEvent(
			@SuppressWarnings("unused") final AttachmentUpdatedAtChangedEvent event) {}
	
	protected void onAttachmentAssetFileNameChangedEvent(
			@SuppressWarnings("unused") final AttachmentAssetFileNameChangedEvent event) {}
	
	protected void onAttachmentAssetContentTypeChangedEvent(
			@SuppressWarnings("unused") final AttachmentAssetContentTypeChangedEvent event) {}
	
	protected void onAttachmentAssetFileSizeChangedEvent(
			@SuppressWarnings("unused") final AttachmentAssetFileSizeChangedEvent event) {}
	
	protected void onAttachmentAssetUrlChangedEvent(
			@SuppressWarnings("unused") final AttachmentAssetUrlChangedEvent event) {}
}
