package com.techelevator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class PurchaseLog {
	
	public void logPurchase(String slot, Item item, BigDecimal initialBalance) {
		
		BigDecimal endingBalance = initialBalance.subtract(item.getPrice());
		
		String event = item.getName() + "  " + slot;
		
		printToLogFile(event,initialBalance,endingBalance);
	
	
	}
	public void logFeed(BigDecimal amountAdded, BigDecimal initialBalance)  {
		BigDecimal endingBalance = initialBalance.add(amountAdded);
		printToLogFile("Money Feed"+amountAdded,initialBalance,endingBalance);
	
	}
	
	
	public void logOutOfStock(BigDecimal balance)  {
		printToLogFile("Item is unavailable.", balance, balance);
	
	}
	
	public void notEnoughMoney(BigDecimal balance)  {
		printToLogFile("There are insufficent funds.",balance,balance);
	
	}
	
	
	
	
	public void logChange(BigDecimal availableFunds)  {
		printToLogFile("The change dispensed"+ availableFunds.toString(),availableFunds, new BigDecimal("0.00"));
	}
	
	
	private void printToLogFile(String event, BigDecimal start, BigDecimal finish) { // this will throw IOException {

		

		StringBuilder theLogEntry = new StringBuilder();			//Lines of output (formats)
		
		theLogEntry.append(String.format("", new SimpleDateFormat("MM/dd/YYYY hh:mm:ss a").format(new java.util.Date())));
		
		theLogEntry.append(String.format( "", event));
		
		theLogEntry.append(String.format("" + start.toString()));
		
		theLogEntry.append(String.format("" + finish.toString()));

		

		File fileLog = new File("Log.txt");			// Our Audit Log

		

		if (!fileLog.exists()) {						// If log file does not exist, create it
			
			try {
				fileLog.createNewFile();
			} catch (IOException e) {
				System.out.println("***** UNABLE TO CREATE LOG FILE *****");
			}
			
			
		} else if (fileLog.exists() && fileLog.isDirectory()) {
			System.out.println("DIRECTORY WITH NAME \"Log.txt\" exists.***\n");
		}


		try (FileOutputStream fileOutput = new FileOutputStream(fileLog,true); 
			PrintWriter printWriter = new PrintWriter(fileOutput)) {

			

			printWriter.println(theLogEntry);						// Audit Log entry
			printWriter.flush();

		} catch (IOException e) {
			System.out.println("AUDIT LOG HAS BEEN DELETED.  UNABLE TO RECORD TRANSACTION ");
		}
	}

	PurchaseLog theLog = new PurchaseLog();
	boolean shouldProcess = true;         // Loop control variable
	
	while(true) {                // Loop until user indicates they want to exit
		
		String choice = (String)vendingMenu.getChoiceFromOptions(MAIN_MENU_OPTIONS);  // Display menu and get choice
	
		if(choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
			System.out.println(theMachine.displayItems());		//We need a getter and stter for display items
		
		} else if(choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
			
			//THE PURCHASE MENU BELOW//
			
			while(true) {
					System.out.println(" Current Balance is $" + theMachine.getDepositedMoney());
					String purchaseChoice = (String) vendingMenu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					
					if(purchaseChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						
						//THE FEED MONEY MENU BELOW//
						
						while(true) {
							
							System.out.println(" The Current Balance is $" + theMachine.getDepositedMoney());
							String feedChoice = (String) vendingMenu.getChoiceFromOptions(FEED_MENU_OPTIONS);
							
							if(feedChoice.equals(MAIN_MENU_OPTION_EXIT));
							break;
							
							}
						theMachine.depositMoney(new Dollar(feedChoice.replace("$", "")));
					
						}
					 else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
						 	System.out.println(" The Current Balance is $" + theMachine.getDepositedMoney());
						 	System.out.println(theMachine.display.items());
						 	System.out.println(" Please Make A Selection ");
						 	vendingMenu.getChoiceFromOptions(theMachine);
					
			
			}		else if(purchaseChoice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
						Change usersChange = new Change();
						theLog.logChange(theMachine.returnChange());
						System.out.println(usersChange.makeChange(theMahcine.returnChange()));
						break;
			}
					
		}
}
