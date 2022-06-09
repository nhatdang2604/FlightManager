package com.tkpm.service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.tkpm.dao.TicketDAO;
import com.tkpm.entities.Flight;
import com.tkpm.entities.Ticket;
import com.tkpm.entities.TicketClass;

//Using enum for applying Singleton Pattern
public enum TicketService {

	INSTANCE;
	
	private TicketDAO ticketDAO;
	private FlightService flightService;
	
	private TicketService() {
		ticketDAO = TicketDAO.INSTANCE;
		flightService = FlightService.INSTANCE;
	}

	//Update tickets
	public Set<Ticket> updateTickets(List<Ticket> tickets) {
		return new TreeSet<>(ticketDAO.update(tickets));
	}
	
	//Create new tickets
	public Set<Ticket> createTickets(List<Ticket> tickets) {
		return new TreeSet<>(ticketDAO.create(tickets));
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
	
	//Get all the not-booked tickets with the given ticket class from a flight
	public List<Ticket> findAvailableTicketFromFlight(Flight flight, TicketClass ticketClass) {
		
		//Get all the tickets from flight
		Set<Ticket> tickets = findTicketFromFlight(flight);
		
		//In all tickets from flight, get all the ticket with the given class and had not been booked yet
		List<Ticket> availableTickets = tickets
				.stream()
				.filter(ticket -> !ticket.getIsBooked() && ticket.getTicketClass().equals(ticketClass))
				.collect(Collectors.toList());
									
		return availableTickets;
	}
	
	//Update tickets
	public Set<Ticket> updateTickets(List<Ticket> tickets) {
		return new TreeSet<>(ticketDAO.update(tickets));
	}
	
	//Create new tickets
	public Set<Ticket> createTickets(List<Ticket> tickets) {
		return new TreeSet<>(ticketDAO.create(tickets));
	}
	
	//Delete tickets by the given ids
	public int deleteTickets(List<Integer> ids) {
		return ticketDAO.delete(ids);
	}
}
 