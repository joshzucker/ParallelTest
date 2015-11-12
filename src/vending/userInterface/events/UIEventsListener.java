package vending.userInterface.events;

import java.util.EventListener;

/**
 * @author Stephane S. Some
 *
 */
public interface UIEventsListener extends  EventListener {
	public void uiEvent(UIEvents e);
}
