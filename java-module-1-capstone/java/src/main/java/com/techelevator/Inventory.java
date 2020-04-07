package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Inventory {
	
	private Map<String, Item> inventoryMap;

	public Inventory() throws IOException {
		this.inventoryMap = new LinkedHashMap<String, Item>();
		this.stockInventory();

	}

	public Item getItemBySlotKey(String slotKey) {
		return inventoryMap.get(slotKey);
	}

	public void stockInventory() {
		try {
			File vMInventory = getInventoryFile("vendingmachine.csv");
			try (Scanner inventoryScanner = new Scanner(vMInventory)) {
				while (inventoryScanner.hasNextLine()) {
					String itemInfoFromFile = inventoryScanner.nextLine();
					Item currentItem = getValues(itemInfoFromFile);
					inventoryMap.put(currentItem.getSlot(), currentItem);
				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
			System.exit(1);
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			System.exit(1);
		}

	}

	public int getInventorySize() {
		return inventoryMap.size();
	}

	public Item getValues(String inventoryFileLine) {
		String[] values = inventoryFileLine.split("\\|");
		double priceAsDouble = Double.parseDouble(values[2]);

		int price = (int) (priceAsDouble * 100);
		return new Item (values[0], values[1], new Dollar(price), 5);
	}

	private static File getInventoryFile(String filePath) throws IOException {
		File vMInventory = new File(filePath);
		if (!vMInventory.exists()) {
			throw new FileNotFoundException("Inventory File : " + filePath + "does not exist.");
		}
		if (!vMInventory.isFile()) {
			throw new IOException("Inventory File : " + filePath + "exists, but is not a file.");

		}
		return vMInventory;

	}
	
	public void DisplayInventory () {
		String formattedLogLine = String.format("%1$ - \t %2$ - \\t %5$ - \\t %10$ - \\t", "Slot", "Product","Price", "Quantity");
		System.out.println(formattedLogLine);
		
		List<String> keys = new ArrayList <String>();
		keys.addAll(inventoryMap.keySet());
		Collections.sort(keys);
		
		for (String slot : keys) {
			Item currentItem = inventoryMap.get(slot);
			System.out.println(currentItem);
		}
	}
	
	public static boolean isValidProductSlotKey (String slotKey) {
		if (slotKey.length() < 2) {
			return false;
			
		}
		String[] slotKeySplit = slotKey.split("");
		boolean firstKeySpot = slotKeySplit[0].equalsIgnoreCase("A") || slotKeySplit[0].equalsIgnoreCase("B")
				|| slotKeySplit[0].equalsIgnoreCase("C") || slotKeySplit[0].equalsIgnoreCase("D");
		boolean secondKeySpot = slotKeySplit[1].equalsIgnoreCase("1") || slotKeySplit[1].equalsIgnoreCase("2")
				|| slotKeySplit[1].equalsIgnoreCase("3") || slotKeySplit[1].equalsIgnoreCase("4");

		return firstKeySpot && secondKeySpot;
		
	}

	public Dollar selectItemBySlot(String itemSelection, Dollar currentBalance) {
		Item selectedItem = inventoryMap.get(itemSelection);

		Dollar selectedItemPrice = selectedItem.getPrice();
		if (currentBalance.isGreaterThan(selectedItemPrice)) {
			selectedItem.decreaseQuantity();
			System.out.println("Enjoy your " + selectedItem.getName() + "." + "There are now "
					+ selectedItem.getQuantity() + " left in the Vendo Matic 800");
		
		return new Dollar (currentBalance.getTotalCents() - selectedItem.getPrice().getTotalCents());
		
		} else {
			System.out.println("Please insert more money");
			return new Dollar(currentBalance.getTotalCents());
		}
	}
	
	public boolean selectedProductInStock(String slotKey) {
		Item selectedItem = inventoryMap.get(slotKey);
		return selectedItem.getQuantity() > 0;
	}
	
	public void printSalesReport (File technicianSalesReport) {
		try (PrintWriter technicianSalesReportWriter = new PrintWriter
				(new FileOutputStream(technicianSalesReport, true))) {
				String technicianSalesReportHeader = String.format("%1$-\t %2$-\t %3$-\t", "ITEM NAME", "QUANTITY SOLD", "GROSS SALES");
				technicianSalesReportWriter.println(technicianSalesReportHeader);
				
				Dollar totalGrossSales = Dollar.ZERO_DOLLARS;
				for (Item item : inventoryMap.values()) {
					
					String itemName = item.getName();
					int itemQuantitySold = 5 - item.getQuantity();
					Dollar itemGrossSales = new Dollar(
							item.getPrice().getTotalCents() * itemQuantitySold);
					String formattedTechnicianSalesLine = String.format("%1$-\t %2$-\t %3$-\t", itemName, itemQuantitySold, itemGrossSales);
					
					technicianSalesReportWriter.println();
					technicianSalesReportWriter.println(formattedTechnicianSalesLine);
					technicianSalesReportWriter.flush();
					totalGrossSales = totalGrossSales.add(itemGrossSales);
				}
				technicianSalesReportWriter.println();
				technicianSalesReportWriter.println("Total Gross Sales : " + totalGrossSales);
				technicianSalesReportWriter.flush();
		
	} catch (IOException e) {
		System.out.println(e.getMessage());
		System.exit(1);
		
	}
	
	}
	
	
	
	
	
}
