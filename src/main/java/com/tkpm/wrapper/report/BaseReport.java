package com.tkpm.wrapper.report;

import java.time.Month;
import java.time.Year;
import java.util.List;

import com.tkpm.wrapper.FlightStatisticWrapper;

public class BaseReport {

	private List<FlightStatisticWrapper> wrappers;
	private Month month;	//year report set this field to null
	private Integer year;
	
	public BaseReport(List<FlightStatisticWrapper> wrappers) {
		this.setWrappers(wrappers);
	}

	

	
	public Integer getTurnover() {
		int turnover = 0;
		
		for (FlightStatisticWrapper wrapper: wrappers) {
			turnover += wrapper.getTurnover();
		}
		
		return turnover;
	}
}
