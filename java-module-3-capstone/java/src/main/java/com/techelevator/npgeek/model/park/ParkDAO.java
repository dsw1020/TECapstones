package com.techelevator.npgeek.model.park;

import java.util.List;

import com.techelevator.npgeek.model.survey.Survey;

public interface ParkDAO {
	
	public Park getParkInformation(String parkCode);
	
	public List<Park> getAllParks();
	
	public List<Park> getFavorites();
	
	
}
