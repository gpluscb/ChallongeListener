package com.github.gpluscb.challonge_listener.listener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.gpluscb.challonge_listener.events.GenericEvent;
import com.github.gpluscb.challonge_listener.events.tournament.GenericTournamentChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.GenericTournamentEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentAcceptAttachmentsChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentAcceptingPredictionsChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentAllowParticipantMatchReportingChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentAnonymousVotingChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentCategoryChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentCheckInDurationChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentCompletedAtChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentCreatedAtChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentCreatedByApiChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentCreatedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentCreditCappedChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentDeletedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentDescriptionChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentDescriptionSourceChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentDoesOwnChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentEventIdChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentFullChallongeUrlChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentGameIdChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentGameNameChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentGrandFinalsModifierChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentGroupStagesEnabledChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentGroupStagesWereStartedChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentHideForumChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentHideSeedsChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentHoldThirdPlaceMatchChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentIdChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentLiveImageUrlChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentLockedAtChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentMatchesChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentMaxPredictionsPerUserChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentNameChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentNotifyUsersWhenMatchesOpenChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentNotifyUsersWhenTheTournamentEndsChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentOpenSignupChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentParticipantsChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentParticipantsCountChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentParticipantsLockedChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentParticipantsSwappableChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentPointsForByeChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentPointsForGameTieChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentPointsForGameWinChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentPointsForMatchTieChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentPointsForMatchWinChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentPredictTheLosersBracketChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentPredictionMethodChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentPredictionsOpenedAtChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentPrivateOnlyChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentProgressMeterChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentPublicPredictionsBeforeStartTimeChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentQuickAdvanceChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentRankedByChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentRankedChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentRegistrationFeeChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentRegistrationTypeChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentRequireScoreAgreementChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentReviewBeforeFinalizingChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentRoundRobinIterationsChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentRoundRobinPointsForGameTieChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentRoundRobinPointsForGameWinChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentRoundRobinPointsForMatchTieChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentRoundRobinPointsForMatchWinChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentSequentialPairingsChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentShowRoundsChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentSignUpUrlChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentSignupCapChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentStartAtChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentStartedAtChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentStartedCheckingInAtChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentStateChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentSubdomainChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentSwissRoundsChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentTeamConvertableChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentTeamsChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentTieBreaksChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentTournamentTypeChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentUpdatedAtChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.TournamentUrlChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.GenericMatchChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.GenericMatchEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchAttachmentCountChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchAttachmentsChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchCompletedAtChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchCreatedAtChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchCreatedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchDeletedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchForfeitedChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchGroupIdChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchHasAttachmentChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchIdChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchIdentifierChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchLocationChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchLoserIdChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchOptionalChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchPlayer1IdChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchPlayer1IsPrerequisiteMatchLoserChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchPlayer1PrerequisiteMatchIdChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchPlayer1VotesChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchPlayer2IdChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchPlayer2IsPrerequisiteMatchLoserChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchPlayer2PrerequisiteMatchIdChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchPlayer2VotesChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchPrerequisiteMatchIdsCsvChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchRoundChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchScheduledTimeChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchScoresCsvChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchStartedAtChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchStateChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchTournamentIdChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchUnderwayAtChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchUpdatedAtChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.MatchWinnerIdChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentAssetContentTypeChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentAssetFileNameChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentAssetFileSizeChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentAssetUrlChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentCreatedAtChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentCreatedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentDeletedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentDescriptionChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentIdChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentMatchIdChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentOriginalFileNameChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentUpdatedAtChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentUrlChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.attachment.AttachmentUserIdChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.attachment.GenericAttachmentChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.match.attachment.GenericAttachmentEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.GenericParticipantChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.GenericParticipantEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantActiveChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantAttachedParticipatablePortraitUrlChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantCanCheckInChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantChallongeEmailAddressVerifiedChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantChallongeUsernameChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantCheckInOpenChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantCheckedInAtChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantCheckedInChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantConfirmRemoveChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantCreatedAtChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantCreatedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantDeletedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantDisplayNameChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantDisplayNameWithInvitationEmailAddressChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantEmailHashChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantFinalRankChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantGroupIdChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantHasIrrelevantSeedChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantIconChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantIdChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantInvitationIdChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantInvitationPendingChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantInviteEmailChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantMatchAddedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantMatchRemovedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantMatchesChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantMiscChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantNameChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantOnWaitingListChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantParticipatableOrInvitationAttachedChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantReactivatableChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantRemovableChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantSeedChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantTournamentIdChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantUpdatedAtChangedEvent;
import com.github.gpluscb.challonge_listener.events.tournament.participant.ParticipantUsernameChangedEvent;

