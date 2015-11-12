package vending.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import vending.hardwareAbstraction.HWabstraction;
import vending.hardwareAbstraction.events.HWEvents;
import vending.hardwareAbstraction.events.HWEventsListener;
import vending.userInterface.VendingInterface;
import vending.userInterface.events.UIEvents;
import vending.userInterface.events.UIEventsListener;
import vending.vendingController.VendingController;
import vending.vendingController.events.CEvents;
import vending.vendingController.events.CEventsListener;
import vending.vendingController.states.VendingState;

/**
 * Sample test fixture for the vending machine problem.
 * 
 * Codebase might have to be massaged in order to allow further testing.
 * 
 * @author jkealey
 * 
 */
public class VendingTests implements UIEventsListener, HWEventsListener, CEventsListener {
    protected VendingController _ctrl;
    protected HWabstraction _hwabst;
    protected VendingInterface _interf;

    protected ArrayList hwEvents, uiEvents, cEvents;

    public void cEvent(CEvents e) {
        cEvents.add(e);
        _hwabst.cEvent(e);
        _interf.cEvent(e);
    }

    public void clearEventLog() {
        hwEvents.clear();
        uiEvents.clear();
        cEvents.clear();
    }

    public void hwEvent(HWEvents e) {
        hwEvents.add(e);
        _ctrl.hwEvent(e);
    }

    public void insert10c() {
        (_interf.getMyButtons()[1]).doClick();
    }

    public void insert1dollar() {
        (_interf.getMyButtons()[3]).doClick();
    }

    public void insert25c() {
        (_interf.getMyButtons()[2]).doClick();
    }

    public void insert5c() {
        (_interf.getMyButtons()[0]).doClick();
    }

    public void requestChange() {
        (_interf.getMyButtons()[4]).doClick();
    }

    public void requestCoke() {
        (_interf.getMyButtons()[5]).doClick();
    }

    public void requestGranola() {
        (_interf.getMyButtons()[8]).doClick();
    }

    public void requestHersey() {
        (_interf.getMyButtons()[7]).doClick();
    }

    public void requestMars() {
        (_interf.getMyButtons()[6]).doClick();
    }

    public void requestPepsi() {
        (_interf.getMyButtons()[9]).doClick();
    }
    
	private void notEnoughMoney() {
		_hwabst.fireHWEvent(new HWEvents(_hwabst, HWEvents.NOTENOUGHMONEY));;
	}
	
	private void notAvailable() {
		_hwabst.fireHWEvent(new HWEvents(_hwabst, HWEvents.NOTAVAILABLE));;
	}

	private void selectionOKChange() {
		_hwabst.fireHWEvent(new HWEvents(_hwabst, HWEvents.SELECTIONOKCHANGE));;
	}
	
	private void selectionOKNoChange() {
		_hwabst.fireHWEvent(new HWEvents(_hwabst, HWEvents.SELECTIONOKNCHANGE));;
	}
	
	@Before
    public void setUp() throws Exception {
        
        _hwabst = new HWabstraction();
        _interf = new VendingInterface();
        _ctrl = new VendingController();
        _interf.addUIEventListener(this);
        _ctrl.addCEventListener(this);
        _ctrl.addCEventListener(this);
        _hwabst.addHWEventListener(this);
        _hwabst.start();
        _ctrl.start();

        hwEvents = new ArrayList();
        uiEvents = new ArrayList();
        cEvents = new ArrayList();

        pause();

    }

