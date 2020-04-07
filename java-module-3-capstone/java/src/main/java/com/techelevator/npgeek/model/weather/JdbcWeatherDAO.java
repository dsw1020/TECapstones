package com.techelevator.npgeek.model.weather;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
// Implement the WeatherDAO to allow JDBC Class to utilize parent

@Component
public class JdbcWeatherDAO implements WeatherDAO {

	private JdbcTemplate jdbcTemplate; // Create private JDBC Object template

	@Autowired
	public JdbcWeatherDAO(DataSource dataSource) { // Establish object for the data source
		this.jdbcTemplate = new JdbcTemplate(dataSource); // Link object and data source to Java JDBC
	}

// Method to get data from database 
//	@Override
//	public Weather getWeatherByParkCode(String parkCode) { // Call method from DAO
//		String sqlGetWeatherByParkCode = "SELECT park.parkcode, forecast, fivedayforecastvalue, low, high " // SQL statement to pull data from the database
//				+ "FROM weather " 
//				+ "INNER JOIN park " 
//				+ "ON park.parkcode = weather.parkcode ";
//		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetWeatherByParkCode); // create object to store the data from rows in the NPGeek database
//		Weather parkWeather = null;
//		while (results.next()) { // Create while loop to iterate through the data table results object
//			parkWeather = mapRowToWeather(results); // store the data in the parkWeather object as the while loop is
//													// true
//		}
//		return parkWeather; // Return the object with the data from the NPGeek database
//	}
	
	
	@Override
	public Weather getTodayWeather(String parkCode) {
		Weather today = new Weather();
		String sqlSelectWeather = "SELECT * FROM weather WHERE parkcode = ? AND fivedayforecastvalue = 1";
		SqlRowSet results =jdbcTemplate.queryForRowSet(sqlSelectWeather, parkCode);
		while(results.next()) {
			today.setParkCode(results.getString("parkcode"));
			today.setTempLow(results.getInt("low"));
			today.setTempHigh(results.getInt("high"));
			today.setForecast(results.getString("forecast"));
			today.setDay(results.getInt("fivedayforecastvalue"));
		}
		return today;
	}
	
	
	
	@Override
	public List<Weather> nextFour(String parkCode) {
		List<Weather> fourDayWeather = new ArrayList<Weather>();
		String sqlSelectFourWeathers = "SELECT * FROM weather WHERE parkcode = ? AND fivedayforecastvalue != 1";
		SqlRowSet results =jdbcTemplate.queryForRowSet(sqlSelectFourWeathers, parkCode);
		while(results.next()) {
			Weather day = new Weather();
			day.setParkCode(results.getString("parkcode"));
			day.setTempLow(results.getInt("low"));
			day.setTempHigh(results.getInt("high"));
			day.setForecast(results.getString("forecast"));
			day.setDay(results.getInt("fivedayforecastvalue"));
			fourDayWeather.add(day);
		}
		return fourDayWeather;
	}
	
	
	
	@Override
	public List<Weather> nextFourCelcius(String parkCode) {
		List<Weather> fourDayWeather = new ArrayList<Weather>();
		String sqlSelectFourWeathers = "SELECT * FROM weather WHERE parkcode = ? AND fivedayforecastvalue != 1";
		SqlRowSet results =jdbcTemplate.queryForRowSet(sqlSelectFourWeathers, parkCode);
		while(results.next()) {
			Weather day = new Weather();
			day.setParkCode(results.getString("parkcode"));
			day.setTempLow(convertTempToCelcius(results.getInt("low")));
			day.setTempHigh(convertTempToCelcius(results.getInt("high")));
			day.setForecast(results.getString("forecast"));
			day.setDay(results.getInt("fivedayforecastvalue"));
			fourDayWeather.add(day);
		}
		return fourDayWeather;
	}
	
	@Override
	public Weather getTodayWeatherCelcius(String parkCode) {
		Weather today = new Weather();
		String sqlSelectWeather = "SELECT * FROM weather WHERE parkcode = ? AND fivedayforecastvalue = 1";
		SqlRowSet results =jdbcTemplate.queryForRowSet(sqlSelectWeather, parkCode);
		while(results.next()) {
			today.setParkCode(results.getString("parkcode"));
			today.setTempLow(convertTempToCelcius(results.getInt("low")));
			today.setTempHigh(convertTempToCelcius(results.getInt("high")));
			today.setForecast(results.getString("forecast"));
			today.setDay(results.getInt("fivedayforecastvalue"));
		}
		return today;
	}
	

//	private Weather mapRowToWeather(SqlRowSet results) {
//		// Creating a new Weather object in order to place the data from the database
//		// within the object
//		Weather theWeather = new Weather();
//		// setting the table data from the database; translates the database data over
//		// to Java
//		theWeather.setParkCode(results.getString("parkcode")); // 'parkcode' (and following) are the column names in the database				
//		theWeather.setForecast(results.getString("forecast"));
//		theWeather.setTempLow(results.getInt("low"));
//		theWeather.setTempHigh(results.getInt("high"));
//		theWeather.setDay(results.getInt("fivedayforecastvalue"));
//
//		return theWeather;
//	}
	
	// Make a temperature converter class and pass all the temperature data through the class
	
	@Override
	public List<Weather> fiveDayForecast(String parkCode, String tempChoice){
		List<Weather> forecast = new ArrayList<Weather>();
		String sqlForecast = "Select * from weather where parkcode = ?";
		SqlRowSet results =jdbcTemplate.queryForRowSet(sqlForecast, parkCode);
		while(results.next()) {
			Weather day = new Weather();
			day.setParkCode(results.getString("parkcode"));
			day.setTempLow(results.getInt("low"));
			if (tempChoice.equals("Celcius")) {
				day.setTempLow(convertTempToCelcius(day.getTempLow()));
			}
			day.setTempHigh(results.getInt("high"));
			if (tempChoice.equals("Celcius")) {
				day.setTempHigh(convertTempToCelcius(day.getTempHigh()));
			}
			day.setForecast(results.getString("forecast"));
			day.setDay(results.getInt("fivedayforecastvalue"));
			forecast.add(day);
		}
		
		return forecast;
	}
	
	@Override
	public int convertTempToCelcius(int tempFahrenheit) {
		int tempCelcius = (tempFahrenheit - 32) * (5/9);
	return tempCelcius;
}
}
