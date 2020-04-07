package com.techelevator.model.campground;

import java.math.BigDecimal;

public class Campground {

	private long campground_id;
	private long park_id;
	private String name;
	private String open_from_mm;
	private String open_to_mm;
	private BigDecimal daily_fee;

	public long getCampground_id() {
		return campground_id;
	}

	public void setCampground_id(long campground_id) {
		this.campground_id = campground_id;
	}

	public long getPark_id() {
		return park_id;
	}

	public void setPark_id(long park_id) {
		this.park_id = park_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOpen_from_mm() {
		return open_from_mm;
	}

	public void setOpen_from_mm(String open_from_mm) {
		this.open_from_mm = open_from_mm;
	}

	public String getOpen_to_mm() {
		return open_to_mm;
	}

	public void setOpen_to_mm(String open_to_mm) {
		this.open_to_mm = open_to_mm;
	}

	public BigDecimal getDaily_fee() {
		return daily_fee;
	}

	public void setDaily_fee(BigDecimal daily_fee) {
		this.daily_fee = daily_fee;
	}

	public String getMonth(String mm) {

		String month = "";

		switch (mm) {

		case "01":
			month = "January";
			break;
		case "02":
			month = "February";
			break;
		case "03":
			month = "March";
			break;
		case "04":
			month = "April";
			break;
		case "05":
			month = "May";
			break;
		case "06":
			month = "June";
			break;
		case "07":
			month = "July";
			break;
		case "08":
			month = "August";
			break;
		case "09":
			month = "September";
			break;
		case "10":
			month = "October";
			break;
		case "11":
			month = "November";
			break;
		case "12":
			month = "December";
			break;

		}
		return month;

	}
	
	@Override
	public String toString() {
		String campgroundInfo = this.getName()+ "\t" + this.getMonth(this.getOpen_from_mm())+ "\t" + this.getMonth(this.getOpen_to_mm()) + "\t"+ this.getDaily_fee();
		return campgroundInfo;
	}
}
