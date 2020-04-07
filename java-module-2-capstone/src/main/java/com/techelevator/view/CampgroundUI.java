package com.techelevator.view;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.techelevator.CampgroundApp;
import com.techelevator.model.campground.Campground;
import com.techelevator.model.park.Park;
import com.techelevator.model.reservation.Reservation;
import com.techelevator.model.site.Site;

public class CampgroundUI {

	/*******************************************************************************************************
	 * This is the CampgroundUI class
	 * 
	 * All user interactions should be coded here
	 * 
	 * The application program will instantiate an object of this class and use the
	 * object to interact with the user.
	 * 
	 * And data required from the user for the application will be acquired by
	 * methods of this class and sent back to the user either as the return value
	 * from the method or in an object returned from the method.
	 * 
	 * Any messages the application programmer wishes to display to the user may be
	 * sent to methods of this class as variables or objects. Any messaging method
	 * may also have "canned" messages the user may request by a message id.
	 * 
	 *******************************************************************************************************/

	/*******************************************************************************************************
	 * Menu class object
	 * 
	 * Use this Menu object for ALL Menu choices presented to the user
	 * 
	 * This is the same Menu class discussed in module 1 and made available in the
	 * module 1 capstone
	 * 
	 * 
	 ******************************************************************************************************/

	Menu CampMenu = new Menu(System.in, System.out); // Define menu for keyboard input and screen output
	CampgroundApp cApp = new CampgroundApp();

	
	/*
		displayApplicationBanner();
		while (true) {
			printHeading("Select a Park for Further Details");
			Park choice = (Park) CampMenu.getChoiceFromOptions(parks.toArray());
			System.out.println(choice.getDescription());
		}
	}*/
	/*
	
	public void run() throws ParseException {
		displayApplicationBanner();
		while (true) {
			printHeading("Select a Park for Further Details");
			String choice = (String) CampMenu.getChoiceFromOptions(PARK_MENU_OPTIONS);
			if (choice.equals(PARKS_INTERFACE_OPTION_SELECT_PARK)) {
				handleParkInformation();
			} else if (choice.equals(PARKS_INTERFACE_OPTION_EXIT)) {
				System.exit(0);
			}
		}
	}


	

		

	private void handleParkInformation() {
		printHeading("Park Infromation Screen");
		String choice = (String) CampMenu.getChoiceFromOptions(PARKS_INFORMATION_MENU_OPTIONS);
		if (choice.equals(PARKS_INFORMATION_MENU_OPTION_SELECT_PARK)) {
			handleCampgrounds();
			if (choice.equals(PARKS_INFORMATION_MENU_OPTION_SEARCH_RESERVATION)) {
				handleSearchReservation();
			} else if (choice.equals(PARKS_INFORMATION_MENU_OPTION_RETURN)) {
				endMethodProcessing();
			}
		}
	}

	private void handleParkInformation() {
		printHeading("Park Infromation Screen");
		String choice = (String) CampMenu.getChoiceFromOptions(PARKS_INFORMATION_MENU_OPTIONS);
		if (choice.equals(PARKS_INFORMATION_MENU_OPTION_SELECT_PARK)) {
			handleCampgrounds();
			if (choice.equals(PARKS_INFORMATION_MENU_OPTION_SEARCH_RESERVATION)) {
				handleSearchReservation();
			} else if (choice.equals(PARKS_INFORMATION_MENU_OPTION_RETURN)) {
				endMethodProcessing();
			}
	
	
	
	
	
	
	
	private void handleCampgrounds() {
		printHeading("Campgrounds");
		String choice = (String) CampMenu.getChoiceFromOptions(PARKS_CAMPGROUND_MENU_OPTIONS);
		if (choice.equals(PARKS_CAMPGROUND_MENU_OPTION_SELECT_PARK)) {
			cApp.handleListAllCampgrounds();
		} else if (choice.equals(PARKS_CAMPGROUND_MENU_OPTION_EXIT)) {
			endMethodProcessing();
		}
	}
	
		}
	}
	
	*/
	
	
	public Park displayparksToUser(List<Park> listOfParks) {
		
		String[] parkNames = new String[listOfParks.size()+1];	
		
		int i = 0;
		for (Park aPark : listOfParks) {
			String parkName = aPark.getName();
			parkNames[i] = parkName;
			i++;
		}
		
		parkNames[listOfParks.size()] = "Quit";
		
		String userChoice = (String)CampMenu.getChoiceFromOptions(parkNames);
		for (Park park: listOfParks) {
			if (park.getName().equals(userChoice)) {
				return park;
			}
		}
		
		return null;
		
	}
	
