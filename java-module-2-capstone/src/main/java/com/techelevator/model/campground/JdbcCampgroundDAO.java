package com.techelevator.model.campground;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.park.Park;

public class JdbcCampgroundDAO implements CampgroundDAO {
	private JdbcTemplate jdbcTemplate;

	public JdbcCampgroundDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<Campground> getAllCampgrounds(long park_id) {
		List<Campground> allCampgrounds = new ArrayList<Campground>();
		String sqlGetAllCampgrounds = "SELECT campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee "
				+ "FROM campground " + "WHERE park_id = ? ";

		SqlRowSet campgroundResults = jdbcTemplate.queryForRowSet(sqlGetAllCampgrounds, park_id);
		while (campgroundResults.next()) {
			Campground theCampground = mapRowToCampgrounds(campgroundResults);
			allCampgrounds.add(theCampground);
		}
		return allCampgrounds;
	}

	@Override
	public Campground getCampgroundById(long campground_id) {
		String sqlSearchCampgroundById = "SELECT * FROM campground WHERE campground_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchCampgroundById, campground_id);
		results.next();
		Campground newCampground = mapRowToCampgrounds(results);
		return newCampground;
	}

	
	private Campground mapRowToCampgrounds(SqlRowSet results) {
		Campground theCampground;
		theCampground = new Campground();
		theCampground.setCampground_id(results.getLong("campground_id"));
		theCampground.setPark_id(results.getLong("park_id"));
		theCampground.setName(results.getString("name"));
		theCampground.setOpen_from_mm(results.getString("open_from_mm"));
		theCampground.setOpen_to_mm(results.getString("open_to_mm"));
		theCampground.setDaily_fee(new BigDecimal(results.getString("daily_fee")));
		return theCampground;
	}

	public String getMonth(String mm) {
		return "returning wrong getMonth -- campground";
		
		}
	
	public Campground getCampgroundByUsersPark(Long usersParkChoice) {
		String sqlGetCampgroundByName = "SELECT * FROM campground WHERE campground_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetCampgroundByName, usersParkChoice);
		results.next();
		Campground newCampground = mapRowToCampgrounds(results);
		return newCampground;
	}
}
