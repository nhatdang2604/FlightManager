package com.tkpm.controller;

import java.util.ArrayList;
import java.util.List;

import com.tkpm.entities.Flight;
import com.tkpm.service.FlightService;
import com.tkpm.view.feature_view.FlightFeatureView;
import com.tkpm.view.feature_view.detail_view.FlightListDetailView;
import com.tkpm.view.feature_view.tabbed_controller_view.FlightTabbedControllerView;
import com.tkpm.view.feature_view.table.FlightListTableView;
import com.tkpm.view.frame.BaseMainFrame;
import com.tkpm.view.frame.CustomerMainFrame;

public class CustomerController {

	protected BaseMainFrame mainFrame;
	protected FlightService flightService;
	
	public CustomerController(BaseMainFrame mainFrame) { 
		this.mainFrame = mainFrame;
		flightService = FlightService.INSTANCE;
		initFeatures();
	}
	
	protected void initFeatures() {
		initFlightFeature();
	}
	
	
	protected void initFlightFeature() {
		initListFlight();
	}
	
	protected void initListFlight() {
		FlightFeatureView featureView = (FlightFeatureView) mainFrame
				.getFeatureViews()
				.get(CustomerMainFrame.FLIGHT_FEATURE_INDEX);
		
		FlightTabbedControllerView controllerView = featureView.getTabbedControllerView();
		
		//Get detail view first
		FlightListDetailView detail = controllerView.getFlightListDetailView();
		
		//Init table
		FlightListTableView table = controllerView.getFlightListTableView();
		List<Flight> flights = new ArrayList<>(flightService.findAllFlights());
		table.setFlights(flights);
		table.update();
		
		table.get
		
		
	}
	
}
