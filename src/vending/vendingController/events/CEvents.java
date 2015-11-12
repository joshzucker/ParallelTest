package vending.vendingController.events;

import java.util.EventObject;

import vending.commonU.SelectionObject;


/**
 * @author Stephane S. Some
 *
 */
public class CEvents  extends EventObject {

    private static final long serialVersionUID = 6147875569147295021L;
    public static final int SELECTION = 0;
	public static final int MESSAGE = 1;
	public static final int NEWBALANCE = 2;
	private int IVALUE;
	private String SVALUE;
	private SelectionObject SOBJVALUE;
	private int TYPE;

	public CEvents(Object obj, int typ) {
		super(obj);
		this.TYPE = typ;
	}

	public CEvents(Object obj, int typ, int val) {
		super(obj);
		this.TYPE = typ;
		this.IVALUE = val;
	}
	
	public CEvents(Object obj, int typ, String val) {
		super(obj);
		this.TYPE = typ;
		this.SVALUE = val;
	}

	public CEvents(Object obj, int typ, SelectionObject val) {
		super(obj);
		this.TYPE = typ;
		this.SOBJVALUE = val;
	}
		
	public int intValue()  {
		return(this.IVALUE);
	}

	public String stringValue()  {
		return(this.SVALUE);
	}	

	public SelectionObject selectionValue()  {
		return(this.SOBJVALUE);
	}	
	
	public String toString() {
		switch (TYPE) {
			case SELECTION: return("SELECTION");
			case MESSAGE: return("MESSAGE");
			case NEWBALANCE: return("NEWBALANCE");
			default: return(null);
		}
	}

	public boolean message() {
		return(this.TYPE == MESSAGE);
	}

	public boolean selection() {
		return(this.TYPE == SELECTION);
	}
	
	public boolean newbalance() {
		return(this.TYPE == NEWBALANCE);
	}
}
