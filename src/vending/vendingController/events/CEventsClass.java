package vending.vendingController.events;

import javax.swing.event.EventListenerList;

/**
 * @author Stephane S. Some
 *
 */
public class CEventsClass extends Thread {
	protected EventListenerList 
	 	 cEventListeners = new EventListenerList(); 
 	public void addCEventListener(CEventsListener listener)  {
		 cEventListeners.add(CEventsListener.class,listener);
 	}
 	public void removeCEventListener(CEventsListener listener)  {
		 cEventListeners.remove(CEventsListener.class, listener);
 	}
 	public void fireCEvent(CEvents aEvent)  {
	 	Object[] listeners = cEventListeners.getListenerList();

		 // loop through each listener and pass on the event if needed
		 int numListeners = listeners.length;
	 	for (int i = 0; i < numListeners; i++)     {
		  	if (listeners[i]==CEventsListener.class)  {
				((CEventsListener)listeners[i+1]).cEvent(aEvent);
		  	}            
		 }
 	}
}

