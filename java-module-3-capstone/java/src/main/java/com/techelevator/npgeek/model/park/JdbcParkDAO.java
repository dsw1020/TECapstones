package com.techelevator.npgeek.model.park;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcParkDAO implements ParkDAO {

	private JdbcTemplate myJdbcTemplate;
	
	@Autowired
	public JdbcParkDAO(DataSource aDataSource) {
		this.myJdbcTemplate = new JdbcTemplate(aDataSource);
	}
	
	@Override
	public List<Park> getAllParks() {
		List<Park> parkList = new ArrayList<Park>();
		String sqlViewAllParks = "select * from park";
		SqlRowSet results = myJdbcTemplate.queryForRowSet(sqlViewAllParks);
		while (results.next()) {
			Park thePark = mapRowToPark(results);
			parkList.add(thePark);
		}

		return parkList;
	}

	@Override
	public Park getParkInformation(String parkCode) {
		Park thePark = new Park();
		String sqlGetParkInformation = "select * from park where parkcode = ? order by parkname";
		SqlRowSet results = myJdbcTemplate.queryForRowSet(sqlGetParkInformation, parkCode);
		if (results.next()) {
			thePark = mapRowToPark(results);
		}
		return thePark;
	}

	@Override
	public List<Park> getFavorites() {
		List<Park> favoriteParks = new ArrayList<Park>();
		String sqlSelectAllSurveys = "SELECT COUNT (survey_result.parkcode) AS count, parkname, park.parkcode AS parkcode FROM survey_result INNER JOIN park ON park.parkcode = survey_result.parkcode GROUP BY parkname, park.parkcode ORDER BY COUNT DESC, parkname";
		SqlRowSet results = myJdbcTemplate.queryForRowSet(sqlSelectAllSurveys);
		while(results.next()) {
			Park surveyedPark = new Park();
			surveyedPark.setCode(results.getString("parkcode"));
			surveyedPark.setCount(results.getInt("count"));
			surveyedPark.setName(results.getString("parkname"));
			favoriteParks.add(surveyedPark);
		}
		
	
		return favoriteParks;
	}
	
	
		
	private Park mapRowToPark(SqlRowSet results) {

		Park thePark = new Park();
		
		thePark.setCode(results.getString("parkcode"));
		thePark.setName(results.getString("parkname"));
		thePark.setState(results.getString("state"));
		thePark.setAcreage(results.getInt("acreage"));
		thePark.setElevation(results.getInt("elevationinfeet"));
		thePark.setMilesOfTrail(results.getDouble("milesoftrail"));
		thePark.setNumberOfCampsites(results.getInt("numberofcampsites"));
		thePark.setClimate(results.getString("climate"));
		thePark.setYearFounded(results.getInt("yearfounded"));
		thePark.setAnnualVisitorCount(results.getInt("annualvisitorcount"));
		thePark.setQuote(results.getString("inspirationalquote"));
		thePark.setQuoteSource(results.getString("inspirationalquotesource"));
		thePark.setDescription(results.getString("parkdescription"));
		thePark.setEntryFee(results.getDouble("entryfee"));
		thePark.setNumberOfAnimals(results.getInt("numberofanimalspecies"));

		return thePark;
	}


	}


