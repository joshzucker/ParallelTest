package vending.vendingController.states;

import vending.hardwareAbstraction.events.HWEvents;
import vending.userInterface.events.UIEvents;
import vending.vendingController.VendingController;

/**
 * @author Stephane S. Some
 *
 */
public interface VendingState {
	// all the possible states of Vending Machine
	static public final VendingState IDLE = new IDLEState();
	static public final VendingState RECEIVINGCOINS = new ReceivingCoinsState();
	static public final VendingState CHECKINGSELECTION = new CheckingSelectionState();
	public void handle(UIEvents ev, VendingController v) ;
	public void handle(HWEvents ev, VendingController v) ;
}
