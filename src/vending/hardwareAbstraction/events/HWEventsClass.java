package vending.hardwareAbstraction.events;

import javax.swing.event.EventListenerList;


/**
 * @author Stephane S. Some
 *
 */
public abstract class HWEventsClass extends Thread {
	protected EventListenerList 
		  hwEventListeners = new EventListenerList(); 
	 public void addHWEventListener(HWEventsListener listener)  {
		 hwEventListeners.add(HWEventsListener.class,listener);
	 }
	 public void removeHWEventListener(HWEventsListener listener)  {
		 hwEventListeners.remove(HWEventsListener.class, listener);
	 }
	 public void fireHWEvent(HWEvents aEvent)  {
		 Object[] listeners = hwEventListeners.getListenerList();

		 // loop through each listener and pass on the event if needed
		 int numListeners = listeners.length;
		 for (int i = 0; i < numListeners; i++)     {
			  if (listeners[i]==HWEventsListener.class)  {
				((HWEventsListener)listeners[i+1]).hwEvent(aEvent);
			  }            
		 }
	 }
}
