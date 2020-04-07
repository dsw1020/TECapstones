package com.techelevator.model.park;

import java.util.Date;

public class Park {
	private long park_id;
	private String name;
	private String location;
	private Date establish_date;
	private long area;
	private long visitors;
	private String description;
	
	
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getEstablish_date() {
		return establish_date;
	}
	public void setEstablish_date(Date establish_date) {
		this.establish_date = establish_date;
	}
	public long getArea() {
		return area;
	}
	public void setArea(long area) {
		this.area = area;
	}
	public long getVisitors() {
		return visitors;
	}
	public void setVisitors(long visitors) {
		this.visitors = visitors;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String descitpion) {
		this.description = descitpion;
	}
	@Override
	public String toString() {
		return this.name;
		/*
		return "Park [park_id=" + park_id + ", name=" + name + ", location=" + location + ", establish_date="
				+ establish_date + ", area=" + area + ", visitors=" + visitors + ", description=" + description + "]";
				*/
	}

}
