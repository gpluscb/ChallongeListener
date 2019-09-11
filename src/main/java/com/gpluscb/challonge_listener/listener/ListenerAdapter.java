package com.gpluscb.challonge_listener.listener;

import com.gpluscb.challonge_listener.events.GenericEvent;
import com.gpluscb.challonge_listener.events.tournament.*;
import com.gpluscb.challonge_listener.events.tournament.match.*;
import com.gpluscb.challonge_listener.events.tournament.match.attachment.*;
import com.gpluscb.challonge_listener.events.tournament.participant.*;

/**
 * Breaks up the {@link com.gpluscb.challonge_listener.listener.EventListener
 * EventListener} to feature inheritable methods specific to one type of
 * {@link com.gpluscb.challonge_listener.events.GenericEvent GenericEvent}.
 * 
 * @see com.gpluscb.challonge_listener.listener.EventListener EventListener
 */
public abstract class ListenerAdapter implements EventListener {
	/**
	 * Fires an event to all of the applying methods.
	 * 
	 * @param event
	 *            The event to be fired
	 */
	// TODO: Give ParticipantMatchChangedEvents own abstract class
	// TODO: Weigh benefits and disadvantages of reflection (I mean I have it
	// done soooo...)
	@Override
	public final void onEvent(GenericEvent event) {
		onGenericEvent(event);
		if(event instanceof GenericTournamentEvent) {
			onGenericTournamentEvent((GenericTournamentEvent) event);
			if(event instanceof TournamentCreatedEvent) {
				onTournamentCreatedEvent((TournamentCreatedEvent) event);
			} else if(event instanceof TournamentDeletedEvent) {
				onTournamentDeletedEvent((TournamentDeletedEvent) event);
			} else if(event instanceof GenericTournamentChangedEvent) {
				onGenericTournamentChangedEvent((GenericTournamentChangedEvent) event);
				if(event instanceof TournamentAcceptAttachmentsChangedEvent) {
					onTournamentAcceptAttachmentsChangedEvent((TournamentAcceptAttachmentsChangedEvent) event);
				} else if(event instanceof TournamentAcceptingPredictionsChangedEvent) {
					onTournamentAcceptingPredictionsChangedEvent((TournamentAcceptingPredictionsChangedEvent) event);
				} else if(event instanceof TournamentAllowParticipantMatchReportingChangedEvent) {
					onTournamentAllowParticipantMatchReportingChangedEvent(
							(TournamentAllowParticipantMatchReportingChangedEvent) event);
				} else if(event instanceof TournamentAnonymousVotingChangedEvent) {
					onTournamentAnonymousVotingChangedEvent((TournamentAnonymousVotingChangedEvent) event);
				} else if(event instanceof TournamentCategoryChangedEvent) {
					onTournamentCategoryChangedEvent((TournamentCategoryChangedEvent) event);
				} else if(event instanceof TournamentCheckInDurationChangedEvent) {
					onTournamentCheckInDurationChangedEvent((TournamentCheckInDurationChangedEvent) event);
				} else if(event instanceof TournamentCompletedAtChangedEvent) {
					onTournamentCompletedAtChangedEvent((TournamentCompletedAtChangedEvent) event);
				} else if(event instanceof TournamentCreatedAtChangedEvent) {
					onTournamentCreatedAtChangedEvent((TournamentCreatedAtChangedEvent) event);
				} else if(event instanceof TournamentCreatedByApiChangedEvent) {
					onTournamentCreatedByApiChangedEvent((TournamentCreatedByApiChangedEvent) event);
				} else if(event instanceof TournamentCreditCappedChangedEvent) {
					onTournamentCreditCappedChangedEvent((TournamentCreditCappedChangedEvent) event);
				} else if(event instanceof TournamentDescriptionChangedEvent) {
					onTournamentDescriptionChangedEvent((TournamentDescriptionChangedEvent) event);
				} else if(event instanceof TournamentDescriptionSourceChangedEvent) {
					onTournamentDescriptionSourceChangedEvent((TournamentDescriptionSourceChangedEvent) event);
				} else if(event instanceof TournamentEventIdChangedEvent) {
					onTournamentEventIdChangedEvent((TournamentEventIdChangedEvent) event);
				} else if(event instanceof TournamentFullChallongeUrlChangedEvent) {
					onTournamentFullChallongeUrlChangedEvent((TournamentFullChallongeUrlChangedEvent) event);
				} else if(event instanceof TournamentGameIdChangedEvent) {
					onTournamentGameIdChangedEvent((TournamentGameIdChangedEvent) event);
				} else if(event instanceof TournamentGameNameChangedEvent) {
					onTournamentGameNameChangedEvent((TournamentGameNameChangedEvent) event);
				} else if(event instanceof TournamentGrandFinalsModifierChangedEvent) {
					onTournamentGrandFinalsModifierChangedEvent((TournamentGrandFinalsModifierChangedEvent) event);
				} else if(event instanceof TournamentGroupStagesEnabledChangedEvent) {
					onTournamentGroupStagesEnabledChangedEvent((TournamentGroupStagesEnabledChangedEvent) event);
				} else if(event instanceof TournamentGroupStagesWereStartedChangedEvent) {
					onTournamentGroupStagesWereStartedChangedEvent(
							(TournamentGroupStagesWereStartedChangedEvent) event);
				} else if(event instanceof TournamentHideForumChangedEvent) {
					onTournamentHideForumChangedEvent((TournamentHideForumChangedEvent) event);
				} else if(event instanceof TournamentHideSeedsChangedEvent) {
					onTournamentHideSeedsChangedEvent((TournamentHideSeedsChangedEvent) event);
				} else if(event instanceof TournamentHoldThirdPlaceMatchChangedEvent) {
					onTournamentHoldThirdPlaceMatchChangedEvent((TournamentHoldThirdPlaceMatchChangedEvent) event);
				} else if(event instanceof TournamentIdChangedEvent) {
					onTournamentIdChangedEvent((TournamentIdChangedEvent) event);
				} else if(event instanceof TournamentLiveImageUrlChangedEvent) {
					onTournamentLiveImageUrlChangedEvent((TournamentLiveImageUrlChangedEvent) event);
				} else if(event instanceof TournamentLockedAtChangedEvent) {
					onTournamentLockedAtChangedEvent((TournamentLockedAtChangedEvent) event);
				} else if(event instanceof TournamentMatchesChangedEvent) {
					onTournamentMatchesChangedEvent((TournamentMatchesChangedEvent) event);
				} else if(event instanceof TournamentMaxPredictionsPerUserChangedEvent) {
					onTournamentMaxPredictionsPerUserChangedEvent((TournamentMaxPredictionsPerUserChangedEvent) event);
				} else if(event instanceof TournamentNameChangedEvent) {
					onTournamentNameChangedEvent((TournamentNameChangedEvent) event);
				} else if(event instanceof TournamentNotifyUsersWhenMatchesOpenChangedEvent) {
					onTournamentNotifyUsersWhenMatchesOpenChangedEvent(
							(TournamentNotifyUsersWhenMatchesOpenChangedEvent) event);
				} else if(event instanceof TournamentNotifyUsersWhenTheTournamentEndsChangedEvent) {
					onTournamentNotifyUsersWhenTheTournamentEndsChangedEvent(
							(TournamentNotifyUsersWhenTheTournamentEndsChangedEvent) event);
				} else if(event instanceof TournamentOpenSignupChangedEvent) {
					onTournamentOpenSignupChangedEvent((TournamentOpenSignupChangedEvent) event);
				} else if(event instanceof TournamentParticipantsChangedEvent) {
					onTournamentParticipantsChangedEvent((TournamentParticipantsChangedEvent) event);
				} else if(event instanceof TournamentParticipantsCountChangedEvent) {
					onTournamentParticipantsCountChangedEvent((TournamentParticipantsCountChangedEvent) event);
				} else if(event instanceof TournamentParticipantsLockedChangedEvent) {
					onTournamentParticipantsLockedChangedEvent((TournamentParticipantsLockedChangedEvent) event);
				} else if(event instanceof TournamentParticipantsSwappableChangedEvent) {
					onTournamentParticipantsSwappableChangedEvent((TournamentParticipantsSwappableChangedEvent) event);
				} else if(event instanceof TournamentPointsForByeChangedEvent) {
					onTournamentPointsForByeChangedEvent((TournamentPointsForByeChangedEvent) event);
				} else if(event instanceof TournamentPointsForGameTieChangedEvent) {
					onTournamentPointsForGameTieChangedEvent((TournamentPointsForGameTieChangedEvent) event);
				} else if(event instanceof TournamentPointsForGameWinChangedEvent) {
					onTournamentPointsForGameWinChangedEvent((TournamentPointsForGameWinChangedEvent) event);
				} else if(event instanceof TournamentPointsForMatchTieChangedEvent) {
					onTournamentPointsForMatchTieChangedEvent((TournamentPointsForMatchTieChangedEvent) event);
				} else if(event instanceof TournamentPointsForMatchWinChangedEvent) {
					onTournamentPointsForMatchWinChangedEvent((TournamentPointsForMatchWinChangedEvent) event);
				} else if(event instanceof TournamentPredictionMethodChangedEvent) {
					onTournamentPredictionMethodChangedEvent((TournamentPredictionMethodChangedEvent) event);
				} else if(event instanceof TournamentPredictionsOpenedAtChangedEvent) {
					onTournamentPredictionsOpenedAtChangedEvent((TournamentPredictionsOpenedAtChangedEvent) event);
				} else if(event instanceof TournamentPrivateOnlyChangedEvent) {
					onTournamentPrivateOnlyChangedEvent((TournamentPrivateOnlyChangedEvent) event);
				} else if(event instanceof TournamentProgressMeterChangedEvent) {
					onTournamentProgressMeterChangedEvent((TournamentProgressMeterChangedEvent) event);
				} else if(event instanceof TournamentPublicPredictionsBeforeStartTimeChangedEvent) {
					onTournamentPublicPredictionsBeforeStartTimeChangedEvent(
							(TournamentPublicPredictionsBeforeStartTimeChangedEvent) event);
				} else if(event instanceof TournamentQuickAdvanceChangedEvent) {
					onTournamentQuickAdvanceChangedEvent((TournamentQuickAdvanceChangedEvent) event);
				} else if(event instanceof TournamentRankedByChangedEvent) {
					onTournamentRankedByChangedEvent((TournamentRankedByChangedEvent) event);
				} else if(event instanceof TournamentRequireScoreAgreementChangedEvent) {
					onTournamentRequireScoreAgreementChangedEvent((TournamentRequireScoreAgreementChangedEvent) event);
				} else if(event instanceof TournamentReviewBeforeFinalizingChangedEvent) {
					onTournamentReviewBeforeFinalizingChangedEvent(
							(TournamentReviewBeforeFinalizingChangedEvent) event);
				} else if(event instanceof TournamentRoundRobinPointsForGameTieChangedEvent) {
					onTournamentRoundRobinPointsForGameTieChangedEvent(
							(TournamentRoundRobinPointsForGameTieChangedEvent) event);
				} else if(event instanceof TournamentRoundRobinPointsForGameWinChangedEvent) {
					onTournamentRoundRobinPointsForGameWinChangedEvent(
							(TournamentRoundRobinPointsForGameWinChangedEvent) event);
				} else if(event instanceof TournamentRoundRobinPointsForMatchTieChangedEvent) {
					onTournamentRoundRobinPointsForMatchTieChangedEvent(
							(TournamentRoundRobinPointsForMatchTieChangedEvent) event);
				} else if(event instanceof TournamentRoundRobinPointsForMatchWinChangedEvent) {
					onTournamentRoundRobinPointsForMatchWinChangedEvent(
							(TournamentRoundRobinPointsForMatchWinChangedEvent) event);
				} else if(event instanceof TournamentSequentialPairingsChangedEvent) {
					onTournamentSequentialPairingsChangedEvent((TournamentSequentialPairingsChangedEvent) event);
				} else if(event instanceof TournamentShowRoundsChangedEvent) {
					onTournamentShowRoundsChangedEvent((TournamentShowRoundsChangedEvent) event);
				} else if(event instanceof TournamentSignupCapChangedEvent) {
					onTournamentSignupCapChangedEvent((TournamentSignupCapChangedEvent) event);
				} else if(event instanceof TournamentSignUpUrlChangedEvent) {
					onTournamentSignUpUrlChangedEvent((TournamentSignUpUrlChangedEvent) event);
				} else if(event instanceof TournamentStartAtChangedEvent) {
					onTournamentStartAtChangedEvent((TournamentStartAtChangedEvent) event);
				} else if(event instanceof TournamentStartedAtChangedEvent) {
					onTournamentStartedAtChangedEvent((TournamentStartedAtChangedEvent) event);
				} else if(event instanceof TournamentStartedCheckingInAtChangedEvent) {
					onTournamentStartedCheckingInAtChangedEvent((TournamentStartedCheckingInAtChangedEvent) event);
				} else if(event instanceof TournamentStateChangedEvent) {
					onTournamentStateChangedEvent((TournamentStateChangedEvent) event);
				} else if(event instanceof TournamentSubdomainChangedEvent) {
					onTournamentSubdomainChangedEvent((TournamentSubdomainChangedEvent) event);
				} else if(event instanceof TournamentSwissRoundsChangedEvent) {
					onTournamentSwissRoundsChangedEvent((TournamentSwissRoundsChangedEvent) event);
				} else if(event instanceof TournamentTeamConvertableChangedEvent) {
					onTournamentTeamConvertableChangedEvent((TournamentTeamConvertableChangedEvent) event);
				} else if(event instanceof TournamentTeamsChangedEvent) {
					onTournamentTeamsChangedEvent((TournamentTeamsChangedEvent) event);
				} else if(event instanceof TournamentTieBreaksChangedEvent) {
					onTournamentTieBreaksChangedEvent((TournamentTieBreaksChangedEvent) event);
				} else if(event instanceof TournamentTournamentTypeChangedEvent) {
					onTournamentTournamentTypeChangedEvent((TournamentTournamentTypeChangedEvent) event);
				} else if(event instanceof TournamentUpdatedAtChangedEvent) {
					onTournamentUpdatedAtChangedEvent((TournamentUpdatedAtChangedEvent) event);
				} else if(event instanceof TournamentUrlChangedEvent) {
					onTournamentUrlChangedEvent((TournamentUrlChangedEvent) event);
				} else if(event instanceof GenericParticipantEvent) {
					onGenericParticipantEvent((GenericParticipantEvent) event);
					if(event instanceof ParticipantCreatedEvent) {
						onParticipantCreatedEvent((ParticipantCreatedEvent) event);
					} else if(event instanceof ParticipantDeletedEvent) {
						onParticipantDeletedEvent((ParticipantDeletedEvent) event);
					} else if(event instanceof GenericParticipantChangedEvent) {
						onGenericParticipantChangedEvent((GenericParticipantChangedEvent) event);
						if(event instanceof ParticipantActiveChangedEvent) {
							onParticipantActiveChangedEvent((ParticipantActiveChangedEvent) event);
						} else if(event instanceof ParticipantAttachedParticipatablePortraitUrlChangedEvent) {
							onParticipantAttachedParticipatablePortraitUrlChangedEvent(
									(ParticipantAttachedParticipatablePortraitUrlChangedEvent) event);
						} else if(event instanceof ParticipantCanCheckInChangedEvent) {
							onParticipantCanCheckInChangedEvent((ParticipantCanCheckInChangedEvent) event);
						} else if(event instanceof ParticipantChallongeEmailAddressVerifiedChangedEvent) {
							onParticipantChallongeEmailAddressVerifiedChangedEvent(
									(ParticipantChallongeEmailAddressVerifiedChangedEvent) event);
						} else if(event instanceof ParticipantChallongeUsernameChangedEvent) {
							onParticipantChallongeUsernameChangedEvent(
									(ParticipantChallongeUsernameChangedEvent) event);
						} else if(event instanceof ParticipantCheckedInAtChangedEvent) {
							onParticipantCheckedInAtChangedEvent((ParticipantCheckedInAtChangedEvent) event);
						} else if(event instanceof ParticipantCheckedInChangedEvent) {
							onParticipantCheckedInChangedEvent((ParticipantCheckedInChangedEvent) event);
						} else if(event instanceof ParticipantConfirmRemoveChangedEvent) {
							onParticipantConfirmRemoveChangedEvent((ParticipantConfirmRemoveChangedEvent) event);
						} else if(event instanceof ParticipantCreatedAtChangedEvent) {
							onParticipantCreatedAtChangedEvent((ParticipantCreatedAtChangedEvent) event);
						} else if(event instanceof ParticipantDisplayNameWithInvitationEmailAddressChangedEvent) {
							onParticipantDisplayNameWithInvitationEmailAddressChangedEvent(
									(ParticipantDisplayNameWithInvitationEmailAddressChangedEvent) event);
						} else if(event instanceof ParticipantEmailHashChangedEvent) {
							onParticipantEmailHashChangedEvent((ParticipantEmailHashChangedEvent) event);
						} else if(event instanceof ParticipantFinalRankChangedEvent) {
							onParticipantFinalRankChangedEvent((ParticipantFinalRankChangedEvent) event);
						} else if(event instanceof ParticipantGroupIdChangedEvent) {
							onParticipantGroupIdChangedEvent((ParticipantGroupIdChangedEvent) event);
						} else if(event instanceof ParticipantIconChangedEvent) {
							onParticipantIconChangedEvent((ParticipantIconChangedEvent) event);
						} else if(event instanceof ParticipantIdChangedEvent) {
							onParticipantIdChangedEvent((ParticipantIdChangedEvent) event);
						} else if(event instanceof ParticipantInvitationIdChangedEvent) {
							onParticipantInvitationIdChangedEvent((ParticipantInvitationIdChangedEvent) event);
						} else if(event instanceof ParticipantInvitationPendingChangedEvent) {
							onParticipantInvitationPendingChangedEvent(
									(ParticipantInvitationPendingChangedEvent) event);
						} else if(event instanceof ParticipantInviteEmailChangedEvent) {
							onParticipantInviteEmailChangedEvent((ParticipantInviteEmailChangedEvent) event);
						} else if(event instanceof ParticipantMatchesChangedEvent) {
							onParticipantMatchesChangedEvent((ParticipantMatchesChangedEvent) event);
						} else if(event instanceof ParticipantMiscChangedEvent) {
							onParticipantMiscChangedEvent((ParticipantMiscChangedEvent) event);
						} else if(event instanceof ParticipantNameChangedEvent) {
							onParticipantNameChangedEvent((ParticipantNameChangedEvent) event);
						} else if(event instanceof ParticipantOnWaitingListChangedEvent) {
							onParticipantOnWaitingListChangedEvent((ParticipantOnWaitingListChangedEvent) event);
						} else if(event instanceof ParticipantParticipatableOrInvitationAttachedChangedEvent) {
							onParticipantParticipatableOrInvitationAttachedChangedEvent(
									(ParticipantParticipatableOrInvitationAttachedChangedEvent) event);
						} else if(event instanceof ParticipantReactivatableChangedEvent) {
							onParticipantReactivatableChangedEvent((ParticipantReactivatableChangedEvent) event);
						} else if(event instanceof ParticipantRemovableChangedEvent) {
							onParticipantRemovableChangedEvent((ParticipantRemovableChangedEvent) event);
						} else if(event instanceof ParticipantSeedChangedEvent) {
							onParticipantSeedChangedEvent((ParticipantSeedChangedEvent) event);
						} else if(event instanceof ParticipantTournamentIdChangedEvent) {
							onParticipantTournamentIdChangedEvent((ParticipantTournamentIdChangedEvent) event);
						} else if(event instanceof ParticipantUpdatedAtChangedEvent) {
							onParticipantUpdatedAtChangedEvent((ParticipantUpdatedAtChangedEvent) event);
						} else if(event instanceof ParticipantUsernameChangedEvent) {
							onParticipantUsernameChangedEvent((ParticipantUsernameChangedEvent) event);
						} else if(event instanceof ParticipantMatchAddedEvent) {
							onParticipantMatchAddedEvent((ParticipantMatchAddedEvent) event);
						} else if(event instanceof ParticipantMatchRemovedEvent) {
							onParticipantMatchRemovedEvent((ParticipantMatchRemovedEvent) event);
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
						if(event instanceof MatchAttachmentCountChangedEvent) {
							onMatchAttachmentCountChangedEvent((MatchAttachmentCountChangedEvent) event);
						} else if(event instanceof MatchAttachmentsChangedEvent) {
							onMatchAttachmentsChangedEvent((MatchAttachmentsChangedEvent) event);
						} else if(event instanceof MatchCreatedAtChangedEvent) {
							onMatchCreatedAtChangedEvent((MatchCreatedAtChangedEvent) event);
						} else if(event instanceof MatchGroupIdChangedEvent) {
							onMatchGroupIdChangedEvent((MatchGroupIdChangedEvent) event);
						} else if(event instanceof MatchHasAttachmentChangedEvent) {
							onMatchHasAttachmentChangedEvent((MatchHasAttachmentChangedEvent) event);
						} else if(event instanceof MatchIdChangedEvent) {
							onMatchIdChangedEvent((MatchIdChangedEvent) event);
						} else if(event instanceof MatchIdentifierChangedEvent) {
							onMatchIdentifierChangedEvent((MatchIdentifierChangedEvent) event);
						} else if(event instanceof MatchLocationChangedEvent) {
							onMatchLocationChangedEvent((MatchLocationChangedEvent) event);
						} else if(event instanceof MatchLoserIdChangedEvent) {
							onMatchLoserIdChangedEvent((MatchLoserIdChangedEvent) event);
						} else if(event instanceof MatchPlayer1IdChangedEvent) {
							onMatchPlayer1IdChangedEvent((MatchPlayer1IdChangedEvent) event);
						} else if(event instanceof MatchPlayer1IsPrerequisiteMatchLoserChangedEvent) {
							onMatchPlayer1IsPrerequisiteMatchLoserChangedEvent(
									(MatchPlayer1IsPrerequisiteMatchLoserChangedEvent) event);
						} else if(event instanceof MatchPlayer1PrerequisiteMatchIdChangedEvent) {
							onMatchPlayer1PrerequisiteMatchIdChangedEvent(
									(MatchPlayer1PrerequisiteMatchIdChangedEvent) event);
						} else if(event instanceof MatchPlayer1VotesChangedEvent) {
							onMatchPlayer1VotesChangedEvent((MatchPlayer1VotesChangedEvent) event);
						} else if(event instanceof MatchPlayer2IdChangedEvent) {
							onMatchPlayer2IdChangedEvent((MatchPlayer2IdChangedEvent) event);
						} else if(event instanceof MatchPlayer2IsPrerequisiteMatchLoserChangedEvent) {
							onMatchPlayer2IsPrerequisiteMatchLoserChangedEvent(
									(MatchPlayer2IsPrerequisiteMatchLoserChangedEvent) event);
						} else if(event instanceof MatchPlayer2PrerequisiteMatchIdChangedEvent) {
							onMatchPlayer2PrerequisiteMatchIdChangedEvent(
									(MatchPlayer2PrerequisiteMatchIdChangedEvent) event);
						} else if(event instanceof MatchPlayer2VotesChangedEvent) {
							onMatchPlayer2VotesChangedEvent((MatchPlayer2VotesChangedEvent) event);
						} else if(event instanceof MatchPrerequisiteMatchIdsCsvChangedEvent) {
							onMatchPrerequisiteMatchIdsCsvChangedEvent(
									(MatchPrerequisiteMatchIdsCsvChangedEvent) event);
						} else if(event instanceof MatchRoundChangedEvent) {
							onMatchRoundChangedEvent((MatchRoundChangedEvent) event);
						} else if(event instanceof MatchScheduledTimeChangedEvent) {
							onMatchScheduledTimeChangedEvent((MatchScheduledTimeChangedEvent) event);
						} else if(event instanceof MatchScoresCsvChangedEvent) {
							onMatchScoresCsvChangedEvent((MatchScoresCsvChangedEvent) event);
						} else if(event instanceof MatchStartedAtChangedEvent) {
							onMatchStartedAtChangedEvent((MatchStartedAtChangedEvent) event);
						} else if(event instanceof MatchStateChangedEvent) {
							onMatchStateChangedEvent((MatchStateChangedEvent) event);
						} else if(event instanceof MatchTournamentIdChangedEvent) {
							onMatchTournamentIdChangedEvent((MatchTournamentIdChangedEvent) event);
						} else if(event instanceof MatchUnderwayAtChangedEvent) {
							onMatchUnderwayAtChangedEvent((MatchUnderwayAtChangedEvent) event);
						} else if(event instanceof MatchUpdatedAtChangedEvent) {
							onMatchUpdatedAtChangedEvent((MatchUpdatedAtChangedEvent) event);
						} else if(event instanceof MatchWinnerIdChangedEvent) {
							onMatchWinnerIdChangedEvent((MatchWinnerIdChangedEvent) event);
						} else if(event instanceof GenericAttachmentEvent) {
							onGenericAttachmentEvent((GenericAttachmentEvent) event);
							if(event instanceof AttachmentCreatedEvent) {
								onAttachmentCreatedEvent((AttachmentCreatedEvent) event);
							} else if(event instanceof AttachmentDeletedEvent) {
								onAttachmentDeletedEvent((AttachmentDeletedEvent) event);
							} else if(event instanceof GenericAttachmentChangedEvent) {
								onAttachmentChangedEvent((GenericAttachmentChangedEvent) event);
								if(event instanceof AttachmentAssetContentTypeChangedEvent) {
									onAttachmentAssetContentTypeChangedEvent(
											(AttachmentAssetContentTypeChangedEvent) event);
								} else if(event instanceof AttachmentAssetFileNameChangedEvent) {
									onAttachmentAssetFileNameChangedEvent((AttachmentAssetFileNameChangedEvent) event);
								} else if(event instanceof AttachmentAssetFileSizeChangedEvent) {
									onAttachmentAssetFileSizeChangedEvent((AttachmentAssetFileSizeChangedEvent) event);
								} else if(event instanceof AttachmentAssetUrlChangedEvent) {
									onAttachmentAssetUrlChangedEvent((AttachmentAssetUrlChangedEvent) event);
								} else if(event instanceof AttachmentCreatedAtChangedEvent) {
									onAttachmentCreatedAtChangedEvent((AttachmentCreatedAtChangedEvent) event);
								} else if(event instanceof AttachmentDescriptionChangedEvent) {
									onAttachmentDescriptionChangedEvent((AttachmentDescriptionChangedEvent) event);
								} else if(event instanceof AttachmentIdChangedEvent) {
									onAttachmentIdChangedEvent((AttachmentIdChangedEvent) event);
								} else if(event instanceof AttachmentMatchIdChangedEvent) {
									onAttachmentMatchIdChangedEvent((AttachmentMatchIdChangedEvent) event);
								} else if(event instanceof AttachmentOriginalFileNameChangedEvent) {
									onAttachmentOriginalFileNameChangedEvent(
											(AttachmentOriginalFileNameChangedEvent) event);
								} else if(event instanceof AttachmentUpdatedAtChangedEvent) {
									onAttachmentUpdatedAtChangedEvent((AttachmentUpdatedAtChangedEvent) event);
								} else if(event instanceof AttachmentUrlChangedEvent) {
									onAttachmentUrlChangedEvent((AttachmentUrlChangedEvent) event);
								} else if(event instanceof AttachmentUserIdChangedEvent) {
									onAttachmentUserIdChangedEvent((AttachmentUserIdChangedEvent) event);
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void onGenericEvent(GenericEvent event) {}
	
	// Tournament methods
	public void onGenericTournamentEvent(GenericTournamentEvent event) {}
	
	public void onTournamentCreatedEvent(TournamentCreatedEvent event) {}
	
	public void onTournamentDeletedEvent(TournamentDeletedEvent event) {}
	
	public void onGenericTournamentChangedEvent(GenericTournamentChangedEvent event) {}
	
	public void onTournamentIdChangedEvent(TournamentIdChangedEvent event) {}
	
	public void onTournamentUrlChangedEvent(TournamentUrlChangedEvent event) {}
	
	public void onTournamentNameChangedEvent(TournamentNameChangedEvent event) {}
	
	public void onTournamentTournamentTypeChangedEvent(TournamentTournamentTypeChangedEvent event) {}
	
	public void onTournamentSubdomainChangedEvent(TournamentSubdomainChangedEvent event) {}
	
	public void onTournamentDescriptionChangedEvent(TournamentDescriptionChangedEvent event) {}
	
	public void onTournamentOpenSignupChangedEvent(TournamentOpenSignupChangedEvent event) {}
	
	public void onTournamentHoldThirdPlaceMatchChangedEvent(TournamentHoldThirdPlaceMatchChangedEvent event) {}
	
	public void onTournamentPointsForMatchWinChangedEvent(TournamentPointsForMatchWinChangedEvent event) {}
	
	public void onTournamentPointsForMatchTieChangedEvent(TournamentPointsForMatchTieChangedEvent event) {}
	
	public void onTournamentPointsForGameWinChangedEvent(TournamentPointsForGameWinChangedEvent event) {}
	
	public void onTournamentPointsForGameTieChangedEvent(TournamentPointsForGameTieChangedEvent event) {}
	
	public void onTournamentPointsForByeChangedEvent(TournamentPointsForByeChangedEvent event) {}
	
	public void onTournamentSwissRoundsChangedEvent(TournamentSwissRoundsChangedEvent event) {}
	
	public void onTournamentRankedByChangedEvent(TournamentRankedByChangedEvent event) {}
	
	public void onTournamentRoundRobinPointsForGameWinChangedEvent(
			TournamentRoundRobinPointsForGameWinChangedEvent event) {}
	
	public void onTournamentRoundRobinPointsForGameTieChangedEvent(
			TournamentRoundRobinPointsForGameTieChangedEvent event) {}
	
	public void onTournamentRoundRobinPointsForMatchWinChangedEvent(
			TournamentRoundRobinPointsForMatchWinChangedEvent event) {}
	
	public void onTournamentRoundRobinPointsForMatchTieChangedEvent(
			TournamentRoundRobinPointsForMatchTieChangedEvent event) {}
	
	public void onTournamentAcceptAttachmentsChangedEvent(TournamentAcceptAttachmentsChangedEvent event) {}
	
	public void onTournamentHideForumChangedEvent(TournamentHideForumChangedEvent event) {}
	
	public void onTournamentShowRoundsChangedEvent(TournamentShowRoundsChangedEvent event) {}
	
	public void onTournamentPrivateOnlyChangedEvent(TournamentPrivateOnlyChangedEvent event) {}
	
	public void onTournamentNotifyUsersWhenTheTournamentEndsChangedEvent(
			TournamentNotifyUsersWhenTheTournamentEndsChangedEvent event) {}
	
	public void onTournamentSequentialPairingsChangedEvent(TournamentSequentialPairingsChangedEvent event) {}
	
	public void onTournamentSignupCapChangedEvent(TournamentSignupCapChangedEvent event) {}
	
	public void onTournamentStartAtChangedEvent(TournamentStartAtChangedEvent event) {}
	
	public void onTournamentCheckInDurationChangedEvent(TournamentCheckInDurationChangedEvent event) {}
	
	public void onTournamentAllowParticipantMatchReportingChangedEvent(
			TournamentAllowParticipantMatchReportingChangedEvent event) {}
	
	public void onTournamentAnonymousVotingChangedEvent(TournamentAnonymousVotingChangedEvent event) {}
	
	public void onTournamentCategoryChangedEvent(TournamentCategoryChangedEvent event) {}
	
	public void onTournamentCompletedAtChangedEvent(TournamentCompletedAtChangedEvent event) {}
	
	public void onTournamentCreatedAtChangedEvent(TournamentCreatedAtChangedEvent event) {}
	
	public void onTournamentCreatedByApiChangedEvent(TournamentCreatedByApiChangedEvent event) {}
	
	public void onTournamentCreditCappedChangedEvent(TournamentCreditCappedChangedEvent event) {}
	
	public void onTournamentGameIdChangedEvent(TournamentGameIdChangedEvent event) {}
	
	public void onTournamentGroupStagesEnabledChangedEvent(TournamentGroupStagesEnabledChangedEvent event) {}
	
	public void onTournamentHideSeedsChangedEvent(TournamentHideSeedsChangedEvent event) {}
	
	public void onTournamentMaxPredictionsPerUserChangedEvent(TournamentMaxPredictionsPerUserChangedEvent event) {}
	
	public void onTournamentNotifyUsersWhenMatchesOpenChangedEvent(
			TournamentNotifyUsersWhenMatchesOpenChangedEvent event) {}
	
	public void onTournamentParticipantsCountChangedEvent(TournamentParticipantsCountChangedEvent event) {}
	
	public void onTournamentPredictionMethodChangedEvent(TournamentPredictionMethodChangedEvent event) {}
	
	public void onTournamentPredictionsOpenedAtChangedEvent(TournamentPredictionsOpenedAtChangedEvent event) {}
	
	public void onTournamentProgressMeterChangedEvent(TournamentProgressMeterChangedEvent event) {}
	
	public void onTournamentQuickAdvanceChangedEvent(TournamentQuickAdvanceChangedEvent event) {}
	
	public void onTournamentRequireScoreAgreementChangedEvent(TournamentRequireScoreAgreementChangedEvent event) {}
	
	public void onTournamentStartedAtChangedEvent(TournamentStartedAtChangedEvent event) {}
	
	public void onTournamentStartedCheckingInAtChangedEvent(TournamentStartedCheckingInAtChangedEvent event) {}
	
	public void onTournamentStateChangedEvent(TournamentStateChangedEvent event) {}
	
	public void onTournamentTeamsChangedEvent(TournamentTeamsChangedEvent event) {}
	
	public void onTournamentTieBreaksChangedEvent(TournamentTieBreaksChangedEvent event) {}
	
	public void onTournamentUpdatedAtChangedEvent(TournamentUpdatedAtChangedEvent event) {}
	
	public void onTournamentDescriptionSourceChangedEvent(TournamentDescriptionSourceChangedEvent event) {}
	
	public void onTournamentFullChallongeUrlChangedEvent(TournamentFullChallongeUrlChangedEvent event) {}
	
	public void onTournamentLiveImageUrlChangedEvent(TournamentLiveImageUrlChangedEvent event) {}
	
	public void onTournamentSignUpUrlChangedEvent(TournamentSignUpUrlChangedEvent event) {}
	
	public void onTournamentReviewBeforeFinalizingChangedEvent(TournamentReviewBeforeFinalizingChangedEvent event) {}
	
	public void onTournamentAcceptingPredictionsChangedEvent(TournamentAcceptingPredictionsChangedEvent event) {}
	
	public void onTournamentParticipantsLockedChangedEvent(TournamentParticipantsLockedChangedEvent event) {}
	
	public void onTournamentGameNameChangedEvent(TournamentGameNameChangedEvent event) {}
	
	public void onTournamentParticipantsSwappableChangedEvent(TournamentParticipantsSwappableChangedEvent event) {}
	
	public void onTournamentTeamConvertableChangedEvent(TournamentTeamConvertableChangedEvent event) {}
	
	public void onTournamentGroupStagesWereStartedChangedEvent(TournamentGroupStagesWereStartedChangedEvent event) {}
	
	public void onTournamentLockedAtChangedEvent(TournamentLockedAtChangedEvent event) {}
	
	public void onTournamentEventIdChangedEvent(TournamentEventIdChangedEvent event) {}
	
	public void onTournamentPublicPredictionsBeforeStartTimeChangedEvent(
			TournamentPublicPredictionsBeforeStartTimeChangedEvent event) {}
	
	public void onTournamentGrandFinalsModifierChangedEvent(TournamentGrandFinalsModifierChangedEvent event) {}
	
	public void onTournamentParticipantsChangedEvent(TournamentParticipantsChangedEvent event) {}
	
	public void onTournamentMatchesChangedEvent(TournamentMatchesChangedEvent event) {}
	
	// Participant methods
	public void onGenericParticipantEvent(GenericParticipantEvent event) {}
	
	public void onParticipantCreatedEvent(ParticipantCreatedEvent event) {}
	
	public void onParticipantDeletedEvent(ParticipantDeletedEvent event) {}
	
	public void onGenericParticipantChangedEvent(GenericParticipantChangedEvent event) {}
	
	public void onParticipantIdChangedEvent(ParticipantIdChangedEvent event) {}
	
	public void onParticipantTournamentIdChangedEvent(ParticipantTournamentIdChangedEvent event) {}
	
	public void onParticipantNameChangedEvent(ParticipantNameChangedEvent event) {}
	
	public void onParticipantChallongeUsernameChangedEvent(ParticipantChallongeUsernameChangedEvent event) {}
	
	public void onParticipantSeedChangedEvent(ParticipantSeedChangedEvent event) {}
	
	public void onParticipantMiscChangedEvent(ParticipantMiscChangedEvent event) {}
	
	public void onParticipantActiveChangedEvent(ParticipantActiveChangedEvent event) {}
	
	public void onParticipantCheckedInAtChangedEvent(ParticipantCheckedInAtChangedEvent event) {}
	
	public void onParticipantCreatedAtChangedEvent(ParticipantCreatedAtChangedEvent event) {}
	
	public void onParticipantFinalRankChangedEvent(ParticipantFinalRankChangedEvent event) {}
	
	public void onParticipantGroupIdChangedEvent(ParticipantGroupIdChangedEvent event) {}
	
	public void onParticipantIconChangedEvent(ParticipantIconChangedEvent event) {}
	
	public void onParticipantInvitationIdChangedEvent(ParticipantInvitationIdChangedEvent event) {}
	
	public void onParticipantInviteEmailChangedEvent(ParticipantInviteEmailChangedEvent event) {}
	
	public void onParticipantOnWaitingListChangedEvent(ParticipantOnWaitingListChangedEvent event) {}
	
	public void onParticipantUpdatedAtChangedEvent(ParticipantUpdatedAtChangedEvent event) {}
	
	public void onParticipantChallongeEmailAddressVerifiedChangedEvent(
			ParticipantChallongeEmailAddressVerifiedChangedEvent event) {}
	
	public void onParticipantRemovableChangedEvent(ParticipantRemovableChangedEvent event) {}
	
	public void onParticipantParticipatableOrInvitationAttachedChangedEvent(
			ParticipantParticipatableOrInvitationAttachedChangedEvent event) {}
	
	public void onParticipantConfirmRemoveChangedEvent(ParticipantConfirmRemoveChangedEvent event) {}
	
	public void onParticipantInvitationPendingChangedEvent(ParticipantInvitationPendingChangedEvent event) {}
	
	public void onParticipantDisplayNameWithInvitationEmailAddressChangedEvent(
			ParticipantDisplayNameWithInvitationEmailAddressChangedEvent event) {}
	
	public void onParticipantEmailHashChangedEvent(ParticipantEmailHashChangedEvent event) {}
	
	public void onParticipantUsernameChangedEvent(ParticipantUsernameChangedEvent event) {}
	
	public void onParticipantAttachedParticipatablePortraitUrlChangedEvent(
			ParticipantAttachedParticipatablePortraitUrlChangedEvent event) {}
	
	public void onParticipantCanCheckInChangedEvent(ParticipantCanCheckInChangedEvent event) {}
	
	public void onParticipantCheckedInChangedEvent(ParticipantCheckedInChangedEvent event) {}
	
	public void onParticipantReactivatableChangedEvent(ParticipantReactivatableChangedEvent event) {}
	
	public void onParticipantMatchesChangedEvent(ParticipantMatchesChangedEvent event) {}
	
	public void onParticipantMatchAddedEvent(ParticipantMatchAddedEvent event) {}
	
	public void onParticipantMatchRemovedEvent(ParticipantMatchRemovedEvent event) {}
	
	// Match methods
	public void onGenericMatchEvent(GenericMatchEvent event) {}
	
	public void onMatchCreatedEvent(MatchCreatedEvent event) {}
	
	public void onMatchDeletedEvent(MatchDeletedEvent event) {}
	
	public void onGenericMatchChangedEvent(GenericMatchChangedEvent event) {}
	
	public void onMatchIdChangedEvent(MatchIdChangedEvent event) {}
	
	public void onMatchTournamentIdChangedEvent(MatchTournamentIdChangedEvent event) {}
	
	public void onMatchAttachmentCountChangedEvent(MatchAttachmentCountChangedEvent event) {}
	
	public void onMatchCreatedAtChangedEvent(MatchCreatedAtChangedEvent event) {}
	
	public void onMatchGroupIdChangedEvent(MatchGroupIdChangedEvent event) {}
	
	public void onMatchHasAttachmentChangedEvent(MatchHasAttachmentChangedEvent event) {}
	
	public void onMatchIdentifierChangedEvent(MatchIdentifierChangedEvent event) {}
	
	public void onMatchLocationChangedEvent(MatchLocationChangedEvent event) {}
	
	public void onMatchLoserIdChangedEvent(MatchLoserIdChangedEvent event) {}
	
	public void onMatchWinnerIdChangedEvent(MatchWinnerIdChangedEvent event) {}
	
	public void onMatchPlayer1IdChangedEvent(MatchPlayer1IdChangedEvent event) {}
	
	public void onMatchPlayer1IsPrerequisiteMatchLoserChangedEvent(
			MatchPlayer1IsPrerequisiteMatchLoserChangedEvent event) {}
	
	public void onMatchPlayer1PrerequisiteMatchIdChangedEvent(MatchPlayer1PrerequisiteMatchIdChangedEvent event) {}
	
	public void onMatchPlayer1VotesChangedEvent(MatchPlayer1VotesChangedEvent event) {}
	
	public void onMatchPlayer2IdChangedEvent(MatchPlayer2IdChangedEvent event) {}
	
	public void onMatchPlayer2IsPrerequisiteMatchLoserChangedEvent(
			MatchPlayer2IsPrerequisiteMatchLoserChangedEvent event) {}
	
	public void onMatchPlayer2PrerequisiteMatchIdChangedEvent(MatchPlayer2PrerequisiteMatchIdChangedEvent event) {}
	
	public void onMatchPlayer2VotesChangedEvent(MatchPlayer2VotesChangedEvent event) {}
	
	public void onMatchRoundChangedEvent(MatchRoundChangedEvent event) {}
	
	public void onMatchScheduledTimeChangedEvent(MatchScheduledTimeChangedEvent event) {}
	
	public void onMatchStartedAtChangedEvent(MatchStartedAtChangedEvent event) {}
	
	public void onMatchStateChangedEvent(MatchStateChangedEvent event) {}
	
	public void onMatchUnderwayAtChangedEvent(MatchUnderwayAtChangedEvent event) {}
	
	public void onMatchUpdatedAtChangedEvent(MatchUpdatedAtChangedEvent event) {}
	
	public void onMatchPrerequisiteMatchIdsCsvChangedEvent(MatchPrerequisiteMatchIdsCsvChangedEvent event) {}
	
	public void onMatchScoresCsvChangedEvent(MatchScoresCsvChangedEvent event) {}
	
	public void onMatchAttachmentsChangedEvent(MatchAttachmentsChangedEvent event) {}
	
	// Attachment methods
	public void onGenericAttachmentEvent(GenericAttachmentEvent event) {}
	
	public void onAttachmentCreatedEvent(AttachmentCreatedEvent event) {}
	
	public void onAttachmentDeletedEvent(AttachmentDeletedEvent event) {}
	
	public void onAttachmentChangedEvent(GenericAttachmentChangedEvent event) {}
	
	public void onAttachmentIdChangedEvent(AttachmentIdChangedEvent event) {}
	
	public void onAttachmentMatchIdChangedEvent(AttachmentMatchIdChangedEvent event) {}
	
	public void onAttachmentUserIdChangedEvent(AttachmentUserIdChangedEvent event) {}
	
	public void onAttachmentDescriptionChangedEvent(AttachmentDescriptionChangedEvent event) {}
	
	public void onAttachmentUrlChangedEvent(AttachmentUrlChangedEvent event) {}
	
	public void onAttachmentOriginalFileNameChangedEvent(AttachmentOriginalFileNameChangedEvent event) {}
	
	public void onAttachmentCreatedAtChangedEvent(AttachmentCreatedAtChangedEvent event) {}
	
	public void onAttachmentUpdatedAtChangedEvent(AttachmentUpdatedAtChangedEvent event) {}
	
	public void onAttachmentAssetFileNameChangedEvent(AttachmentAssetFileNameChangedEvent event) {}
	
	public void onAttachmentAssetContentTypeChangedEvent(AttachmentAssetContentTypeChangedEvent event) {}
	
	public void onAttachmentAssetFileSizeChangedEvent(AttachmentAssetFileSizeChangedEvent event) {}
	
	public void onAttachmentAssetUrlChangedEvent(AttachmentAssetUrlChangedEvent event) {}
}
