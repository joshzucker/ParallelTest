package vending.hardwareAbstraction;

import vending.commonU.SelectionObject;
import vending.hardwareAbstraction.events.HWEvents;
import vending.hardwareAbstraction.events.HWEventsClass;
import vending.vendingController.events.CEvents;
import vending.vendingController.events.CEventsListener;

/**
 * @author Stephane S. Some
 * 
 */
public class HWabstraction extends HWEventsClass implements CEventsListener {
    private int NBITEMS = 5;
    private VendingItem[] itemset; // the set of items in vending machine

    public void run() {
        itemset = new VendingItem[NBITEMS];
        // create set of items
        // 0 - Coke
        itemset[0] = new VendingItem("Coke", 100, 10);
        // 1 - Mars
        itemset[1] = new VendingItem("Mars", 105, 12);
        // 2 - Hershey
        itemset[2] = new VendingItem("Hershey", 105, 0);
        // 3 - Granola
        itemset[3] = new VendingItem("Granola", 150, 10);
        // 4 - Pepsi
        itemset[4] = new VendingItem("Pepsi", 125, 10);

        try {
            while (!isInterrupted()) {
                sleep(100);
            }
        } catch (InterruptedException e) {
        }

    }

    public void cEvent(CEvents ev) {
        if (ev.selection()) {
            SelectionObject sel = ev.selectionValue();
            if (sel.getSelectNum() < itemset.length) {
                VendingItem item = itemset[sel.getSelectNum()];
                // check price
                if (item.getPrice() > sel.getAmountEntered()) {
                    this.fireHWEvent(new HWEvents(this, HWEvents.NOTENOUGHMONEY));
                    return;
                }
                // check quantity
                if (item.getQuantity() == 0) {
                    this.fireHWEvent(new HWEvents(this, HWEvents.NOTAVAILABLE));
                    return;
                }
                int change = sel.getAmountEntered() - item.getPrice();
                item.removeOne();
                if (change > 0) {
                    this.fireHWEvent(new HWEvents(this, HWEvents.SELECTIONOKCHANGE, change));
                } else {
                    this.fireHWEvent(new HWEvents(this, HWEvents.SELECTIONOKNCHANGE));
                }
            }
        }
    }

}
