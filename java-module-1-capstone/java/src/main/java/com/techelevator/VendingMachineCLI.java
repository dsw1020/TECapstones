package com.techelevator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**************************************************************************************************************************
*  This is your Vending Machine Command Line Interface (CLI) class
*
*  It is the main process for the Vending Machine
*
*  THIS is where most, if not all, of your Vending Machine interactions should be coded
*  
*  It is instantiated and invoked from the VendingMachineApp (main() application)
*  
*  Your code should be placed in here
***************************************************************************************************************************/
import com.techelevator.view.Menu; // Gain access to Menu class provided for the Capstone

public class VendingMachineCLI {

	// Main menu options defined as constants

	private static final String HIDDEN_MENU_PRINT_SALES_REPORT = " ";
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { HIDDEN_MENU_PRINT_SALES_REPORT, MAIN_MENU_OPTION_DISPLAY_ITEMS,
			MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY,
			PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION };


	private Menu menu; // Menu object to be used by an instance of this class
	private Inventory inventory;
	private Dollar currentBalance;
	private File purchaseLog;

	public VendingMachineCLI(Menu menu) { // Constructor - user will pas a menu for this class to use
		this.menu = menu;// Make the Menu the user object passed, our Menu
		this.inventory = inventory;
		this.currentBalance = Dollar.ZERO_DOLLARS;
		this.purchaseLog = new File("PurchaseLog.txt");
	}

	/**************************************************************************************************************************
	*  VendingMachineCLI main processing loop
	*  
	*  Display the main menu and process option chosen
	*
	*  It is invoked from the VendingMachineApp program
	*
	*  THIS is where most, if not all, of your Vending Machine objects and interactions 
	*  should be coded
	*
	*  Methods should be defined following run() method and invoked from it
	*
	***************************************************************************************************************************/

	public void run() {
	
		inventory.stockInventory();

		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(HIDDEN_MENU_PRINT_SALES_REPORT)) {
				File technicianSalesReport = generateTechnicianSalesReport();
				inventory.printSalesReport(technicianSalesReport);
				System.out.println("Sales Report Generated");

			} else if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				
				inventory.DisplayInventory();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				
				purchaseItems();
		
			}
		}
	}
			
			
			public File generateTechnicianSalesReport() {

				LocalDateTime now = LocalDateTime.now();
				String date = now.format(DateTimeFormatter.ISO_LOCAL_DATE);
				String time = now.format(DateTimeFormatter.ISO_LOCAL_TIME);
				String timeWithOutSeconds = time.substring(0, time.length() - 4);

				String nameOfTechnicianSalesReport = "Vendo-Matic-Sales" + "_" + date + "_" + timeWithOutSeconds + ".csv";

				File technicianSalesReport = new File("./", nameOfTechnicianSalesReport);
				return technicianSalesReport;
			}
		
	/*	public static void main(String[] args) {
			Menu theMenu = new Menu(System.in, System.out);
			VendingMachineCLI theCLI = new VendingMachineCLI(menu);
			theCLI.run(); */
			
		
		
		

	/********************************************************************************************************
	 * Methods used to perform processing
	 ********************************************************************************************************/
	public static void displayItems() { // static attribute used as method is not associated with specific object
										// instance
		// Code to display items in Vending Machine
	}

	public static void purchaseItems() { // static attribute used as method is not associated with specific object
											// instance
		// Code to purchase items from Vending Machine
	}

	public static void endMethodProcessing() { // static attribute used as method is not associated with specific object
												// instance
		// Any processing that needs to be done before method ends
	}
}

/*
 * switch(choice) { // Process based on user menu choice
 * 
 * case MAIN_MENU_OPTION_DISPLAY_ITEMS: displayItems(); // invoke method to
 * display items in Vending Machine break; // Exit switch statement
 * 
 * case MAIN_MENU_OPTION_PURCHASE: purchaseItems(); // invoke method to purchase
 * items from Vending Machine break; // Exit switch statement
 * 
 * case MAIN_MENU_OPTION_EXIT: endMethodProcessing(); // Invoke method to
 * perform end of method processing shouldProcess = false; // Set variable to
 * end loop break; // Exit switch statement } } return; // End method and return
 * to caller
 */