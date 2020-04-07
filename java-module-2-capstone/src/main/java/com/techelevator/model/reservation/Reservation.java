package com.techelevator.model.reservation;

import java.time.LocalDate;

public class Reservation {
	private long reservation_id;
	private long site_id;
	private String name;
	private LocalDate from_date;
	private LocalDate to_date;
	private LocalDate create_date;
	
	
	public long getReservation_id() {
		return reservation_id;
	}
	public void setReservation_id(long reservation_id) {
		this.reservation_id = reservation_id;
	}
	public long getSite_id() {
		return site_id;
	}
	public void setSite_id(long site_id) {
		this.site_id = site_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getFrom_date() {
		return from_date;
	}
	public void setFrom_date(LocalDate from_date) {
		this.from_date = from_date;
	}
	public LocalDate getTo_date() {
		return to_date;
	}
	public void setTo_date(LocalDate to_date) {
		this.to_date = to_date;
	}
	public LocalDate getCreate_date() {
		return create_date;
	}
	public void setCreate_date(LocalDate create_date) {
		this.create_date = create_date;
	}

	public String getMonth(String mm) {
		
		String month = "";
		
		switch (mm) {
		
		case "01": month = "January";
				   break;
		case "02": month = "February";
		 		   break;
		case "03": month = "March";
				   break;
		case "04": month = "April";
		 		   break;
		case "05": month = "May";
		 		   break;
		case "06": month = "June";
		 		   break;
		case "07": month = "July";
		 		   break;
		case "08": month = "August";
		 		   break;
		case "09": month = "September";
		 		   break;
		case "10": month = "October";
		 		   break;
		case "11": month = "November";
		 		   break;
		case "12": month = "December";
		 		   break;
		 
		
		}
		return month;
	
}
	
	
}
