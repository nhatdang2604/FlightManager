package com.tkpm.service;

import java.util.Set;
import java.util.TreeSet;

import com.tkpm.dao.AirportDAO;
import com.tkpm.entities.Airport;

//Using enum for applying Singleton Pattern
public enum AirportService {

	INSTANCE;
	
	private AirportDAO airportDAO;
	
	private AirportService() {
		airportDAO = AirportDAO.INSTANCE;
	}
	
	//Create new airport
	public Airport createAirport(Airport airport) {
		return airportDAO.create(airport);
	}
	
	//Update an airport
	public Airport updateAirport(Airport airport) {
		return airportDAO.update(airport);
	}
	
	//Delete an airport by the given id
	public int deleteAirport(Integer id) {
		return airportDAO.delete(id);
	}
	
	//Find all airports in database
	public Set<Airport> findAllAirports() {
		
		//Using set, because query in DAO only return list
		return new TreeSet<>(airportDAO.findAll());
		
	}
	
	//Find airport by id
	public Airport findAirportById(Integer id) {
		return airportDAO.find(id);
	}
}
 