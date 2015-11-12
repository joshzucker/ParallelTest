package vending.vendingController;

import vending.commonU.SelectionObject;
import vending.hardwareAbstraction.events.HWEvents;
import vending.hardwareAbstraction.events.HWEventsListener;
import vending.userInterface.events.UIEvents;
import vending.userInterface.events.UIEventsListener;
import vending.vendingController.events.CEvents;
import vending.vendingController.events.CEventsClass;
import vending.vendingController.states.VendingState;

/**
 * @author Stephane S. Some
 * 
 */
public class VendingController extends CEventsClass implements UIEventsListener, HWEventsListener {
    private VendingState state;
    private int currentAmount;

    public VendingController() {
        currentAmount = 0;
    }

    public void run() {
        // initial state
        setVendingState(VendingState.IDLE);
        try {
            while (!isInterrupted()) {
                sleep(100);
            }
        } catch (InterruptedException e) {
        }

    }

    public void uiEvent(UIEvents ev) {
        // delegate to state
        state.handle(ev, this);
    }

    public void hwEvent(HWEvents ev) {
        // delegate to state
        state.handle(ev, this);
    }

    public void setVendingState(VendingState newState) {
        this.state = newState;
    }

    public void addToAmount(int amt) {
        currentAmount += amt;
    }

    public void resetAmount() {
        currentAmount = 0;
    }

    public void checkSelection(int sel) {
        this.fireCEvent(new CEvents(this, CEvents.SELECTION, new SelectionObject(sel, currentAmount)));
    }

    public void notAvailableItem() {
        this.fireCEvent(new CEvents(this, CEvents.MESSAGE, "Item not available"));
    }

    public void notEnoughMoney() {
        this.fireCEvent(new CEvents(this, CEvents.MESSAGE, "Not enough balance for Item"));
    }

    public void okWithChange(int chg) {
        this.currentAmount = chg; //
        this.fireCEvent(new CEvents(this, CEvents.MESSAGE, "Please retrieve Item"));
        this.fireCEvent(new CEvents(this, CEvents.NEWBALANCE, chg));
    }

    public void okNoChange() {
        this.fireCEvent(new CEvents(this, CEvents.MESSAGE, "Please retrieve Item"));
        this.fireCEvent(new CEvents(this, CEvents.NEWBALANCE, 0));
    }

    public VendingState getVendingState() {
        return state;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }
}
