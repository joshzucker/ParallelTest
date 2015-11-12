package vending.hardwareAbstraction;

/**
 * @author Stephane S. Some
 *
 * A item in the vending machine
 */
public class VendingItem {
	private String name;
	private int price;
	private int quantity;

	public VendingItem(String name, int price, int initialQuantity) {
		this.name = name;
		this.price = price;
		this.quantity = initialQuantity;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public void removeOne() {
		this.quantity--;
	}

}
