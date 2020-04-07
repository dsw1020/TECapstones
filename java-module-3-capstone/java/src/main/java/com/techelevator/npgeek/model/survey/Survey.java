package com.techelevator.npgeek.model.survey;

import javax.validation.constraints.AssertTrue;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class Survey {

	private int surveyId;
	private String parkCode;
	
	private String state;
	private String activity;
	
	
	@NotBlank(message ="Email address is required")
	@Email(message="Email must be a vaild email address")
	private String emailAddress;

	public int getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	@Override
	public String toString() {
		return "Survey [surveyId=" + surveyId + ", parkCode=" + parkCode + ", emailAddress=" + emailAddress + ", state="
				+ state + ", activity=" + activity + "]";
	}
	
	
	
}
	
