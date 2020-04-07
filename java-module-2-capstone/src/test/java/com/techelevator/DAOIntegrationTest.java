package com.techelevator;

import static org.junit.Assert.*;


import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.campground.Campground;
import com.techelevator.model.campground.JdbcCampgroundDAO;
import com.techelevator.model.park.JdbcParkDAO;
import com.techelevator.model.reservation.JdbcReservationDAO;
import com.techelevator.model.site.JdbcSiteDAO;



public class DAOIntegrationTest {

	/* Using this particular implementation of DataSource so that
	 * every database interaction is part of the same database
	 * session and hence the same database transaction */
	private static SingleConnectionDataSource dataSource;
	private static final String TEST_CAMPGROUND = "CAMP";
	private static final String TEST_PARK = "PARK";
	private static final String TEST_RESERVATION = "RES";
	private static final String TEST_SITE = "SITE";
	
	
	private JdbcCampgroundDAO campDao;
	private JdbcParkDAO parkDao;
	private JdbcReservationDAO resDao;
	private JdbcSiteDAO siteDao;
	
	private JdbcTemplate jdbcTemplate;


	/* Before any tests are run, this method initializes the datasource for testing. */
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		/* The following line disables autocommit for connections
		 * returned by this DataSource. This allows us to rollback
		 * any changes after each test */
		dataSource.setAutoCommit(false);
	}

	/* After all tests have finished running, this method will close the DataSource */
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}
	
	@Before
	public void setup() {
		String sqlInsertCampground = "INSERT INTO Campground (campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee ) VALUES (?,?,?,?,?,?)";
		//String sqlInsertPark = "INSERT INTO Department (name) VALUES (?)"
		//String sqlInsertReservation = "INSERT INTO Department (name) VALUES (?)"
		//String sqlInsertSite = "INSERT INTO Department (name) VALUES (?)"
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource); 	// Instantiate the JdbcTemplate so we can test
		
		jdbcTemplate.update(sqlInsertCampground, 11, 1, TEST_CAMPGROUND, 02, 11, (new BigDecimal (35))); 		// Actually INSERT the test data
		
		campDao = new JdbcCampgroundDAO(dataSource); 							// Instantiate the DAO containing the method(s) to test
	}

	/* After each test, we rollback any changes that were made to the database so that
	 * everything is clean for the next test */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	
	@Test
	public void get_all_campgrounds_test() {
		List<Campground> allCampgrounds = campDao.getAllCampgrounds(1L);
		assertTrue(allCampgrounds.size()>0);
	}
	
	
	
	@Test
	public void get_campground_by_id() {
		Campground theCampground = campDao.getCampgroundById(1L);
		assertEquals(1, theCampground.getCampground_id());
	
}
	
	
//	private void assertCampgroundsAreEqual(Campground expected, Campground actual) {
//		assertEquals(expected.getCampground_id(), actual.getCampground_id());
//		assertEquals(expected.getPark_id(), actual.getPark_id());
//		assertEquals(expected.getName(), actual.getName());
//		assertEquals(expected.getOpen_from_mm(), actual.getOpen_from_mm());
//		assertEquals(expected.getOpen_to_mm(), actual.getOpen_to_mm());
//		assertEquals(expected.getDaily_fee(), actual.getDaily_fee());
//}
	
	
	
	// Test Campground Method Generator
	private Campground getCampground(String name) {
		Campground theCampground = new Campground();
		theCampground.setName(name);
		return theCampground;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/* This method provides access to the DataSource for subclasses so that
	 * they can instantiate a DAO for testing */
	protected DataSource getDataSource() {
		return dataSource;
	}
}