    /**
     * Pause to allow other Threads to work.
     */
    private void pause() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testConformance_1() {
        // check state
        assertEquals("Invalid initial state", VendingState.IDLE, _ctrl.getVendingState());
        // check initial amount
        assertEquals("Invalid initial amount in controller", 0, _ctrl.getCurrentAmount());
        assertEquals("Invalid initial amount in UI", 0, _interf.currBalance());

        clearEventLog();

        insert1dollar();
        pause();

        // check state change
        assertEquals("Invalid state", VendingState.RECEIVINGCOINS, _ctrl.getVendingState());

        // check if addToAmount worked
        assertEquals("Invalid amount in controller", 100, _ctrl.getCurrentAmount());
        assertEquals("Invalid amount in UI", 100, _interf.currBalance());

        // check to see if the expected event passed by
        assertEquals("Invalid UI event size", 1, uiEvents.size());
        assertTrue(((UIEvents) uiEvents.get(0)).coin());
        assertFalse(((UIEvents) uiEvents.get(0)).returncoins());
        assertFalse(((UIEvents) uiEvents.get(0)).selection());
        assertEquals("Invalid controller event size", 0, cEvents.size());
        assertEquals("Invalid hardware event size", 0, hwEvents.size());

        clearEventLog();

        insert25c();
        pause();

        // check state change
        assertEquals("Invalid state", VendingState.RECEIVINGCOINS, _ctrl.getVendingState());

        // check if addToAmount worked
        assertEquals("Invalid amount in controller", 125, _ctrl.getCurrentAmount());
        assertEquals("Invalid amount in UI", 125, _interf.currBalance());

        // check to see if the expected event passed by
        assertEquals("Invalid UI event size", 1, uiEvents.size());
        assertTrue(((UIEvents) uiEvents.get(0)).coin());
        assertFalse(((UIEvents) uiEvents.get(0)).returncoins());
        assertFalse(((UIEvents) uiEvents.get(0)).selection());
        assertEquals("Invalid controller event size", 0, cEvents.size());
        assertEquals("Invalid hardware event size", 0, hwEvents.size());
    }
    
    @Test
    public void testConformance_2() {
        // check state
        assertEquals("Invalid initial state", VendingState.IDLE, _ctrl.getVendingState());
        // check initial amount
        assertEquals("Invalid initial amount in controller", 0, _ctrl.getCurrentAmount());
        assertEquals("Invalid initial amount in UI", 0, _interf.currBalance());

        clearEventLog();

        insert1dollar();
        pause();

        // check state change
        assertEquals("Invalid state", VendingState.RECEIVINGCOINS, _ctrl.getVendingState());

        // check if addToAmount worked
        assertEquals("Invalid amount in controller", 100, _ctrl.getCurrentAmount());
        assertEquals("Invalid amount in UI", 100, _interf.currBalance());

        // check to see if the expected event passed by
        assertEquals("Invalid UI event size", 1, uiEvents.size());
        assertTrue(((UIEvents) uiEvents.get(0)).coin());
        assertFalse(((UIEvents) uiEvents.get(0)).returncoins());
        assertFalse(((UIEvents) uiEvents.get(0)).selection());
        assertEquals("Invalid controller event size", 0, cEvents.size());
        assertEquals("Invalid hardware event size", 0, hwEvents.size());

        clearEventLog();

        insert25c();
        pause();

        // check state change
        assertEquals("Invalid state", VendingState.RECEIVINGCOINS, _ctrl.getVendingState());

        // check if addToAmount worked
        assertEquals("Invalid amount in controller", 125, _ctrl.getCurrentAmount());
        assertEquals("Invalid amount in UI", 125, _interf.currBalance());

        // check to see if the expected event passed by
        assertEquals("Invalid UI event size", 1, uiEvents.size());
        assertTrue(((UIEvents) uiEvents.get(0)).coin());
        assertFalse(((UIEvents) uiEvents.get(0)).returncoins());
        assertFalse(((UIEvents) uiEvents.get(0)).selection());
        assertEquals("Invalid controller event size", 0, cEvents.size());
        assertEquals("Invalid hardware event size", 0, hwEvents.size());
        
        clearEventLog();
        
        requestCoke();
        pause();
        
        // check state change
        assertEquals("Invalid state", VendingState.RECEIVINGCOINS, _ctrl.getVendingState());

        // check if request worked
        assertEquals("Invalid amount in controller", 25, _ctrl.getCurrentAmount());
        assertEquals("Invalid amount in UI", 25, _interf.currBalance());

        // check to see if the expected event passed by
        assertEquals("Invalid UI event size", 1, uiEvents.size());
        assertFalse(((UIEvents) uiEvents.get(0)).coin());
        assertFalse(((UIEvents) uiEvents.get(0)).returncoins());
        assertTrue(((UIEvents) uiEvents.get(0)).selection());
        assertEquals("Invalid controller event size", 6, cEvents.size());
        assertTrue(((CEvents) cEvents.get(0)).selection());
        assertFalse(((CEvents) cEvents.get(0)).message());
        assertFalse(((CEvents) cEvents.get(0)).newbalance());
        // this event is for displaying the "Please retrieve item" pop-up window
        assertFalse(((CEvents) cEvents.get(1)).selection());
        assertTrue(((CEvents) cEvents.get(1)).message());
        assertFalse(((CEvents) cEvents.get(1)).newbalance());
        assertFalse(((CEvents) cEvents.get(2)).selection());
        assertTrue(((CEvents) cEvents.get(2)).message());
        assertFalse(((CEvents) cEvents.get(2)).newbalance());
        // this event is for updating the current amount in the UI
        assertFalse(((CEvents) cEvents.get(3)).selection());
        assertFalse(((CEvents) cEvents.get(3)).message());
        assertTrue(((CEvents) cEvents.get(3)).newbalance());
        assertFalse(((CEvents) cEvents.get(4)).selection());
        assertFalse(((CEvents) cEvents.get(4)).message());
        assertTrue(((CEvents) cEvents.get(4)).newbalance());
        assertTrue(((CEvents) cEvents.get(5)).selection());
        assertFalse(((CEvents) cEvents.get(5)).message());
        assertFalse(((CEvents) cEvents.get(5)).newbalance());
        assertEquals("Invalid hardware event size", 2, hwEvents.size());
        assertFalse(((HWEvents) hwEvents.get(0)).notAvailable());
        assertFalse(((HWEvents) hwEvents.get(0)).notEnoughMoney());
        assertTrue(((HWEvents) hwEvents.get(0)).selectionOkChange());
        assertFalse(((HWEvents) hwEvents.get(0)).selectionOkNChange());
        assertFalse(((HWEvents) hwEvents.get(1)).notAvailable());
        assertFalse(((HWEvents) hwEvents.get(1)).notEnoughMoney());
        assertTrue(((HWEvents) hwEvents.get(1)).selectionOkChange());
        assertFalse(((HWEvents) hwEvents.get(1)).selectionOkNChange());
    }