/**
 * Breaks up the {@link com.github.gpluscb.challonge_listener.listener.EventListener
 * EventListener} to feature inheritable methods specific to one type of
 * {@link com.github.gpluscb.challonge_listener.events.GenericEvent GenericEvent}.
 *
 * @see com.github.gpluscb.challonge_listener.listener.EventListener EventListener
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
	public final void subscribeTo(final long... tournamentId) {
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
	public final boolean unsubscribeFrom(final long... tournamentId) {
		boolean removed = false;
		
		for(final long id : tournamentId) {
			if(this.subscribedTournamentIds.remove(Long.valueOf(id))) {
				removed = true;
			}
		}
		
		return removed;
	}
	
	@Override
	public final List<Long> getSubscribedTournamentIds() {
		return Collections.unmodifiableList(this.subscribedTournamentIds);
	}
	
	/**
	 * Fires an event to all of the applying methods.
	 *
	 * @param event
	 *            The event to be fired
	 */
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
	
	protected void onGenericEvent(final GenericEvent event) {}
	
	// Tournament methods
	protected void onGenericTournamentEvent(final GenericTournamentEvent event) {}
	
	protected void onTournamentCreatedEvent(final TournamentCreatedEvent event) {}
	
	protected void onTournamentDeletedEvent(final TournamentDeletedEvent event) {}
	
	protected void onGenericTournamentChangedEvent(final GenericTournamentChangedEvent event) {}
	
	protected void onTournamentDoesOwnChangedEvent(final TournamentDoesOwnChangedEvent event) {}
	
	protected void onTournamentIdChangedEvent(final TournamentIdChangedEvent event) {}
	
	protected void onTournamentUrlChangedEvent(final TournamentUrlChangedEvent event) {}
	
	protected void onTournamentNameChangedEvent(final TournamentNameChangedEvent event) {}
	
	protected void onTournamentTournamentTypeChangedEvent(final TournamentTournamentTypeChangedEvent event) {}
	
	protected void onTournamentSubdomainChangedEvent(final TournamentSubdomainChangedEvent event) {}
	
	protected void onTournamentDescriptionChangedEvent(final TournamentDescriptionChangedEvent event) {}
	
	protected void onTournamentOpenSignupChangedEvent(final TournamentOpenSignupChangedEvent event) {}
	
	protected void onTournamentHoldThirdPlaceMatchChangedEvent(final TournamentHoldThirdPlaceMatchChangedEvent event) {}
	
	protected void onTournamentRankedChangedEvent(final TournamentRankedChangedEvent event) {}
	
	protected void onTournamentPredictTheLosersBracketChangedEvent(
			final TournamentPredictTheLosersBracketChangedEvent event) {}
	
	protected void onTournamentPointsForMatchWinChangedEvent(final TournamentPointsForMatchWinChangedEvent event) {}
	
	protected void onTournamentPointsForMatchTieChangedEvent(final TournamentPointsForMatchTieChangedEvent event) {}
	
	protected void onTournamentPointsForGameWinChangedEvent(final TournamentPointsForGameWinChangedEvent event) {}
	
	protected void onTournamentPointsForGameTieChangedEvent(final TournamentPointsForGameTieChangedEvent event) {}
	
	protected void onTournamentPointsForByeChangedEvent(final TournamentPointsForByeChangedEvent event) {}
	
	protected void onTournamentSwissRoundsChangedEvent(final TournamentSwissRoundsChangedEvent event) {}
	
	protected void onTournamentRankedByChangedEvent(final TournamentRankedByChangedEvent event) {}
	
	protected void onTournamentRoundRobinIterationsChangedEvent(
			final TournamentRoundRobinIterationsChangedEvent event) {}
	
	protected void onTournamentRoundRobinPointsForGameWinChangedEvent(
			final TournamentRoundRobinPointsForGameWinChangedEvent event) {}
	
	protected void onTournamentRoundRobinPointsForGameTieChangedEvent(
			final TournamentRoundRobinPointsForGameTieChangedEvent event) {}
	
	protected void onTournamentRoundRobinPointsForMatchWinChangedEvent(
			final TournamentRoundRobinPointsForMatchWinChangedEvent event) {}
	
	protected void onTournamentRoundRobinPointsForMatchTieChangedEvent(
			final TournamentRoundRobinPointsForMatchTieChangedEvent event) {}
	
	protected void onTournamentAcceptAttachmentsChangedEvent(final TournamentAcceptAttachmentsChangedEvent event) {}
	
	protected void onTournamentHideForumChangedEvent(final TournamentHideForumChangedEvent event) {}
	
	protected void onTournamentShowRoundsChangedEvent(final TournamentShowRoundsChangedEvent event) {}
	
	protected void onTournamentPrivateOnlyChangedEvent(final TournamentPrivateOnlyChangedEvent event) {}
	
	protected void onTournamentNotifyUsersWhenTheTournamentEndsChangedEvent(
			final TournamentNotifyUsersWhenTheTournamentEndsChangedEvent event) {}
	
	protected void onTournamentSequentialPairingsChangedEvent(final TournamentSequentialPairingsChangedEvent event) {}
	
	protected void onTournamentRegistrationFeeChangedEvent(final TournamentRegistrationFeeChangedEvent event) {}
	
	protected void onTournamentRegistrationTypeChangedEvent(final TournamentRegistrationTypeChangedEvent event) {}
	
	protected void onTournamentSignupCapChangedEvent(final TournamentSignupCapChangedEvent event) {}
	
	protected void onTournamentStartAtChangedEvent(final TournamentStartAtChangedEvent event) {}
	
	protected void onTournamentCheckInDurationChangedEvent(final TournamentCheckInDurationChangedEvent event) {}
	
	protected void onTournamentAllowParticipantMatchReportingChangedEvent(
			final TournamentAllowParticipantMatchReportingChangedEvent event) {}
	
	protected void onTournamentAnonymousVotingChangedEvent(final TournamentAnonymousVotingChangedEvent event) {}
	
	protected void onTournamentCategoryChangedEvent(final TournamentCategoryChangedEvent event) {}
	
	protected void onTournamentCompletedAtChangedEvent(final TournamentCompletedAtChangedEvent event) {}
	
	protected void onTournamentCreatedAtChangedEvent(final TournamentCreatedAtChangedEvent event) {}
	
	protected void onTournamentCreatedByApiChangedEvent(final TournamentCreatedByApiChangedEvent event) {}
	
	protected void onTournamentCreditCappedChangedEvent(final TournamentCreditCappedChangedEvent event) {}
	
	protected void onTournamentGameIdChangedEvent(final TournamentGameIdChangedEvent event) {}
	
	protected void onTournamentGroupStagesEnabledChangedEvent(final TournamentGroupStagesEnabledChangedEvent event) {}
	
	protected void onTournamentHideSeedsChangedEvent(final TournamentHideSeedsChangedEvent event) {}
	
	protected void onTournamentMaxPredictionsPerUserChangedEvent(
			final TournamentMaxPredictionsPerUserChangedEvent event) {}
	
	protected void onTournamentNotifyUsersWhenMatchesOpenChangedEvent(
			final TournamentNotifyUsersWhenMatchesOpenChangedEvent event) {}
	
	protected void onTournamentParticipantsCountChangedEvent(final TournamentParticipantsCountChangedEvent event) {}
	
	protected void onTournamentPredictionMethodChangedEvent(final TournamentPredictionMethodChangedEvent event) {}
	
	protected void onTournamentPredictionsOpenedAtChangedEvent(final TournamentPredictionsOpenedAtChangedEvent event) {}
	
	protected void onTournamentProgressMeterChangedEvent(final TournamentProgressMeterChangedEvent event) {}
	
	protected void onTournamentQuickAdvanceChangedEvent(final TournamentQuickAdvanceChangedEvent event) {}
	
	protected void onTournamentRequireScoreAgreementChangedEvent(
			final TournamentRequireScoreAgreementChangedEvent event) {}
	
	protected void onTournamentStartedAtChangedEvent(final TournamentStartedAtChangedEvent event) {}
	
	protected void onTournamentStartedCheckingInAtChangedEvent(final TournamentStartedCheckingInAtChangedEvent event) {}
	
	protected void onTournamentStateChangedEvent(final TournamentStateChangedEvent event) {}
	
	protected void onTournamentTeamsChangedEvent(final TournamentTeamsChangedEvent event) {}
	
	protected void onTournamentTieBreaksChangedEvent(final TournamentTieBreaksChangedEvent event) {}
	
	protected void onTournamentUpdatedAtChangedEvent(final TournamentUpdatedAtChangedEvent event) {}
	
	protected void onTournamentDescriptionSourceChangedEvent(final TournamentDescriptionSourceChangedEvent event) {}
	
	protected void onTournamentFullChallongeUrlChangedEvent(final TournamentFullChallongeUrlChangedEvent event) {}
	
	protected void onTournamentLiveImageUrlChangedEvent(final TournamentLiveImageUrlChangedEvent event) {}
	
	protected void onTournamentSignUpUrlChangedEvent(final TournamentSignUpUrlChangedEvent event) {}
	
	protected void onTournamentReviewBeforeFinalizingChangedEvent(
			final TournamentReviewBeforeFinalizingChangedEvent event) {}
	
	protected void onTournamentAcceptingPredictionsChangedEvent(
			final TournamentAcceptingPredictionsChangedEvent event) {}
	
	protected void onTournamentParticipantsLockedChangedEvent(final TournamentParticipantsLockedChangedEvent event) {}
	
	protected void onTournamentGameNameChangedEvent(final TournamentGameNameChangedEvent event) {}
	
	protected void onTournamentParticipantsSwappableChangedEvent(
			final TournamentParticipantsSwappableChangedEvent event) {}
	
	protected void onTournamentTeamConvertableChangedEvent(final TournamentTeamConvertableChangedEvent event) {}
	
	protected void onTournamentGroupStagesWereStartedChangedEvent(
			final TournamentGroupStagesWereStartedChangedEvent event) {}
	
	protected void onTournamentLockedAtChangedEvent(final TournamentLockedAtChangedEvent event) {}
	
	protected void onTournamentEventIdChangedEvent(final TournamentEventIdChangedEvent event) {}
	
	protected void onTournamentPublicPredictionsBeforeStartTimeChangedEvent(
			final TournamentPublicPredictionsBeforeStartTimeChangedEvent event) {}
	
	protected void onTournamentGrandFinalsModifierChangedEvent(final TournamentGrandFinalsModifierChangedEvent event) {}
	
	protected void onTournamentParticipantsChangedEvent(final TournamentParticipantsChangedEvent event) {}
	
	protected void onTournamentMatchesChangedEvent(final TournamentMatchesChangedEvent event) {}
	
	// Participant methods
	protected void onGenericParticipantEvent(final GenericParticipantEvent event) {}
	
	protected void onParticipantCreatedEvent(final ParticipantCreatedEvent event) {}
	
	protected void onParticipantDeletedEvent(final ParticipantDeletedEvent event) {}
	
	protected void onGenericParticipantChangedEvent(final GenericParticipantChangedEvent event) {}
	
	protected void onParticipantIdChangedEvent(final ParticipantIdChangedEvent event) {}
	
	protected void onParticipantTournamentIdChangedEvent(final ParticipantTournamentIdChangedEvent event) {}
	
	protected void onParticipantNameChangedEvent(final ParticipantNameChangedEvent event) {}
	
	protected void onParticipantDisplayNameChangedEvent(final ParticipantDisplayNameChangedEvent event) {}
	
	protected void onParticipantCheckInOpenChangedEvent(final ParticipantCheckInOpenChangedEvent event) {}
	
	protected void onParticipantHasIrrelevantSeedChangedEvent(final ParticipantHasIrrelevantSeedChangedEvent event) {}
	
	protected void onParticipantChallongeUsernameChangedEvent(final ParticipantChallongeUsernameChangedEvent event) {}
	
	protected void onParticipantSeedChangedEvent(final ParticipantSeedChangedEvent event) {}
	
	protected void onParticipantMiscChangedEvent(final ParticipantMiscChangedEvent event) {}
	
	protected void onParticipantActiveChangedEvent(final ParticipantActiveChangedEvent event) {}
	
	protected void onParticipantCheckedInAtChangedEvent(final ParticipantCheckedInAtChangedEvent event) {}
	
	protected void onParticipantCreatedAtChangedEvent(final ParticipantCreatedAtChangedEvent event) {}
	
	protected void onParticipantFinalRankChangedEvent(final ParticipantFinalRankChangedEvent event) {}
	
	protected void onParticipantGroupIdChangedEvent(final ParticipantGroupIdChangedEvent event) {}
	
	protected void onParticipantIconChangedEvent(final ParticipantIconChangedEvent event) {}
	
	protected void onParticipantInvitationIdChangedEvent(final ParticipantInvitationIdChangedEvent event) {}
	
	protected void onParticipantInviteEmailChangedEvent(final ParticipantInviteEmailChangedEvent event) {}
	
	protected void onParticipantOnWaitingListChangedEvent(final ParticipantOnWaitingListChangedEvent event) {}
	
	protected void onParticipantUpdatedAtChangedEvent(final ParticipantUpdatedAtChangedEvent event) {}
	
	protected void onParticipantChallongeEmailAddressVerifiedChangedEvent(
			final ParticipantChallongeEmailAddressVerifiedChangedEvent event) {}
	
	protected void onParticipantRemovableChangedEvent(final ParticipantRemovableChangedEvent event) {}
	
	protected void onParticipantParticipatableOrInvitationAttachedChangedEvent(
			final ParticipantParticipatableOrInvitationAttachedChangedEvent event) {}
	
	protected void onParticipantConfirmRemoveChangedEvent(final ParticipantConfirmRemoveChangedEvent event) {}
	
	protected void onParticipantInvitationPendingChangedEvent(final ParticipantInvitationPendingChangedEvent event) {}
	
	protected void onParticipantDisplayNameWithInvitationEmailAddressChangedEvent(
			final ParticipantDisplayNameWithInvitationEmailAddressChangedEvent event) {}
	
	protected void onParticipantEmailHashChangedEvent(final ParticipantEmailHashChangedEvent event) {}
	
	protected void onParticipantUsernameChangedEvent(final ParticipantUsernameChangedEvent event) {}
	
	protected void onParticipantAttachedParticipatablePortraitUrlChangedEvent(
			final ParticipantAttachedParticipatablePortraitUrlChangedEvent event) {}
	
	protected void onParticipantCanCheckInChangedEvent(final ParticipantCanCheckInChangedEvent event) {}
	
	protected void onParticipantCheckedInChangedEvent(final ParticipantCheckedInChangedEvent event) {}
	
	protected void onParticipantReactivatableChangedEvent(final ParticipantReactivatableChangedEvent event) {}
	
	protected void onParticipantMatchesChangedEvent(final ParticipantMatchesChangedEvent event) {}
	
	protected void onParticipantMatchAddedEvent(final ParticipantMatchAddedEvent event) {}
	
	protected void onParticipantMatchRemovedEvent(final ParticipantMatchRemovedEvent event) {}
	
	// Match methods
	protected void onGenericMatchEvent(final GenericMatchEvent event) {}
	
	protected void onMatchCreatedEvent(final MatchCreatedEvent event) {}
	
	protected void onMatchDeletedEvent(final MatchDeletedEvent event) {}
	
	protected void onGenericMatchChangedEvent(final GenericMatchChangedEvent event) {}
	
	protected void onMatchIdChangedEvent(final MatchIdChangedEvent event) {}
	
	protected void onMatchTournamentIdChangedEvent(final MatchTournamentIdChangedEvent event) {}
	
	protected void onMatchAttachmentCountChangedEvent(final MatchAttachmentCountChangedEvent event) {}
	
	protected void onMatchCreatedAtChangedEvent(final MatchCreatedAtChangedEvent event) {}
	
	protected void onMatchGroupIdChangedEvent(final MatchGroupIdChangedEvent event) {}
	
	protected void onMatchHasAttachmentChangedEvent(final MatchHasAttachmentChangedEvent event) {}
	
	protected void onMatchIdentifierChangedEvent(final MatchIdentifierChangedEvent event) {}
	
	protected void onMatchLocationChangedEvent(final MatchLocationChangedEvent event) {}
	
	protected void onMatchLoserIdChangedEvent(final MatchLoserIdChangedEvent event) {}
	
	protected void onMatchWinnerIdChangedEvent(final MatchWinnerIdChangedEvent event) {}
	
	protected void onMatchPlayer1IdChangedEvent(final MatchPlayer1IdChangedEvent event) {}
	
	protected void onMatchPlayer1IsPrerequisiteMatchLoserChangedEvent(
			final MatchPlayer1IsPrerequisiteMatchLoserChangedEvent event) {}
	
	protected void onMatchPlayer1PrerequisiteMatchIdChangedEvent(
			final MatchPlayer1PrerequisiteMatchIdChangedEvent event) {}
	
	protected void onMatchPlayer1VotesChangedEvent(final MatchPlayer1VotesChangedEvent event) {}
	
	protected void onMatchPlayer2IdChangedEvent(final MatchPlayer2IdChangedEvent event) {}
	
	protected void onMatchPlayer2IsPrerequisiteMatchLoserChangedEvent(
			final MatchPlayer2IsPrerequisiteMatchLoserChangedEvent event) {}
	
	protected void onMatchPlayer2PrerequisiteMatchIdChangedEvent(
			final MatchPlayer2PrerequisiteMatchIdChangedEvent event) {}
	
	protected void onMatchPlayer2VotesChangedEvent(final MatchPlayer2VotesChangedEvent event) {}
	
	protected void onMatchRoundChangedEvent(final MatchRoundChangedEvent event) {}
	
	protected void onMatchScheduledTimeChangedEvent(final MatchScheduledTimeChangedEvent event) {}
	
	protected void onMatchStartedAtChangedEvent(final MatchStartedAtChangedEvent event) {}
	
	protected void onMatchStateChangedEvent(final MatchStateChangedEvent event) {}
	
	protected void onMatchUnderwayAtChangedEvent(final MatchUnderwayAtChangedEvent event) {}
	
	protected void onMatchUpdatedAtChangedEvent(final MatchUpdatedAtChangedEvent event) {}
	
	protected void onMatchCompletedAtChangedEvent(final MatchCompletedAtChangedEvent event) {}
	
	protected void onMatchPrerequisiteMatchIdsCsvChangedEvent(final MatchPrerequisiteMatchIdsCsvChangedEvent event) {}
	
	protected void onMatchScoresCsvChangedEvent(final MatchScoresCsvChangedEvent event) {}
	
	protected void onMatchOptionalChangedEvent(final MatchOptionalChangedEvent event) {}
	
	protected void onMatchForfeitedChangedEvent(final MatchForfeitedChangedEvent event) {}
	
	protected void onMatchAttachmentsChangedEvent(final MatchAttachmentsChangedEvent event) {}
	
	// Attachment methods
	protected void onGenericAttachmentEvent(final GenericAttachmentEvent event) {}
	
	protected void onAttachmentCreatedEvent(final AttachmentCreatedEvent event) {}
	
	protected void onAttachmentDeletedEvent(final AttachmentDeletedEvent event) {}
	
	protected void onAttachmentChangedEvent(final GenericAttachmentChangedEvent event) {}
	
	protected void onAttachmentIdChangedEvent(final AttachmentIdChangedEvent event) {}
	
	protected void onAttachmentMatchIdChangedEvent(final AttachmentMatchIdChangedEvent event) {}
	
	protected void onAttachmentUserIdChangedEvent(final AttachmentUserIdChangedEvent event) {}
	
	protected void onAttachmentDescriptionChangedEvent(final AttachmentDescriptionChangedEvent event) {}
	
	protected void onAttachmentUrlChangedEvent(final AttachmentUrlChangedEvent event) {}
	
	protected void onAttachmentOriginalFileNameChangedEvent(final AttachmentOriginalFileNameChangedEvent event) {}
	
	protected void onAttachmentCreatedAtChangedEvent(final AttachmentCreatedAtChangedEvent event) {}
	
	protected void onAttachmentUpdatedAtChangedEvent(final AttachmentUpdatedAtChangedEvent event) {}
	
	protected void onAttachmentAssetFileNameChangedEvent(final AttachmentAssetFileNameChangedEvent event) {}
	
	protected void onAttachmentAssetContentTypeChangedEvent(final AttachmentAssetContentTypeChangedEvent event) {}
	
	protected void onAttachmentAssetFileSizeChangedEvent(final AttachmentAssetFileSizeChangedEvent event) {}
	
	protected void onAttachmentAssetUrlChangedEvent(final AttachmentAssetUrlChangedEvent event) {}
}
