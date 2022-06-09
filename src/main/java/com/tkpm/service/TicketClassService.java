package com.tkpm.service;

import java.util.Set;
import java.util.TreeSet;

import com.tkpm.dao.TicketClassDAO;
import com.tkpm.entities.TicketClass;

//Using enum for applying Singleton Pattern
public enum TicketClassService {

	INSTANCE;
	
	private TicketClassDAO ticketClassDAO;
	
	private TicketClassService() {
		ticketClassDAO = TicketClassDAO.INSTANCE;
	}
	
	//Update a ticket class
	public TicketClass updateTicketClass(TicketClass ticketClass) {
		return ticketClassDAO.update(ticketClass);
	}
	
	//Create new ticket class
	public TicketClass createTicketClass(TicketClass ticketClass) {
		return ticketClassDAO.create(ticketClass);
	}

	//Find all ticket classes in database
	public Set<TicketClass> findAllTicketClasses() {
		
		//Using set, because query in DAO only return list
		return new TreeSet<>(ticketClassDAO.findAll());
		
	}
	
	
	//Delete a ticket class by the given id
	public int deleteTicketClass(Integer id) {
		return ticketClassDAO.delete(id);
	}
	
	//Find ticketClass by id
	public TicketClass findAirportById(Integer id) {
		return ticketClassDAO.find(id);
	}
	
	//Find all ticket classes in database
	public Set<TicketClass> findAllTicketClasses() {
		
		//Using set, because query in DAO only return list
		return new TreeSet<>(ticketClassDAO.findAll());
		
	}
	
	//Find ticketClass by id
	public TicketClass findAirportById(Integer id) {
		return ticketClassDAO.find(id);
	}
}
 