package com.techelevator.model.reservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;



public class JdbcReservationDAO implements ReservationDAO {
	private JdbcTemplate jdbcTemplate;

	public JdbcReservationDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<Reservation> getAllReservations() {
		List<Reservation> newReservationList = new ArrayList<Reservation>();
		String getAllReservationsStr = "SELECT * FROM Reservation";
		SqlRowSet results = jdbcTemplate.queryForRowSet(getAllReservationsStr);
		while (results.next()) {
			Reservation newReservation = mapRowToReservation(results);
			newReservationList.add(newReservation);
		}
		return newReservationList;

	}

	
	private Reservation mapRowToReservation(SqlRowSet results) {
		Reservation theReservation;
		theReservation = new Reservation();
		theReservation.setReservation_id(results.getLong("reservation_id"));
		theReservation.setSite_id(results.getLong("site_id"));
		theReservation.setName(results.getString("name"));
		theReservation.setFrom_date(results.getDate("from_date").toLocalDate());
		theReservation.setTo_date(results.getDate("to_date").toLocalDate());
		theReservation.setCreate_date(results.getDate("create_date").toLocalDate());
		return theReservation;
	}

	@Override
	public Reservation createReservation(long siteId, String reservationName, String arrivalDate, String departureDate) {
		String createReservation = "INSERT INTO reservation (reservation_id, site_id, name, from_date, to_date, create_date) VALUES (?, ?, ?, ?::DATE, ?::DATE, CURRENT_DATE)";
		Long reservationId = getNextReservationId();
		jdbcTemplate.update(createReservation, reservationId, siteId,reservationName, arrivalDate, departureDate );
		Reservation reservation = new Reservation();
		reservation.setReservation_id(reservationId);
		reservation.setName(reservationName);
		reservation.setSite_id(siteId);
		
		return reservation;
	}

	@Override
	public List<Reservation> getAllActiveReservations() {
		List<Reservation> allActiveReservations = new ArrayList<Reservation>();

		String sqlGetAllActiveReservations = "SELECT reservation_id , site_id, name, from_date ,to_date, create_date " 
												+ "FROM reservation " + "WHERE from_date IS NOT NULL AND(to_date IS NULL OR to_date > ?)";

		SqlRowSet activeReservationResults = jdbcTemplate.queryForRowSet(sqlGetAllActiveReservations, LocalDate.now());

		while (activeReservationResults.next()) {
			Reservation theReservation = mapRowToReservation(activeReservationResults);
			allActiveReservations.add(theReservation);
		}
		return allActiveReservations;
	
	}
	
	private Long getNextReservationId() {
		SqlRowSet nextIdResult= jdbcTemplate.queryForRowSet("SELECT nextval('reservation_reservation_id_seq')");
		if (nextIdResult.next()) {
		return nextIdResult.getLong(1);
		} else {
		throw new RuntimeException("Something went wrong while getting an Id for the reservation");
		}
	}

}
