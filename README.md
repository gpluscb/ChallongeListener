# ChallongeListener
A project using the [stefangeyer/challonge-java](https://github.com/stefangeyer/challonge-java) project to fire events to listeners when a tournament changes.

## Using this project:

### Download:
This repo is published as a snapshot on Sonatype OSS. You can download it [here](https://oss.sonatype.org/content/repositories/snapshots/com/github/gpluscb/ChallongeListener/).\
If you are using Maven, add the following to your repositories in your pom.xml:
```
<repository>
	<id>oss.sonatype.org-snapshot</id>
	<url>http://oss.sonatype.org/content/repositories/snapshots</url>
	<releases>
		<enabled>false</enabled>
	</releases>
	<snapshots>
		<enabled>true</enabled>
	</snapshots>
</repository>
```
And this to your dependencies:
```
<dependency>
	<groupId>com.github.gpluscb</groupId>
	<artifactId>ChallongeListener</artifactId>
	<version>1.0.0-SNAPSHOT</version>
</dependency>
```
If you are using Gradle, add the following to your repositories in your build.gradle:
```
maven {
	url 'https://oss.sonatype.org/content/repositories/snapshots/'
}
```
And this to your dependencies:
```
compile group: 'com.github.gpluscb', name: 'ChallongeListener', version: '1.0.0-SNAPSHOT'
```

### Getting started:
Check out the [ChallongeListener example](src/examples/java/com/gpluscb/challonge_listener/ChallongeListenerExample.java) for a quick example.

#### ListenerManager
To listen to anything, you need a [ListenerManager](src/main/java/com/gpluscb/challonge_listener/listener/ListenerManager.java) instance. This instance will fire all the events to your listeners.
```
final ListenerManager manager = new ListenerManager(challonge, 5000);
```
As you can see, you will need to pass a ChallongeExtension instance and (optionally) a long value to the constructor.\
The [ChallongeExtension class](src/main/java/com/gpluscb/challonge_listener/ChallongeExtension.java) works mostly the same as the [Challonge class](https://github.com/stefangeyer/challonge-java/blob/master/core/src/main/java/at/stefangeyer/challonge/Challonge.java) of the challonge-java project, the difference being that ChallongeExtension implements some methods that go beyond what the [Challonge API](https://api.challonge.com/v1) directly offers whereas the usual Challonge class is intended to be more of a direct representation of the Challonge API in java.\
The long value represents the time of the update cycle. Since the Challonge API does not push updates to our client as they happen, the state of a tournament has to be requested from the Challonge API at fixed times and then compared to its previous state. The long value passed to the constructor is the minimum time between updates in milliseconds. 5000ms/5s is the default, if you only pass the ChallongeExtension instance to the constructor. If you pass 0, the instance will try to update as quickly as possible. There is no official stance on api spam or something like that by Challonge as far as I can tell, so I am not at fault if you get limited or banned. Just a quick heads up: the more unique tournaments you subscribe to and the more attachments these tournaments have, the more api calls are made each update cycle.

#### EventListeners
If you want to subscribe to tournaments and utilize events, you need to add EventListeners. There are currently two classes you can utilize as EventListeners: [EventListener](src/main/java/com/gpluscb/challonge_listener/listener/EventListener.java) and [ListenerAdapter](src/main/java/com/gpluscb/challonge_listener/listener/ListenerAdapter.java).\
EventListener is an interface and you can override its `onEvent(GenericEvent)` and `getSubscribedTournamentIds()` methods. The `onEvent` method is called whenever a tournament of which the id is contained within the list returned by the `getSubscribedTournamentIds` method by the ListenerManager instances the listener is added to changes.\
ListenerAdapter is an abstract class with a method for each possible event. Thus you can extend for example only from the\
`onTournamentDescriptionChangedEvent(TournamentDescriptionChangedEvent)`\
method to have your code executed every time the tournament description changes. The ListenerAdapter also implements methods to both add an id to and remove an id from the list of subscribed to tournaments, so you do not have to worry about that too much. These methods are `subscribeTo(long)` and `unsubscribeFrom(long)`.\
Of course, your ListenerManager instance cannot fire any events to your listeners if it does not know about these. You can change that by using the `ListenerManager#addListener(EventListener)` method.

#### Application flow
Since the ListenerManager runs on a seperate thread, there are several methods to communicate between threads and control application flow.\
For a start, if you want your application to shut down smoothly, you will need to shut down your ListenerManager as well using the `shutdown()` method.\
Also, you can wait until the ListenerManager instance reaches some specified state via its `awaitState(ManagerState)` method. Use its `awaitReady()` method to block the current thread until the ListenerManager is ready.

### Documentation:
The documentation is located in the [/docs/](/docs/) folder. It can be accessed at the github pages of this project [here](https://gpluscb.github.io/ChallongeListener/).

## Bigger ideas:

### 1: Reflection vs. script code generation vs. annotation based code generation:
Currently, runtime based reflection is used to handle the enormous amount of events. The events themselves as well as the methods in the ListenerAdapter class are partially hand-written but mostly procedurally generated by some scripts not contained in this repo. I have tried to use compile-time annotation processing before, as I think it would be pretty elegant, but I could not get it to work. Using all the fields/getters of the challonge-java models it would be possible to just update the challonge-java library, recompile and have all the new events immediately.\
In the case file-editing scripts are used instead, they should be published within this repo and (in case gradle works like that I have no idea lul) a gradle task to run those should be added.

Smaller TODOs can be found near the elements they affect in "// TODO:" comments.
