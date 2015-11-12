package vending.vendingController.states;

import vending.hardwareAbstraction.events.HWEvents;
import vending.userInterface.events.UIEvents;
import vending.vendingController.VendingController;

/**
 * @author Stephane S. Some
 *
 */
public class ReceivingCoinsState implements VendingState {

	public void handle(UIEvents ev, VendingController v)  {
		if (ev.coin()) {
			v.addToAmount(ev.intValue());
		} 
		else if (ev.selection()) {
			v.setVendingState(VendingState.CHECKINGSELECTION);
			v.checkSelection(ev.intValue());
		}
		else if (ev.returncoins()) {
			v.setVendingState(VendingState.IDLE);
			v.resetAmount();
		} 
	}

	public void handle(HWEvents ev, VendingController v)  {
		if (ev.notAvailable()) {
			v.setVendingState(VendingState.IDLE);
		}
	}
}