    @Test
    public void testConformance_3() {
    	// for this test case, the setup of vending items in HWAbstraction.java had to be changed.
    	// the number of Hershey items was set to 0
    	
        // check state
        assertEquals("Invalid initial state", VendingState.IDLE, _ctrl.getVendingState());
        // check initial amount
        assertEquals("Invalid initial amount in controller", 0, _ctrl.getCurrentAmount());
        assertEquals("Invalid initial amount in UI", 0, _interf.currBalance());

        clearEventLog();

        insert1dollar();
        pause();

        // check state change
        assertEquals("Invalid state", VendingState.RECEIVINGCOINS, _ctrl.getVendingState());

        // check if addToAmount worked
        assertEquals("Invalid amount in controller", 100, _ctrl.getCurrentAmount());
        assertEquals("Invalid amount in UI", 100, _interf.currBalance());

        // check to see if the expected event passed by
//        assertTrue(((UIEvents) uiEvents.get(0)).coin());
        assertFalse(((UIEvents) uiEvents.get(0)).returncoins());
        assertFalse(((UIEvents) uiEvents.get(0)).selection());
        assertEquals("Invalid controller event size", 0, cEvents.size());
        assertEquals("Invalid hardware event size", 0, hwEvents.size());
        
        clearEventLog();

        insert25c();
        pause();

        // check state change
        assertEquals("Invalid state", VendingState.RECEIVINGCOINS, _ctrl.getVendingState());

        // check if addToAmount worked
        assertEquals("Invalid amount in controller", 125, _ctrl.getCurrentAmount());
        assertEquals("Invalid amount in UI", 125, _interf.currBalance());

        // check to see if the expected event passed by
        assertEquals("Invalid UI event size", 1, uiEvents.size());
        assertTrue(((UIEvents) uiEvents.get(0)).coin());
        assertFalse(((UIEvents) uiEvents.get(0)).returncoins());
        assertFalse(((UIEvents) uiEvents.get(0)).selection());
        assertEquals("Invalid controller event size", 0, cEvents.size());
        assertEquals("Invalid hardware event size", 0, hwEvents.size());
        
        clearEventLog();

        requestHersey();
        pause();

       // // check state change
       // assertEquals("Invalid state", VendingState.RECEIVINGCOINS, _ctrl.getVendingState());

        // check if addToAmount worked
        assertEquals("Invalid amount in controller", 125, _ctrl.getCurrentAmount());
        assertEquals("Invalid amount in UI", 125, _interf.currBalance());

        // check to see if the expected event passed by
        assertEquals("Invalid UI event size", 1, uiEvents.size());
        assertFalse(((UIEvents) uiEvents.get(0)).coin());
        assertFalse(((UIEvents) uiEvents.get(0)).returncoins());
        assertTrue(((UIEvents) uiEvents.get(0)).selection());
        assertEquals("Invalid controller event size", 4, cEvents.size());
        assertTrue(((CEvents) cEvents.get(0)).selection());
        assertFalse(((CEvents) cEvents.get(0)).message());
        assertFalse(((CEvents) cEvents.get(0)).newbalance());
        assertFalse(((CEvents) cEvents.get(1)).selection());
        assertTrue(((CEvents) cEvents.get(1)).message());
        assertFalse(((CEvents) cEvents.get(1)).newbalance());
        assertFalse(((CEvents) cEvents.get(2)).selection());
        assertTrue(((CEvents) cEvents.get(2)).message());
        assertFalse(((CEvents) cEvents.get(2)).newbalance());
        assertTrue(((CEvents) cEvents.get(3)).selection());
        assertFalse(((CEvents) cEvents.get(3)).message());
        assertFalse(((CEvents) cEvents.get(3)).newbalance());
        assertEquals("Invalid hardware event size", 2, hwEvents.size());
        assertTrue(((HWEvents) hwEvents.get(0)).notAvailable());
        assertFalse(((HWEvents) hwEvents.get(0)).notEnoughMoney());
        assertFalse(((HWEvents) hwEvents.get(0)).selectionOkChange());
        assertFalse(((HWEvents) hwEvents.get(0)).selectionOkNChange());
        assertTrue(((HWEvents) hwEvents.get(1)).notAvailable());
        assertFalse(((HWEvents) hwEvents.get(1)).notEnoughMoney());
        assertFalse(((HWEvents) hwEvents.get(1)).selectionOkChange());
        assertFalse(((HWEvents) hwEvents.get(1)).selectionOkNChange());
    }

