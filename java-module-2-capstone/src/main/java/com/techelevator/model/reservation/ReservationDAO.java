package com.techelevator.model.reservation;

import java.util.List;

public interface ReservationDAO {
	public List<Reservation> getAllReservations();
	public Reservation createReservation (long siteId,String reservationName, String arrivalDate, String departureDate);
	public List<Reservation> getAllActiveReservations();
}
