package com.tkpm.service;

import java.time.Month;
import java.util.ArrayList;
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
	
	private	List<FlightStatisticWrapper> filterFlightsByMonthAndYear(Set<Flight> flights, int month, int year) {
		return flights
				.stream()
				.filter(flight -> 
					flight.getDateTime().getMonth().getValue() == month &&
					flight.getDateTime().getYear() == year)
				.map(flight -> new FlightStatisticWrapper(flight))
				.collect(Collectors.toList());
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
	
	public List<BaseReport> getDataToReportByYear(int year) {
		
		List<BaseReport> reports = new ArrayList<>();
		reports.add(null);	//padding, to make report.get(1) become report for Januaray, .get(2), ... .get(12)
		
		//Filtering the flights by month and year, then get the wrapper for each flight
		Set<Flight> flights = flightService.findAllFlights();
		for (int month = 1; month <= 12; ++month) {
		
			//Setup report
			List<FlightStatisticWrapper> wrappers =  filterFlightsByMonthAndYear(flights, month, year);
			BaseReport reportByMonth = new BaseReport(wrappers);
			reportByMonth.setYear(year);
			reports.add(reportByMonth);
		}
		
		return reports;
		
	}
}
