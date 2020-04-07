package com.techelevator;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.campground.Campground;
import com.techelevator.model.campground.CampgroundDAO;
import com.techelevator.model.campground.JdbcCampgroundDAO;
import com.techelevator.model.park.JdbcParkDAO;
import com.techelevator.model.park.Park;
import com.techelevator.model.park.ParkDAO;
import com.techelevator.model.reservation.*;
import com.techelevator.model.site.JdbcSiteDAO;
import com.techelevator.model.site.Site;
import com.techelevator.model.site.SiteDAO;
import com.techelevator.view.*;

public class CampgroundApp {

	/****************************************************************************************************
	 * This is the Campground Reservation system application program
	 * 
	 * Any and all user interactions should be placed in the CampgroundUI class
	 * which is in the com.techelevator.view package.
	 * 
	 * This application program should instantiate a CampgroundUI object and use its
	 * methods to interact with the user.
	 * 
	 * This application program should contain no user interactions.
	 * 
	 * Any and all database accesses should be placed in the appropriate
	 * com.techelevator.model.tablename package component
	 * 
	 * This application program should instantiate DAO objects and use the methods
	 * in the DAO to interact with the database tables.
	 * 
	 * There should be no SQL in this application program
	 * 
	 ***************************************************************************************************/

	public static void main(String[] args) throws Exception {

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		final String MENU_VIEW_CAMPGROUND = "View Campgrounds";
		final String MENU_SEARCH_FOR_RESERVATION = "Search for Reservation";
		final String RETURN_TO_PREVIOUS_SCREEN = "Return to previous screen";
		final String[] SELECT_A_COMMAND = new String[] { MENU_VIEW_CAMPGROUND, MENU_SEARCH_FOR_RESERVATION,
				RETURN_TO_PREVIOUS_SCREEN };
		
		final String SEARCH_AVAILABLE_RESERVATIONS = "Search Available Reservations";
		final String RETURN_TO_PREVIOUS_SCREEN_2 = "Return to previous screen";
		final String[] SELECT_A_COMMAND2 = new String[] { SEARCH_AVAILABLE_RESERVATIONS,
				RETURN_TO_PREVIOUS_SCREEN_2  };
		/****************************************************************************************************
		 * Instantiate any DAOs you will be using here
		 ***************************************************************************************************/
		JdbcCampgroundDAO campDao = new JdbcCampgroundDAO(dataSource);
		JdbcParkDAO parkDao = new JdbcParkDAO(dataSource);
		JdbcReservationDAO resDao = new JdbcReservationDAO(dataSource);
		JdbcSiteDAO siteDao = new JdbcSiteDAO(dataSource);

		/****************************************************************************************************
		 * Your application programming logic goes here
		 ***************************************************************************************************/

		/*******************************************************************************************************
		 * Class member variables, objects, constants and methods should be coded here.
		 ******************************************************************************************************/

		CampgroundUI userInterface = new CampgroundUI(); // Object to manage user interactions;
		userInterface.displayApplicationBanner();

		boolean shouldProcess = true;
		Park usersParkChoice = null;
		Campground usersCampgroundChoice = null;
		while (shouldProcess) {
			List<Park> listOfParks = parkDao.getAllParks();
			usersParkChoice = userInterface.displayparksToUser(listOfParks);

			if (usersParkChoice == null) {
				userInterface.endMethodProcessing();
				shouldProcess = false;
			}
			userInterface.displayDescription(usersParkChoice);

			boolean shouldCampgroundProcess = true;
			while (shouldCampgroundProcess) {

				String choice = userInterface.displayCommandPrompts(SELECT_A_COMMAND);

				long park_id = usersParkChoice.getPark_id();
				
				List<Campground> campgroundList = campDao.getAllCampgrounds(usersParkChoice.getPark_id());
				
				userInterface.displayCampsToUser(campgroundList);
				
				String choice2 = userInterface.displayCommandPrompts(SELECT_A_COMMAND2);
					
				switch (choice2) {
					case SEARCH_AVAILABLE_RESERVATIONS: 
						
						usersCampgroundChoice = userInterface.offerCampChoice(campgroundList);
						
						List<Reservation> listOfReservations = resDao.getAllReservations();
						
						List<Campground> campgroundList1 = campDao.getAllCampgrounds(usersParkChoice.getPark_id());
						
						boolean shouldReservationProcess = true;
						
						//while (shouldReservationProcess) {
						String arrivalDateString = userInterface.userArrivalDate();
						String departureDateString = userInterface.userDepartureDate();
						LocalDate arrivalDate = LocalDate.parse(arrivalDateString);
						LocalDate departureDate = LocalDate.parse(departureDateString);
							
						List<Site> openSites = siteDao.getAllSites(usersCampgroundChoice.getCampground_id(), arrivalDate, departureDate);
						Site chosenSite = userInterface.offerSites(openSites);
						//}
						String resName = userInterface.getReservationName();
						Reservation reservation = resDao.createReservation(chosenSite.getSite_id(), resName, arrivalDate.toString(), departureDate.toString());
						//String reservationName = userInterface.getReservationName();
						userInterface.displayReservationConfirmation(reservation);
						userInterface.endMethodProcessing();
						break;
						
					

					case RETURN_TO_PREVIOUS_SCREEN: {
						shouldCampgroundProcess = false;
						break;

					}
					}
				}
			}
		}
	}
