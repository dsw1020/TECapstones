package com.techelevator.model.campground;

import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public interface CampgroundDAO {

	public List<Campground> getAllCampgrounds(long park_id);

	//public Campground mapRowToCampgrounds(SqlRowSet results);

	public Campground getCampgroundById(long campground_id);

}
