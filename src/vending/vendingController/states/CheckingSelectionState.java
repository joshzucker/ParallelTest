package vending.vendingController.states;

import vending.hardwareAbstraction.events.HWEvents;
import vending.userInterface.events.UIEvents;
import vending.vendingController.VendingController;

/**
 * @author Stephane S. Some
 *
 */
public class CheckingSelectionState implements VendingState {

	public void handle(UIEvents ev, VendingController v) {
	
	}

	public void handle(HWEvents ev, VendingController v)  {
		if (ev.notAvailable()) {
			v.setVendingState(VendingState.RECEIVINGCOINS);
			v.notAvailableItem();	
		}
		else if (ev.notEnoughMoney()) {
			v.setVendingState(VendingState.RECEIVINGCOINS);
			v.notEnoughMoney();
		}
		else if (ev.selectionOkChange()) {
			v.setVendingState(VendingState.RECEIVINGCOINS);
			v.okWithChange(ev.intValue());
		}
		else if (ev.selectionOkNChange()) {
			v.setVendingState(VendingState.IDLE);
			v.okNoChange();
		}
	}

}
