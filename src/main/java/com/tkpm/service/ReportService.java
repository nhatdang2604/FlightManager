package com.tkpm.service;

import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.tkpm.entities.Flight;
import com.tkpm.wrapper.FlightStatisticWrapper;
import com.tkpm.wrapper.report.BaseReport;

public enum ReportService {
	INSTANCE;
	
	private FlightService flightService;
	
	private ReportService() {
		flightService = FlightService.INSTANCE;
	}
	
	public BaseReport getReportByMonth(Month month, int year) {
		
		BaseReport report = null;
		
		//Filtering the flights by month and year, then get the wrapper for each flight
		Set<Flight> flights = flightService.findAllFlights();
		List<FlightStatisticWrapper> wrappers = flights
				.stream()
				.filter(flight -> 
					flight.getDateTime().getMonth().equals(month) &&
					flight.getDateTime().getYear() == year)
				.map(flight -> new FlightStatisticWrapper(flight))
				.collect(Collectors.toList());
		
		//Setup report
		report = new BaseReport(wrappers);
		report.setMonth(month)
			.setYear(year);
		
		return report;
	}
	
	public BaseReport getReportByYear(int year) {
		
		BaseReport report = null;
		
		//Filtering the flights by month and year, then get the wrapper for each flight
		Set<Flight> flights = flightService.findAllFlights();
		List<FlightStatisticWrapper> wrappers = flights
				.stream()
				.filter(flight -> flight.getDateTime().getYear() == year)
				.map(flight -> new FlightStatisticWrapper(flight))
				.collect(Collectors.toList());
		
		//Setup report
		report = new BaseReport(wrappers);
		report.setYear(year);
		
		return report;
		
	}
}
