package com.tkpm.wrapper.report;

import java.util.List;

import com.tkpm.wrapper.FlightStatisticWrapper;

public class BaseReport {

	private List<FlightStatisticWrapper> wrappers;
	
	public BaseReport(List<FlightStatisticWrapper> wrappers) {
		this.setWrappers(wrappers);
	}

	//Getters and setters
	public List<FlightStatisticWrapper> getWrappers() {return wrappers;}
	public void setWrappers(List<FlightStatisticWrapper> wrappers) {this.wrappers = wrappers;}
	
	public Integer getTurnover() {
		int turnover = 0;
		
		for (FlightStatisticWrapper wrapper: wrappers) {
			turnover += wrapper.getTurnover();
		}
		
		return turnover;
	}
}
