package com.techelevator;

import java.util.List;

public class VendingMachine {


	private double alltheMoney = 0.0; // The full money count in machine

	private final int NUMBER_ROWS; // Rows where snacks are
	private final int NUMBER_COLS; // Columns where snacks are

	private Item[][] availableItems; // The items available
	private double depositedMoney; // Money deposited by user
	private double collectedMoney; // Money collected by machine (total collected by machine)

	public VendingMachine(int rows, int cols, int stockingQuantity) {
		depositedMoney = 0.0;	//Starts at zero
			
		collectedMoney = 0.0;	//Starts at zero

		NUMBER_ROWS = rows;		//Row placement of snack/drink

		NUMBER_COLS = cols;		//Column placement of snack/drink

		availableItems = new Item[NUMBER_ROWS][NUMBER_COLS];
		}

	public String vending(int row, int col, int allTheMoney) {
		Item theSnackPicked = availableItems[row][col];

		if (depositedMoney >= theSnackPicked.getCost() && theSnackPicked.getName()) {

			depositedMoney = depositedMoney - theSnackPicked.getCost();	// deposited money, subtract the cost of item
			
			allTheMoney = allTheMoney + theSnackPicked.getCost();

			return theSnackPicked.getName();

		} else {
			return null;
		}
	}

	public static double getAllCollectedMoney() {
		return allTheMoney;
	}

	public void depositMoney(Dollar dollars) {	
		depositedMoney = depositedMoney + dollars;
	}

	public double getDepositedMoney() {
		return depositedMoney;
	}

	public double getCollectedMoney() {
		return collectedMoney;

	}

	public double getChange() {
		double change = depositedMoney;
		depositedMoney = 0.0;
		return change;
	}
	public char[] displayItems() {
		// TODO Auto-generated method stub		// HAVE TO MAKE THIS A "displayItems() method"
		return null;
	}
}