    @Test
    public void testConformance_4() {
    	// for this test case, the setup of vending items in HWAbstraction.java had to be changed.
    	// the price of Pepsi was set to 1.25 (so that it matches the UI) 
    	
        // check state
        assertEquals("Invalid initial state", VendingState.IDLE, _ctrl.getVendingState());
        // check initial amount
        assertEquals("Invalid initial amount in controller", 0, _ctrl.getCurrentAmount());
        assertEquals("Invalid initial amount in UI", 0, _interf.currBalance());

        clearEventLog();

        insert1dollar();
        pause();

        // check state change
        assertEquals("Invalid state", VendingState.RECEIVINGCOINS, _ctrl.getVendingState());

        // check if addToAmount worked
        assertEquals("Invalid amount in controller", 100, _ctrl.getCurrentAmount());
        assertEquals("Invalid amount in UI", 100, _interf.currBalance());

        // check to see if the expected event passed by
        assertEquals("Invalid UI event size", 1, uiEvents.size());
        assertTrue(((UIEvents) uiEvents.get(0)).coin());
        assertFalse(((UIEvents) uiEvents.get(0)).returncoins());
        assertFalse(((UIEvents) uiEvents.get(0)).selection());
        assertEquals("Invalid controller event size", 0, cEvents.size());
        assertEquals("Invalid hardware event size", 0, hwEvents.size());

        clearEventLog();
        
        requestPepsi();
        pause();
        
        // check state change
        assertEquals("Invalid state", VendingState.RECEIVINGCOINS, _ctrl.getVendingState());

        // check if request worked
        assertEquals("Invalid amount in controller", 100, _ctrl.getCurrentAmount());
        assertEquals("Invalid amount in UI", 100, _interf.currBalance());

        // check to see if the expected event passed by
        assertEquals("Invalid UI event size", 1, uiEvents.size());
        assertFalse(((UIEvents) uiEvents.get(0)).coin());
        assertFalse(((UIEvents) uiEvents.get(0)).returncoins());
        assertTrue(((UIEvents) uiEvents.get(0)).selection());
        assertEquals("Invalid controller event size", 4, cEvents.size());
        assertTrue(((CEvents) cEvents.get(0)).selection());
        assertFalse(((CEvents) cEvents.get(0)).message());
        assertFalse(((CEvents) cEvents.get(0)).newbalance());
        assertFalse(((CEvents) cEvents.get(1)).selection());
        assertTrue(((CEvents) cEvents.get(1)).message());
        assertFalse(((CEvents) cEvents.get(1)).newbalance());
        assertFalse(((CEvents) cEvents.get(2)).selection());
        assertTrue(((CEvents) cEvents.get(2)).message());
        assertFalse(((CEvents) cEvents.get(2)).newbalance());
        assertTrue(((CEvents) cEvents.get(3)).selection());
        assertFalse(((CEvents) cEvents.get(3)).message());
        assertFalse(((CEvents) cEvents.get(3)).newbalance());
        assertEquals("Invalid hardware event size", 2, hwEvents.size());
        assertFalse(((HWEvents) hwEvents.get(0)).notAvailable());
        assertTrue(((HWEvents) hwEvents.get(0)).notEnoughMoney());
        assertFalse(((HWEvents) hwEvents.get(0)).selectionOkChange());
        assertFalse(((HWEvents) hwEvents.get(0)).selectionOkNChange());
        assertFalse(((HWEvents) hwEvents.get(0)).notAvailable());
        assertTrue(((HWEvents) hwEvents.get(0)).notEnoughMoney());
        assertFalse(((HWEvents) hwEvents.get(0)).selectionOkChange());
        assertFalse(((HWEvents) hwEvents.get(0)).selectionOkNChange());
    }

