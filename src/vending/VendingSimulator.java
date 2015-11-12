package vending;

import vending.hardwareAbstraction.HWabstraction;
import vending.userInterface.VendingInterface;
import vending.vendingController.VendingController;



/**
 * @author Stephane S. Some
 *
 */
public class VendingSimulator {

	public static void main(String[] args) {
		HWabstraction _hwabst = new HWabstraction();
		VendingInterface _interf = new VendingInterface();
		VendingController _ctrl = new VendingController();
		_interf.addUIEventListener(_ctrl);
		_ctrl.addCEventListener(_interf);
		_ctrl.addCEventListener(_hwabst);
		_hwabst.addHWEventListener(_ctrl);
		_hwabst.start();
		_ctrl.start();
	}
}
