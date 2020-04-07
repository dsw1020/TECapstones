package com.techelevator.model.park;

import java.util.List;

public interface ParkDAO {
	public List<Park> getAllParks();
	public List<Park> getParkInfo(long choice);
	public List<Park> getAllCampgroundsByParkId();
	public Park getParkByName(String usersParkChoice);
	public List<Park> getParkByID(long id);
	

}
