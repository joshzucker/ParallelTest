package vending.vendingController.events;

import java.util.EventListener;

/**
 * @author Stephane S. Some
 *
 */
public interface CEventsListener  extends  EventListener {
	public void cEvent(CEvents e);
}
