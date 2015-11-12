package vending.userInterface;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import vending.userInterface.events.UIEvents;
import vending.userInterface.events.UIEventsClass;
import vending.vendingController.events.CEvents;
import vending.vendingController.events.CEventsListener;

/**
 * @author Stephane S. Some
 * 
 */
public class VendingInterface extends UIEventsClass implements CEventsListener {

    private static final long serialVersionUID = -1739743447122540615L;
    private static int FWidth = 600;
    private static int FHeight = 500;
    private static int[] COINSVAL = { 5, 10, 25, 100 };
    private static String CURRENTBALSTRING = "Current Balance: ";
    private JLabel balance = new JLabel(CURRENTBALSTRING + 0 + " \u00A2");
    private int currBalance = 0;
    private JButton myButtons[];

    public VendingInterface() {
        myButtons = new JButton[12];
        myButtons[0] = new JButton("5 \u00A2");
        myButtons[1] = new JButton("10 \u00A2");
        myButtons[2] = new JButton("25 \u00A2");
        myButtons[3] = new JButton("1.00 \u0024");
        myButtons[4] = new JButton("Coin Return");
        myButtons[5] = new JButton("Coke 1.00 \u0024");
        myButtons[6] = new JButton("Mars 1.05  \u0024");
        myButtons[7] = new JButton("Hershey 1.05 \u0024");
        myButtons[8] = new JButton("Granola 1.50 \u0024");
        myButtons[9] = new JButton("Pepsi 1.25  \u0024");
        Container pane = this.getContentPane();
        pane.setLayout(new GridLayout(6, 2));
        for (int i = 0; i < 5; i++) {
            myButtons[i].addActionListener(new CoinActionListener(this, i));
            pane.add(myButtons[i]);
            myButtons[i + 5].addActionListener(new SelectionActionListener(this, i));
            pane.add(myButtons[i + 5]);
        }
        pane.add(balance);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setTitle("Vending Machine Simulator");
        setSize(FWidth, FHeight);
        setVisible(true);
    }

    public void cEvent(CEvents ev) {
        if (ev.message()) {
            JOptionPane.showMessageDialog(this, ev.stringValue(), "Message", JOptionPane.INFORMATION_MESSAGE);
        } else if (ev.newbalance()) {
            setBalance(ev.intValue());
        }
    }

    public void addToBalance(int val) {
        currBalance += val;
        setBalLabel();
    }

    public void setBalance(int val) {
        currBalance = val;
        setBalLabel();
    }

    public int currBalance() {
        return currBalance;
    }

    public void resetBalance() {
        currBalance = 0;
        setBalLabel();
    }

    private void setBalLabel() {
        balance.setText(CURRENTBALSTRING + currBalance + " \u00A2");
    }

    public class CoinActionListener implements ActionListener {
        private VendingInterface frame;
        private int num;

        public CoinActionListener(VendingInterface frame, int num) {
            this.frame = frame;
            this.num = num;
        }

        public void actionPerformed(ActionEvent e) {
            if (num < COINSVAL.length) {
                frame.fireUIEvent(new UIEvents(frame, UIEvents.COIN, COINSVAL[num]));
                frame.addToBalance(COINSVAL[num]);
            } else {
                frame.fireUIEvent(new UIEvents(frame, UIEvents.RETURNCOINS));
                frame.resetBalance();
            }
        }
    }

    public class SelectionActionListener implements ActionListener {
        private VendingInterface frame;
        private int num;

        public SelectionActionListener(VendingInterface frame, int num) {
            this.frame = frame;
            this.num = num;
        }

        public void actionPerformed(ActionEvent e) {
            frame.fireUIEvent(new UIEvents(frame, UIEvents.SELECTION, num));
        }
    }

    /**
     * 5c, 10c, 25c, $1, return, coke ($1), mars ($1.05), hersey ($1.05), granola ($1.50), pepsi ($1.25)
     * 
     * @return the JButton
     */
    public JButton[] getMyButtons() {
        return myButtons;
    }
}
