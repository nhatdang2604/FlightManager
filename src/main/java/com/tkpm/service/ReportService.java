package com.tkpm.service;

import java.time.Month;
import java.util.LinkedList;
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
	
	private List<FlightStatisticWrapper> getWrappersForReportByMonth(Month month, int year, Set<Flight> flights) {
		return flights
				.stream()
				.filter(flight -> 
					flight.getDateTime().getMonth().equals(month) &&
					flight.getDateTime().getYear() == year)
				.map(flight -> new FlightStatisticWrapper(flight))
				.collect(Collectors.toList());
	}
	
	public BaseReport getReportByMonth(Month month, int year) {
		
		BaseReport report = null;
		
		//Filtering the flights by month and year, then get the wrapper for each flight
		Set<Flight> flights = flightService.findAllFlights();
		List<FlightStatisticWrapper> wrappers = getWrappersForReportByMonth(month, year, flights);
		
		//Setup report
		report = new BaseReport(wrappers);
		report.setMonth(month)
			.setYear(year);
		
		return report;
	}
	
	public List<BaseReport> getReportByYear(int year) {
		
		List<BaseReport> reports = new LinkedList<>();
		
		
		Set<Flight> flights = flightService.findAllFlights();

		//Filtering the flights by each month in year, then get the wrapper for each flight
		for (int i = 1; i <= 12; ++i) {
			Month month = Month.of(i);
			List<FlightStatisticWrapper> wrappers = getWrappersForReportByMonth(month, year, flights);
			
			//Setup report
			BaseReport report = new BaseReport(wrappers);
			report
			.setMonth(month)
			.setYear(year);
		
			reports.add(report);
		}
		
		return reports;
	}
}
