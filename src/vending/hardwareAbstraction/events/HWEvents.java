package vending.hardwareAbstraction.events;

import java.util.EventObject;

/**
 * @author Stephane S. Some
 *
 */
public class HWEvents extends EventObject {

    private static final long serialVersionUID = -3927544542347214657L;
    public static final int NOTAVAILABLE = 0;
	public static final int NOTENOUGHMONEY = 1;
	public static final int SELECTIONOKCHANGE = 2;
	public static final int SELECTIONOKNCHANGE = 3;
	private int IVALUE;
	private int TYPE;

	public HWEvents(Object obj, int typ) {
		super(obj);
		this.TYPE = typ;
	}

	public HWEvents(Object obj, int typ, int val) {
		super(obj);
		this.TYPE = typ;
		this.IVALUE = val;
	}
	
	public int intValue()  {
		return(this.IVALUE);
	}
	public String toString() {
		switch (TYPE) {
			case NOTAVAILABLE: return("NOTAVAILABLE");
			case NOTENOUGHMONEY: return("NOTENOUGHMONEY");
			case SELECTIONOKCHANGE: return("SELECTIONOKCHANGE");
			case SELECTIONOKNCHANGE: return("SELECTIONOKNCHANGE");
			default: return(null);
		}
	}

	public boolean notAvailable() {
		return(this.TYPE == NOTAVAILABLE);
	}

	public boolean notEnoughMoney() {
		return(this.TYPE == NOTENOUGHMONEY);
	}

	public boolean selectionOkChange() {
		return(this.TYPE == SELECTIONOKCHANGE);
	}
	
	public boolean selectionOkNChange() {
		return(this.TYPE == SELECTIONOKNCHANGE);
	}
}

