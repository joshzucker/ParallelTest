package vending.hardwareAbstraction.events;

import java.util.EventListener;

/**
 * @author Stephane S. Some
 *
 */
public interface HWEventsListener extends  EventListener {
	public void hwEvent(HWEvents e); 
}