    @Test
    public void testConformance_5() {
    	// for this test case, the setup of vending items in HWAbstraction.java had to be changed.
    	// the price of Pepsi was set to 1.25 (so that it matches the UI) 
    	
        // check state
        assertEquals("Invalid initial state", VendingState.IDLE, _ctrl.getVendingState());
        // check initial amount
        assertEquals("Invalid initial amount in controller", 0, _ctrl.getCurrentAmount());
        assertEquals("Invalid initial amount in UI", 0, _interf.currBalance());

        clearEventLog();

        insert1dollar();
        pause();

        // check state change
        assertEquals("Invalid state", VendingState.RECEIVINGCOINS, _ctrl.getVendingState());

        // check if addToAmount worked
        assertEquals("Invalid amount in controller", 100, _ctrl.getCurrentAmount());
        assertEquals("Invalid amount in UI", 100, _interf.currBalance());

        // check to see if the expected event passed by
        assertEquals("Invalid UI event size", 1, uiEvents.size());
        assertTrue(((UIEvents) uiEvents.get(0)).coin());
        assertFalse(((UIEvents) uiEvents.get(0)).returncoins());
        assertFalse(((UIEvents) uiEvents.get(0)).selection());
        assertEquals("Invalid controller event size", 0, cEvents.size());
        assertEquals("Invalid hardware event size", 0, hwEvents.size());
        
        clearEventLog();

        insert25c();
        pause();

        // check state change
        assertEquals("Invalid state", VendingState.RECEIVINGCOINS, _ctrl.getVendingState());

        // check if addToAmount worked
        assertEquals("Invalid amount in controller", 125, _ctrl.getCurrentAmount());
        assertEquals("Invalid amount in UI", 125, _interf.currBalance());

        // check to see if the expected event passed by
        assertEquals("Invalid UI event size", 1, uiEvents.size());
        assertTrue(((UIEvents) uiEvents.get(0)).coin());
        assertFalse(((UIEvents) uiEvents.get(0)).returncoins());
        assertFalse(((UIEvents) uiEvents.get(0)).selection());
        assertEquals("Invalid controller event size", 0, cEvents.size());
        assertEquals("Invalid hardware event size", 0, hwEvents.size());
        
        clearEventLog();

        requestPepsi();
        pause();

        // check state change
        assertEquals("Invalid state", VendingState.IDLE, _ctrl.getVendingState());

        // check if addToAmount worked
 //        assertEquals("Invalid amount in UI", 0, _interf.currBalance());

        // check to see if the expected event passed by
        assertEquals("Invalid UI event size", 1, uiEvents.size());
        assertFalse(((UIEvents) uiEvents.get(0)).coin());
        assertFalse(((UIEvents) uiEvents.get(0)).returncoins());
        assertTrue(((UIEvents) uiEvents.get(0)).selection());
        assertEquals("Invalid controller event size", 6, cEvents.size());
        assertTrue(((CEvents) cEvents.get(0)).selection());
        assertFalse(((CEvents) cEvents.get(0)).message());
        assertFalse(((CEvents) cEvents.get(0)).newbalance());
        assertFalse(((CEvents) cEvents.get(1)).selection());
        assertTrue(((CEvents) cEvents.get(1)).message());
        assertFalse(((CEvents) cEvents.get(1)).newbalance());
        assertFalse(((CEvents) cEvents.get(2)).selection());
        assertTrue(((CEvents) cEvents.get(2)).message());
        assertFalse(((CEvents) cEvents.get(2)).newbalance());
        assertFalse(((CEvents) cEvents.get(3)).selection());
        assertFalse(((CEvents) cEvents.get(3)).message());
        assertTrue(((CEvents) cEvents.get(3)).newbalance());
        assertFalse(((CEvents) cEvents.get(4)).selection());
        assertFalse(((CEvents) cEvents.get(4)).message());
        assertTrue(((CEvents) cEvents.get(4)).newbalance());
        assertTrue(((CEvents) cEvents.get(5)).selection());
        assertFalse(((CEvents) cEvents.get(5)).message());
        assertFalse(((CEvents) cEvents.get(5)).newbalance());
        assertEquals("Invalid hardware event size", 2, hwEvents.size());
        assertFalse(((HWEvents) hwEvents.get(0)).notAvailable());
        assertFalse(((HWEvents) hwEvents.get(0)).notEnoughMoney());
        assertFalse(((HWEvents) hwEvents.get(0)).selectionOkChange());
        assertTrue(((HWEvents) hwEvents.get(0)).selectionOkNChange());
        assertFalse(((HWEvents) hwEvents.get(1)).notAvailable());
        assertFalse(((HWEvents) hwEvents.get(1)).notEnoughMoney());
        assertFalse(((HWEvents) hwEvents.get(1)).selectionOkChange());
        assertTrue(((HWEvents) hwEvents.get(1)).selectionOkNChange());
    }

