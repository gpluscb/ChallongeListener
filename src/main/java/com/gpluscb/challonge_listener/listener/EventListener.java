package com.gpluscb.challonge_listener.listener;

import com.gpluscb.challonge_listener.events.GenericEvent;

/**
 * Represents a listener that can be managed by and fired to by a
 * {@link com.gpluscb.challonge_listener.listener.ListenerManager
 * ListenerManager}.
 */
public interface EventListener {
	/**
	 * Handles a fired event.
	 * 
	 * @param event
	 *            The event to be handled
	 */
	public void onEvent(GenericEvent event);
}
