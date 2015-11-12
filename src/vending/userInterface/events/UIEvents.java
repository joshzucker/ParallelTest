package vending.userInterface.events;

import java.util.EventObject;

/**
 * @author Stephane S. Some
 *
 */
public class UIEvents extends EventObject {

    private static final long serialVersionUID = -3319543482939470149L;
    public static final int COIN = 0;
	public static final int SELECTION = 1;
	public static final int RETURNCOINS = 2;
	private int IVALUE;
	private int TYPE;

	/**
	 * Method UIEvent. To be used when there is no value attached to the event.
	 */
	public UIEvents(Object obj, int typ) {
		super(obj);
		this.TYPE = typ;
	}

	/**
	 * Method UIEvent. To be used when a integer value is attached to the event.
	 */
	public UIEvents(Object obj, int typ, int val) {
		super(obj);
		this.TYPE = typ;
		this.IVALUE = val;
	}
	
	public int intValue()  {
		return(this.IVALUE);
	}
	public String toString() {
		switch (TYPE) {
			case COIN: return("COIN");
			case SELECTION: return("SELECTION");
			case RETURNCOINS: return("RETURNCOINS");
			default: return(null);
		}
	}

	public boolean coin() {
		return(this.TYPE == COIN);
	}
	
	public boolean selection() {
		return(this.TYPE == SELECTION);
	}
	
	public boolean returncoins() {
		return(this.TYPE == RETURNCOINS);
	}
}
