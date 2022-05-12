package com.tkpm.service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.tkpm.dao.TicketDAO;
import com.tkpm.entities.Flight;
import com.tkpm.entities.Ticket;

//Using enum for applying Singleton Pattern
public enum TicketService {

	INSTANCE;
	
	private TicketDAO ticketDAO;
	private FlightService flightService;
	
	private TicketService() {
		ticketDAO = TicketDAO.INSTANCE;
		flightService = FlightService.INSTANCE;
	}
	
	//Create new tickets
	public Set<Ticket> createTickets(List<Ticket> tickets) {
		return new TreeSet<>(ticketDAO.create(tickets));
	}
	
	//Update tickets
	public Set<Ticket> updateTickets(List<Ticket> tickets) {
		return new TreeSet<>(ticketDAO.update(tickets));
	}
	
	//Delete tickets by the given ids
	public int deleteTickets(List<Integer> ids) {
		return ticketDAO.delete(ids);
	}
	
	//Find all tickets in database
	public Set<Ticket> findAllTickets() {
		
		//Using set, because query in DAO only return list
		return new TreeSet<>(ticketDAO.findAll());
		
	}
	
	//Find tickets by id
	public Ticket findTicketById(Integer id) {
		return ticketDAO.find(id);
	}
	
	//Find all tickets from a flight
	public Set<Ticket> findTicketFromFlight(Flight flight) {
		
		if (null == flight.getTickets()) {
			flight = flightService.findFlightById(flight.getId());
		}
		
		return flight.getTickets();
	}
}
 