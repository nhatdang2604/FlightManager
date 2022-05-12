package com.tkpm.service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.tkpm.dao.ReservationDAO;
import com.tkpm.entities.Flight;
import com.tkpm.entities.Reservation;

//Using enum for applying Singleton Pattern
public enum ReservationService {

	INSTANCE;
	
	private ReservationDAO reservationDAO;
	private FlightService flightService;
	
	private ReservationService() {
		reservationDAO = ReservationDAO.INSTANCE;
		flightService = FlightService.INSTANCE;
	}
	
	//Create new reservations
	public Set<Reservation> createReservations(List<Reservation> reservations) {
		return new TreeSet<>(reservationDAO.create(reservations));
	}
	
	//Update reservations
	public Set<Reservation> updateReservations(List<Reservation> reservations) {
		return new TreeSet<>(reservationDAO.update(reservations));
	}
	
	//Delete reservations by the given ids
	public int deleteReservations(List<Integer> ids) {
		return reservationDAO.delete(ids);
	}
	
	//Find all reservations in database
	public Set<Reservation> findAllReservations() {
		
		//Using set, because query in DAO only return list
		return new TreeSet<>(reservationDAO.findAll());
		
	}
	
	//Find reservations by id
	public Reservation findReservationById(Integer id) {
		return reservationDAO.find(id);
	}
	
	//Find all reservations from a flight
	public Set<Reservation> findReservationFromFlight(Flight flight) {
		
		if (null == flight.getReservations()) {
			flight = flightService.findFlightById(flight.getId());
		}
		
		return flight.getReservations();
	}
}
 