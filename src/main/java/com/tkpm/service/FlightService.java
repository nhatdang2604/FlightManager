package com.tkpm.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.tkpm.dao.FlightDAO;
import com.tkpm.entities.Airport;
import com.tkpm.entities.Flight;

//Using enum for applying Singleton Pattern
public enum FlightService {

	INSTANCE;
	
	private FlightDAO flightDAO;
	
	private FlightService() {
		flightDAO = FlightDAO.INSTANCE;
	}
	
	//Create new flight
	public Flight createFlight(Flight flight) {
		return flightDAO.create(flight);
	}
	
	//Update an flight
	public Flight updateFlight(Flight flight) {
		return flightDAO.update(flight);
	}
	
	//Delete an flight by the given id
	public int deleteFlight(Integer id) {
		return flightDAO.delete(id);
	}
	
	//Find all flights in database
	public Set<Flight> findAllFlights() {
		
		//Using set, because query in DAO only return list
		return new TreeSet<>(flightDAO.findAll());
		
	}
	
	public Set<Flight> findFlightByCriterias(
			Airport departureAirport,
			Airport arrivalAirport,
			LocalDateTime datetime,
			LocalDate startDate,
			LocalDate endDate) {
		
		Set<Flight> flights = findAllFlights(); 
		
		if (null != departureAirport) {
			flights = flights
					.stream()
					.filter(flight -> flight.getDepartureAirport().equals(departureAirport))
					.collect(Collectors.toSet());
		}
		
		if (null != arrivalAirport) {
			flights = flights
					.stream()
					.filter(flight -> flight.getArrivalAirport().equals(arrivalAirport))
					.collect(Collectors.toSet());
		}
		
		if (null != datetime) {
			flights = flights
					.stream()
					.filter(flight -> flight.getDateTime().equals(datetime))
					.collect(Collectors.toSet());
		}
		
		if (null != startDate && null != endDate) {

			flights = flights
					.stream()
					.filter(flight -> flight.getDateTime().toLocalDate().isAfter(startDate) &&
										flight.getDateTime().toLocalDate().isBefore(endDate))
					.collect(Collectors.toSet());
			
		} else if (null != startDate) {
			
			flights = flights
					.stream()
					.filter(flight -> flight.getDateTime().toLocalDate().isAfter(startDate))
					.collect(Collectors.toSet());
			
		} else if (null != endDate) {
			
			flights = flights
					.stream()
					.filter(flight -> flight.getDateTime().toLocalDate().isBefore(endDate))
					.collect(Collectors.toSet());
			
		}
		
		return flights;
	}
	
	//Find flight by id
	public Flight findFlightById(Integer id) {
		return flightDAO.find(id);
	}
}
 