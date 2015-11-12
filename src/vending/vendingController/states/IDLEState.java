package vending.vendingController.states;

import vending.hardwareAbstraction.events.HWEvents;
import vending.userInterface.events.UIEvents;
import vending.vendingController.VendingController;

/**
 * @author Stephane S. Some
 *
 */
public class IDLEState implements VendingState {

	public void handle(UIEvents ev, VendingController v) {
		if (ev.coin()) {
			v.setVendingState(VendingState.RECEIVINGCOINS);
			v.addToAmount(ev.intValue());
		} 
		
	}

	public void handle(HWEvents ev, VendingController v)  {
	
	}

}
