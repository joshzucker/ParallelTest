package vending.commonU;

/**
 * @author Stephane S. Some
 *
 */
public class SelectionObject {
	private int selectNum;
	private int amountEntered;
	public SelectionObject(int selectNum, int amountEntered) {
		this.selectNum = selectNum;
		this.amountEntered = amountEntered;
	}

	public int getAmountEntered() {
		return amountEntered;
	}

	public int getSelectNum() {
		return selectNum;
	}

}
