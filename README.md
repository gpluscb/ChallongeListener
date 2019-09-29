# ChallongeListener
A project using the [stefangeyer/challonge-java](https://github.com/stefangeyer/challonge-java) project to fire events to listeners when an owned or co-owned challonge tournament changes.

## TODOs, no particular order:
- [ ] Maybe give ParticipantMatchEvents own abstract class and different names
	
	Pro:
	* Technically more consistent
	* Might be useful for reflection
	
	Con:
	* Potentially less readable
	* Would treat these events as more than just another ParticipantChangedEvent, which is at least worth a discussion
- [x] Maybe use reflection in ListenerAdapter#onEvent(GenericEvent event)
	
	Pro:
	* Shorter, maybe more readable
	* Already used reflection for similiar tasks
	
	Con:
	* ParticipantMatchEvents mark some inconsistency reflection would have a hard time dealing with
	* Potentially attracting errors, difficult to test
	* Unnecessary work

- [ ] Public void ListenerManager#update() method for manual updates

	Pro:
	* Neat feature for anyone who would need it (and testing)
	
	Con:
	* Cannot think of anyone who would need it (except tests)
	
- [ ] Find better solution than a private object for wait/notify calls. Also learn about synchronizing and race conditions

- [ ] Replace ugly multiple try system for the case that a tournament changes in the middle of getting it from the api

- [ ] Make ChallongeExtension#doesExist(String tournament) check more elegant and safer

- [ ] Make ChallongeExtension#addMissingData() methods public
	Pro:
	* Neat feature for anyone who would need it
	
	Con:
	* Cannot think of anyone who would need it when methods to get stuff with full data already exist.
	
- [x] Noticed weird behaviour of match/has_attachment (always false) and match/attachment_count (null until attachment is added (not 0, null)). Should annoy Challonge support about that