package vending.userInterface.events;

import javax.swing.JFrame;
import javax.swing.event.EventListenerList;

/**
 * @author Stephane S. Some
 *
 */
public abstract class UIEventsClass extends JFrame {
	protected EventListenerList 
		  uiEventListeners = new EventListenerList(); 
	 public void addUIEventListener(UIEventsListener listener)  {
		 uiEventListeners.add(UIEventsListener.class,listener);
	 }
	 public void removeUIEventListener(UIEventsListener listener)  {
		 uiEventListeners.remove(UIEventsListener.class, listener);
	 }
	 public void fireUIEvent(UIEvents aEvent)  {
		 Object[] listeners = uiEventListeners.getListenerList();

		 // loop through each listener and pass on the event if needed
		 int numListeners = listeners.length;
		 for (int i = 0; i < numListeners; i++)     {
			  if (listeners[i]==UIEventsListener.class)  {
				((UIEventsListener)listeners[i+1]).uiEvent(aEvent);
			  }            
		 }
	 }
}
