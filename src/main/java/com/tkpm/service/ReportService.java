package com.tkpm.service;

import com.tkpm.wrapper.report.BaseReport;

public enum ReportService {
	INSTANCE;
	
	private FlightService flightService;
	
	private ReportService() {
		flightService = FlightService.INSTANCE;
	}
	
	public BaseReport getReportByMonth(int month, int year) {
		//TODO:
		BaseReport report = null;
		
		
		return report;
	}
	
	public BaseReport getReportByYear(int year) {
		//TODO:
		BaseReport report = null;
		
		return report;
	}
}
