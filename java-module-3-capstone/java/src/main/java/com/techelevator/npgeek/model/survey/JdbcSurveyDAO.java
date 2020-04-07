package com.techelevator.npgeek.model.survey;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.model.park.Park;

@Component
public class JdbcSurveyDAO implements SurveyDAO {

	private JdbcTemplate myJdbcTemplate;
	
	@Autowired
	public JdbcSurveyDAO (DataSource aDataSource) {
		this.myJdbcTemplate = new JdbcTemplate (aDataSource);
	}
	
	@Override
	public void save(Survey survey) {
	long surveyId = getNextSurveyId();
	String sqlInsertSurvey = "INSERT INTO survey_result(surveyid, parkcode, emailaddress, state, activitylevel) " +
			   "VALUES(?, ?, ?, ?, ?)";	                       
	myJdbcTemplate.update(sqlInsertSurvey,
	surveyId,
	survey.getParkCode(), 
	survey.getEmailAddress(),  
	survey.getState(),
	survey.getActivity()); 
		
	}

	private Long getNextSurveyId() {
		String sqlSelectNextId = "SELECT nextval('seq_surveyid'::regclass)";
		SqlRowSet nextIdResult = myJdbcTemplate.queryForRowSet(sqlSelectNextId);
		Long surveyId = null;
		if(nextIdResult.next()) {
			return nextIdResult.getLong(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the survey");
		
		}
		
	}
	
	private Survey mapRowToSurvey(SqlRowSet results) {
		Survey theSurvey = new Survey();
		
		theSurvey.setSurveyId(results.getInt("surveyid"));
		theSurvey.setParkCode(results.getString("parkcode"));
		theSurvey.setState(results.getString("state"));
		theSurvey.setEmailAddress(results.getString("emailaddress"));
		theSurvey.setActivity(results.getString("activitylevel"));
		return theSurvey;
	}
	
	
	
}

