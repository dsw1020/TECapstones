package com.techelevator.model.park;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcParkDAO implements ParkDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JdbcParkDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<Park> getAllParks() {

		List<Park> parkInfo = new ArrayList<>();
		String parks = "SELECT * FROM park ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(parks);
		while (results.next()) {
			parkInfo.add(mapRowToPark(results));
		}
		return parkInfo;
	}

	@Override
	public List<Park> getParkInfo(long choice) {
		List<Park> parkInfo = new ArrayList<>();
		String park = ("SELECT * FROM park WHERE park_id=?");
		jdbcTemplate.update(park, choice);
		SqlRowSet results = jdbcTemplate.queryForRowSet(park);
		while (results.next()) {
			parkInfo.add(mapRowToPark(results));
		}
		return parkInfo;
	}

	@Override
	public Park getParkByName(String usersParkChoice) {
		String sqlGetParkByName = "SELECT park_id, name, location, establish_date, area, visitors, description "
				+ "FROM park WHERE name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetParkByName, usersParkChoice);
		results.next();
		Park newPark = mapRowToPark(results);
		return newPark;
	}

	private Park mapRowToPark(SqlRowSet results) {
		Park thePark;
		thePark = new Park();
		thePark.setPark_id(results.getLong("park_id"));
		thePark.setName(results.getString("name"));
		thePark.setLocation(results.getString("location"));
		thePark.setEstablish_date(results.getDate("establish_date"));
		thePark.setArea(results.getLong("area"));
		thePark.setVisitors(results.getLong("visitors"));
		thePark.setDescription(results.getString("description"));
		
		return thePark;
	}

	@Override
	public List<Park> getAllCampgroundsByParkId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Park> getParkByID(long id) {
		List<Park> parks = new ArrayList<Park>();
		String getAllParks = "SELECT * FROM park WHERE park_id = ?";
		Park thePark;
		SqlRowSet results = jdbcTemplate.queryForRowSet(getAllParks, id);
		while (results.next()) {
			thePark = mapRowToPark (results);
			parks.add(thePark);
		
		}
		return parks;
	}

}