    @Test
    public void testConformance_6() {
        // check state
        assertEquals("Invalid initial state", VendingState.IDLE, _ctrl.getVendingState());
        // check initial amount
        assertEquals("Invalid initial amount in controller", 0, _ctrl.getCurrentAmount());
        assertEquals("Invalid initial amount in UI", 0, _interf.currBalance());

        clearEventLog();

        insert1dollar();
        pause();

        // check state change
        assertEquals("Invalid state", VendingState.RECEIVINGCOINS, _ctrl.getVendingState());

        // check if addToAmount worked
        assertEquals("Invalid amount in controller", 100, _ctrl.getCurrentAmount());
        assertEquals("Invalid amount in UI", 100, _interf.currBalance());

        // check to see if the expected event passed by
        assertEquals("Invalid UI event size", 1, uiEvents.size());
        assertTrue(((UIEvents) uiEvents.get(0)).coin());
        assertFalse(((UIEvents) uiEvents.get(0)).returncoins());
        assertFalse(((UIEvents) uiEvents.get(0)).selection());
        assertEquals("Invalid controller event size", 0, cEvents.size());
        assertEquals("Invalid hardware event size", 0, hwEvents.size());
        
        clearEventLog();

        requestChange();
        pause();

        // check state change
        assertEquals("Invalid state", VendingState.IDLE, _ctrl.getVendingState());

        // check if addToAmount worked
        assertEquals("Invalid amount in controller", 0, _ctrl.getCurrentAmount());
        assertEquals("Invalid amount in UI", 0, _interf.currBalance());

        // check to see if the expected event passed by
        assertEquals("Invalid UI event size", 1, uiEvents.size());
        assertFalse(((UIEvents) uiEvents.get(0)).coin());
        assertTrue(((UIEvents) uiEvents.get(0)).returncoins());
        assertFalse(((UIEvents) uiEvents.get(0)).selection());
        assertEquals("Invalid controller event size", 0, cEvents.size());
        assertEquals("Invalid hardware event size", 0, hwEvents.size());
    }