	public Campground offerCampChoice(List<Campground> listofCampgrounds) {
		System.out.println("What campground would you like to select?");
		Campground aChoice = (Campground)CampMenu.getChoiceFromOptions(listofCampgrounds.toArray());
		return aChoice;
	}

	public void displayCampsToUser(List<Campground> listofCampgrounds) {
		String outputString = "";
		for (Campground campground: listofCampgrounds) {
			outputString += String.format("%-25s", campground.getName());
			outputString += String.format("%-25s", campground.getOpen_from_mm());
			outputString += String.format("%-25s", campground.getOpen_to_mm());
			outputString += String.format("%-25s", campground.getDaily_fee());
			outputString += "\n";
		}
		System.out.println(outputString);
		
	}

	private void printHeading(String headingText) {
		System.out.println("\n" + headingText);
		for (int i = 0; i < headingText.length(); i++) {
			System.out.print("-");
		}
		System.out.println();
	}

	public void endMethodProcessing() { // static attribute used as method is not associated with specific object
												// instance
		System.out.println("Thank you! Have a great day!");// Any processing that needs to be done before method ends
	}

	public static void parkSelection() {
		System.out.println("Which Park would you like to select");
		
	}
	public void displayDescription(Park parkInfo) {
		System.out.println(parkInfo.getName() + " National Park");
		System.out.println("Location: " + parkInfo.getLocation());
		System.out.println("Eastablished: " + parkInfo.getEstablish_date());
		System.out.println("Area " + parkInfo.getArea() +"km");
		System.out.println("Annual Visitors " + parkInfo.getVisitors());
		System.out.println();
		System.out.print(parkInfo.getDescription());
		System.out.println();
		System.out.println();
	}
	public void displayCampgroundDescription(Campground campgroundInfo) {
		System.out.println("Campground Name: "+ campgroundInfo.getName());
		System.out.println("Open: " + campgroundInfo.getOpen_from_mm());
		System.out.println("Closed: " + campgroundInfo.getOpen_to_mm());
		System.out.println("Daily Fee: " + campgroundInfo.getDaily_fee());
		System.out.println();
		System.out.println();
	}
	
	
	public void displayApplicationBanner() {
		System.out.println("Greetings! Welcome to the...");
		printHeading("National Park Campsite Reservation System");
	}
	public String displayCommandPrompts(String[] SELECT_A_COMMAND) {
		System.out.println();
		System.out.println("Please Select a Command");
		System.out.println();
		String choice = (String)CampMenu.getChoiceFromOptions(SELECT_A_COMMAND);
		return choice;
	}
	public String userArrivalDate() {
		System.out.println("What is the arrival date (yyyy-mm-dd)?");
		Scanner dateScanner = new Scanner(System.in);
		String userArrivalDate = dateScanner.nextLine();
		return userArrivalDate;
	}
	public String userDepartureDate() {
		System.out.println("What is the departure date (yyyy-mm-dd)?");
		Scanner dateScanner = new Scanner(System.in);
		String userDepartureDate = dateScanner.nextLine();
		return userDepartureDate;
	}

	public Site offerSites(List<Site> openSites) {
		Site choice = (Site)CampMenu.getChoiceFromOptions(openSites.toArray());
		return choice;
	}
	public String getReservationName() {
		System.out.println("What name should the reservation be made under?");
		Scanner reservationNameScanner = new Scanner(System.in);
		String userReservationName = reservationNameScanner.nextLine();
		return userReservationName;
	}

	public void displayReservationConfirmation(Reservation reservation) {
		
		
		System.out.print("The reservation has been made and the confirmation number is : " + reservation.getReservation_id());
		System.out.println();
		
	}
	
	/*******************************************************************************************************
	 * Class member variables, objects, constants and methods should be coded here.
	 ******************************************************************************************************/

}
