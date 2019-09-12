# ChallongeListener
A project using the [stefangeyer/challonge-java](https://github.com/stefangeyer/challonge-java) project to fire events to listeners when an owned or co-owned challonge tournament changes.

## TODOs, no particular order:
- [ ] [Maybe give ParticipantMatchEvents own abstract class and different names](/src/main/java/com/gpluscb/challonge_listener/listener/ListenerAdapter.java#L23)
	
	Pro:
	* Technically more consistent
	* Might be useful for reflection
	
	Con:
	* Potentially less readable
	* Would treat these events as more than just another ParticipantChangedEvent, which is at least worth a discussion
- [ ] [Maybe use reflection in ListenerAdapter#onEvent(GenericEvent event)](/src/main/java/com/gpluscb/challonge_listener/listener/ListenerAdapter.java#L24)
	
	Pro:
	* Shorter, maybe more readable
	* Already used reflection for similiar tasks
	
	Con:
	* ParticipantMatchEvents mark some inconsistency reflection would have a hard time dealing with
	* Potentially attracting errors, difficult to test
	* Unnecessary work

- [ ] [Public void ListenerManager#update() method for manual updates](/src/main/java/com/gpluscb/challonge_listener/listener/ListenerManager.java#L54)

	Pro:
	* Neat feature for anyone who would need it (and testing)
	
	Con:
	* Cannot think of anyone who would need it (except tests)
	
- [ ] [Find better solution than a private object for wait/notify calls. Also learn about synchronizing and race conditions](/src/main/java/com/gpluscb/challonge_listener/listener/ListenerManager.java#L75)

- [ ] [Replace ugly multiple try system for the case that a tournament changes in the middle of getting it from the api](/src/main/java/com/gpluscb/challonge_listener/listener/ListenerManager.java#L162)

- [ ] [Make ChallongeExtension#doesExist(String tournament) check more elegant and safer](/src/main/java/com/gpluscb/challonge_listener/ChallongeExtension.java#L59)

- [ ] [Make ChallongeExtension#doesExist(Tournament tournament) more elegant and safer](/src/main/java/com/gpluscb/challonge_listener/ChallongeExtension.java#L86)

- [ ] [Make ChallongeExtension#addMissingData() methods public](/src/main/java/com/gpluscb/challonge_listener/ChallongeExtension.java#L243)

	Pro:
	* Neat feature for anyone who would need it
	
	Con:
	* Cannot think of anyone who would need it when methods to get stuff with full data already exist.
	
- [ ] [Noticed weird behaviour of match/has_attachment (always false) and match/attachment_count (null until attachment is added (not 0, null)). Should annoy Challonge support about that](/src/test/java/com/gpluscb/challonge_listener/ChallongeExtensionTest.java#L28)