    @Test
    public void testSneakPath_7() {
        // check state
        assertEquals("Invalid initial state", VendingState.IDLE, _ctrl.getVendingState());
        // check initial amount
        assertEquals("Invalid initial amount in controller", 0, _ctrl.getCurrentAmount());
        assertEquals("Invalid initial amount in UI", 0, _interf.currBalance());

        clearEventLog();

        requestCoke();
        pause();

        // check state change
        assertEquals("Invalid state", VendingState.IDLE, _ctrl.getVendingState());

        // check if addToAmount worked
        assertEquals("Invalid amount in controller", 0, _ctrl.getCurrentAmount());
        assertEquals("Invalid amount in UI", 0, _interf.currBalance());

        // check to see if the expected event passed by
        assertEquals("Invalid UI event size", 1, uiEvents.size());
        assertFalse(((UIEvents) uiEvents.get(0)).coin());
        assertFalse(((UIEvents) uiEvents.get(0)).returncoins());
        assertTrue(((UIEvents) uiEvents.get(0)).selection());
        assertEquals("Invalid controller event size", 0, cEvents.size());
        assertEquals("Invalid hardware event size", 0, hwEvents.size());
    }
    
    @Test
    public void testSneakPath_8() {
        // check state
        assertEquals("Invalid initial state", VendingState.IDLE, _ctrl.getVendingState());
        // check initial amount
        assertEquals("Invalid initial amount in controller", 0, _ctrl.getCurrentAmount());
        assertEquals("Invalid initial amount in UI", 0, _interf.currBalance());

        clearEventLog();

        requestChange();
        pause();

        // check state change
        assertEquals("Invalid state", VendingState.IDLE, _ctrl.getVendingState());

        // check if addToAmount worked
        assertEquals("Invalid amount in controller", 0, _ctrl.getCurrentAmount());
        assertEquals("Invalid amount in UI", 0, _interf.currBalance());

        // check to see if the expected event passed by
        assertEquals("Invalid UI event size", 1, uiEvents.size());
        assertFalse(((UIEvents) uiEvents.get(0)).coin());
        assertTrue(((UIEvents) uiEvents.get(0)).returncoins());
        assertFalse(((UIEvents) uiEvents.get(0)).selection());
        assertEquals("Invalid controller event size", 0, cEvents.size());
        assertEquals("Invalid hardware event size", 0, hwEvents.size());
    }

    @Test
    public void testSneakPath_9() {
        // check state
        assertEquals("Invalid initial state", VendingState.IDLE, _ctrl.getVendingState());
        // check initial amount
        assertEquals("Invalid initial amount in controller", 0, _ctrl.getCurrentAmount());
        assertEquals("Invalid initial amount in UI", 0, _interf.currBalance());

        clearEventLog();

        notEnoughMoney();
        pause();

        // check state change
        assertEquals("Invalid state", VendingState.IDLE, _ctrl.getVendingState());

        // check if addToAmount worked
        assertEquals("Invalid amount in controller", 0, _ctrl.getCurrentAmount());
        assertEquals("Invalid amount in UI", 0, _interf.currBalance());

        // check to see if the expected event passed by
        assertEquals("Invalid UI event size", 0, uiEvents.size());
        assertEquals("Invalid controller event size", 0, cEvents.size());
        assertEquals("Invalid hardware event size", 1, hwEvents.size());
        assertFalse(((HWEvents) hwEvents.get(0)).notAvailable());
        assertTrue(((HWEvents) hwEvents.get(0)).notEnoughMoney());
        assertFalse(((HWEvents) hwEvents.get(0)).selectionOkChange());
        assertFalse(((HWEvents) hwEvents.get(0)).selectionOkNChange());
    }

