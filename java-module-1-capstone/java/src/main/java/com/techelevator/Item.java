package com.techelevator;

public class Item {

	/************************************************************************************************
	 * Attributes
	 ***********************************************************************************************/

	private String name;
	private String slot;
	private Dollar price;
	private int quantity;

	/************************************************************************************************
	 * 4 arg Constructor
	 ***********************************************************************************************/

	
	
	public Item(String name, String slot, Dollar price, int quantity) {
		this.name = name;
		this.slot = slot;
		this.price = price;
		this.quantity = quantity;

	}

	/************************************************************************************************
	 * Getters & Setters
	 ***********************************************************************************************/

	public String getName() {
		return name;
	}

	public String getSlot() {
		return slot;
	}

	public Dollar getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/************************************************************************************************
	 * Methods
	 ***********************************************************************************************/

	public void decreaseQuantity() {
		this.quantity--;
	}

	public String isSoldOut() {
		if (quantity == 0) {
			return "SOLD OUT";
		}
		else {
			return Integer.toString(quantity);
		}
			
	}
	@Override
	public String toString() {
		String formattedLogLine = String.format("%1$-5s %2$-25s %3$-10s %4$-20s", slot, name, price, isSoldOut());

		return formattedLogLine;
	}
}
