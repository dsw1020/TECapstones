package com.techelevator.npgeek.model.weather;

public class Weather {

	// Instantiate variables

	private String parkCode;
	private int day;
	private int tempLow; // The high temp
	private int tempHigh; // The low temp
	private String forecast;
	private String tempChange = "Fahrenheit";

	// Generate getters and setters

	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getTempLow() {
		return tempLow;
	}

	public void setTempLow(int tempLow) {
		this.tempLow = tempLow;
	}

	public int getTempHigh() {
		return tempHigh;
	}

	public void setTempHigh(int tempHigh) {
		this.tempHigh = tempHigh;
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
	}

	public String getTempChange() {
		return tempChange;
	}

	public void setTempChange(String tempChange) {
		this.tempChange = tempChange;
	}

	// Generate the toString method; used to format the final view of the data

	@Override
	public String toString() {
		return "Weather [parkCode=" + parkCode + ", day=" + day + ", tempLow=" + tempLow + ", tempHigh=" + tempHigh
				+ ", forecast=" + forecast + "]";
	}

}
