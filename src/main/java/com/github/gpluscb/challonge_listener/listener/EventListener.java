package com.github.gpluscb.challonge_listener.listener;

import java.util.List;

import com.github.gpluscb.challonge_listener.events.GenericEvent;

/**
 * Represents a listener that can be managed by and fired to by a
 * {@link com.github.gpluscb.challonge_listener.listener.ListenerManager
 * ListenerManager}.
 */
public interface EventListener {
	/**
	 * Handles a fired event.
	 *
	 * @param event
	 *            The event to be handled
	 */
	void onEvent(GenericEvent event);
	
	/**
	 * The ids of the tournaments this EventListener is subscribed to. Used by
	 * ListenerManager. Unfortunately, no url support is possible here because
	 * there are possible id/url collisions which could give the user events for
	 * an entirely different tournament. Workarounds would require a good amount
	 * of checks, so no implementation of that yet.
	 *
	 * @return All the ids of tournaments the EventListener is subscribed to
	 */
	List<Long> getSubscribedTournamentIds();
}
