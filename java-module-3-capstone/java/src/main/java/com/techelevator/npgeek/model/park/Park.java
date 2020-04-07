package com.techelevator.npgeek.model.park;

public class Park {

	private String code;
	private String Name;
	private String State;
	private int acreage;
	private int elevation;
	private double milesOfTrail;
	private int numberOfCampsites;
	private String Climate;
	private int yearFounded;
	private int annualVisitorCount;
	private String quote;
	private String quoteSource;
	private String description;
	private double entryFee;
	private int numberOfAnimals;
	private int count;
	

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getAcreage() {
		return acreage;
	}

	public void setAcreage(int acreage) {
		this.acreage = acreage;
	}

	public int getElevation() {
		return elevation;
	}

	public void setElevation(int elevation) {
		this.elevation = elevation;
	}

	public double getMilesOfTrail() {
		return milesOfTrail;
	}

	public void setMilesOfTrail(double d) {
		this.milesOfTrail = d;
	}

	public int getNumberOfCampsites() {
		return numberOfCampsites;
	}

	public void setNumberOfCampsites(int numberOfCampsites) {
		this.numberOfCampsites = numberOfCampsites;
	}

	public String getClimate() {
		return Climate;
	}

	public void setClimate(String climate) {
		Climate = climate;
	}

	public int getYearFounded() {
		return yearFounded;
	}

	public void setYearFounded(int yearFounded) {
		this.yearFounded = yearFounded;
	}

	public int getAnnualVisitorCount() {
		return annualVisitorCount;
	}

	public void setAnnualVisitorCount(int annualVisitorCount) {
		this.annualVisitorCount = annualVisitorCount;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public String getQuoteSource() {
		return quoteSource;
	}

	public void setQuoteSource(String quoteSource) {
		this.quoteSource = quoteSource;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getEntryFee() {
		return entryFee;
	}

	public void setEntryFee(double entryFee) {
		this.entryFee = entryFee;
	}

	public int getNumberOfAnimals() {
		return numberOfAnimals;
	}

	public void setNumberOfAnimals(int numberOfAnimals) {
		this.numberOfAnimals = numberOfAnimals;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	
	
	@Override
	public String toString() {
		return "Park [code=" + code + ", Name=" + Name + ", acreage=" + acreage + ", elevation="
				+ elevation + ", milesOfTrail=" + milesOfTrail + ", numberOfCampsites=" + numberOfCampsites
				+ ", Climate=" + Climate + ", yearFounded=" + yearFounded + ", annualVisitorCount=" + annualVisitorCount
				+ ", quote=" + quote + ", quoteSource=" + quoteSource + ", description=" + description + ", entryFee="
				+ entryFee + ", numberOfAnimals=" + numberOfAnimals + "]";
	}

	
	
}
