package com.tkpm.service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.tkpm.dao.ReservationDAO;
import com.tkpm.entities.Flight;
import com.tkpm.entities.Reservation;
import com.tkpm.entities.Ticket;
import com.tkpm.entities.TicketClass;

//Using enum for applying Singleton Pattern
public enum ReservationService {

	INSTANCE;
	
	private ReservationDAO reservationDAO;
	private FlightService flightService;
	private TicketService ticketService;
	
	private ReservationService() {
		reservationDAO = ReservationDAO.INSTANCE;
		flightService = FlightService.INSTANCE;
		ticketService = TicketService.INSTANCE;
	}
	
	//Delete reservations by the given ids
	public int deleteReservations(List<Integer> ids) {
		return reservationDAO.delete(ids);
	}
	
	//Update reservations
	public Set<Reservation> updateReservations(List<Reservation> reservations) {
		return new TreeSet<>(reservationDAO.update(reservations));
	}
	
	//Create new reservations
	public Set<Reservation> createReservations(List<Reservation> reservations) {
		return new TreeSet<>(reservationDAO.create(reservations));
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
	
	//Find all reservations from a flight
	public Set<Reservation> findReservationFromFlight(Flight flight) {
		
		if (null == flight.getReservations()) {
			flight = flightService.findFlightById(flight.getId());
		}
		
		return flight.getReservations();
	}
	
	//Get all the not-booked reservation with the given ticket class from a flight
	public List<Reservation> findAvailableReservationFromFlight(Flight flight, TicketClass ticketClass) {
		
		//Get all the availalbe tickets from a flight with the given ticket class
		List<Ticket> tickets = ticketService.findAvailableTicketFromFlight(flight, ticketClass);
		
		//From the got tickets, get all the reservations
		List<Reservation> reservations = tickets
				.stream()
				.map(ticket -> ticket.getReservation())
				.collect(Collectors.toList());
		
		return reservations;
	}
}
 