    @Test
    public void testSneakPath_10() {
        // check state
        assertEquals("Invalid initial state", VendingState.IDLE, _ctrl.getVendingState());
        // check initial amount
        assertEquals("Invalid initial amount in controller", 0, _ctrl.getCurrentAmount());
        assertEquals("Invalid initial amount in UI", 0, _interf.currBalance());

        clearEventLog();

        notAvailable();
        pause();

        // check state change
        assertEquals("Invalid state", VendingState.IDLE, _ctrl.getVendingState());

        // check if addToAmount worked
        assertEquals("Invalid amount in controller", 0, _ctrl.getCurrentAmount());
        assertEquals("Invalid amount in UI", 0, _interf.currBalance());

        // check to see if the expected event passed by
        assertEquals("Invalid UI event size", 0, uiEvents.size());
        assertEquals("Invalid controller event size", 0, cEvents.size());
        assertEquals("Invalid hardware event size", 1, hwEvents.size());
        assertTrue(((HWEvents) hwEvents.get(0)).notAvailable());
        assertFalse(((HWEvents) hwEvents.get(0)).notEnoughMoney());
        assertFalse(((HWEvents) hwEvents.get(0)).selectionOkChange());
        assertFalse(((HWEvents) hwEvents.get(0)).selectionOkNChange());
    }
    
    @Test
    public void testSneakPath_11() {
        // check state
        assertEquals("Invalid initial state", VendingState.IDLE, _ctrl.getVendingState());
        // check initial amount
        assertEquals("Invalid initial amount in controller", 0, _ctrl.getCurrentAmount());
        assertEquals("Invalid initial amount in UI", 0, _interf.currBalance());

        clearEventLog();

        selectionOKChange();
        pause();

        // check state change
        assertEquals("Invalid state", VendingState.IDLE, _ctrl.getVendingState());

        // check if addToAmount worked
        assertEquals("Invalid amount in controller", 0, _ctrl.getCurrentAmount());
        assertEquals("Invalid amount in UI", 0, _interf.currBalance());

        // check to see if the expected event passed by
        assertEquals("Invalid UI event size", 0, uiEvents.size());
        assertEquals("Invalid controller event size", 0, cEvents.size());
        assertEquals("Invalid hardware event size", 1, hwEvents.size());
        assertFalse(((HWEvents) hwEvents.get(0)).notAvailable());
        assertFalse(((HWEvents) hwEvents.get(0)).notEnoughMoney());
        assertTrue(((HWEvents) hwEvents.get(0)).selectionOkChange());
        assertFalse(((HWEvents) hwEvents.get(0)).selectionOkNChange());
        
        clearEventLog();

        selectionOKNoChange();
        pause();

        // check state change
        assertEquals("Invalid state", VendingState.IDLE, _ctrl.getVendingState());

        // check if addToAmount worked
        assertEquals("Invalid amount in controller", 0, _ctrl.getCurrentAmount());
        assertEquals("Invalid amount in UI", 0, _interf.currBalance());

        // check to see if the expected event passed by
        assertEquals("Invalid UI event size", 0, uiEvents.size());
        assertEquals("Invalid controller event size", 0, cEvents.size());
        assertEquals("Invalid hardware event size", 1, hwEvents.size());
        assertFalse(((HWEvents) hwEvents.get(0)).notAvailable());
        assertFalse(((HWEvents) hwEvents.get(0)).notEnoughMoney());
        assertFalse(((HWEvents) hwEvents.get(0)).selectionOkChange());
        assertTrue(((HWEvents) hwEvents.get(0)).selectionOkNChange());
    }
    
    public void uiEvent(UIEvents e) {
        uiEvents.add(e);
        _ctrl.uiEvent(e);
    }
}
