package com.tkpm.controller;

import java.util.ArrayList;
import java.util.List;

import com.tkpm.entities.Flight;
import com.tkpm.entities.FlightDetail;
import com.tkpm.service.FlightService;
import com.tkpm.service.TicketClassService;
import com.tkpm.view.feature_view.FlightFeatureView;
import com.tkpm.view.feature_view.detail_view.FlightListDetailView;
import com.tkpm.view.feature_view.tabbed_controller_view.FlightTabbedControllerView;
import com.tkpm.view.feature_view.table.FlightListTableView;
import com.tkpm.view.frame.BaseMainFrame;
import com.tkpm.view.frame.CustomerMainFrame;
import com.tkpm.view.frame.form.FlightDetailForm;
import com.tkpm.view.frame.form.TicketForm;

public class CustomerController {

	protected BaseMainFrame mainFrame;
	protected FlightService flightService;
	protected TicketClassService ticketClassService;
	
	public CustomerController(BaseMainFrame mainFrame) { 
		this.mainFrame = mainFrame;
		flightService = FlightService.INSTANCE;
		ticketClassService = TicketClassService.INSTANCE;
		initFeatures();
	}
	
	protected void initFeatures() {
		initFlightFeature();
	}
	
	
	protected void initFlightFeature() {
		initListFlight();
	}
	
	private void initTicketForm(TicketForm form) {
		form.getSubmitButton().addActionListener(event -> {
			fo
		});
	}
	
	protected void initListFlight() {
		FlightFeatureView featureView = (FlightFeatureView) mainFrame
				.getFeatureViews()
				.get(CustomerMainFrame.FLIGHT_FEATURE_INDEX);
		
		FlightTabbedControllerView controllerView = featureView.getTabbedControllerView();
		
		//Init table
		FlightListTableView table = controllerView.getFlightListTableView();
		List<Flight> flights = new ArrayList<>(flightService.findAllFlights());
		table.setFlights(flights);
		table.update();
		
		//Open detail flight view if clicked
		table.getDetailButton().addActionListener(event -> {
			
			//Lazy query for the flight detail
			Flight flight = table.getSelectedFlight();
			FlightDetail flightDetail = flightService.findFlightDetailByFlight(flight);
			flight.setDetail(flightDetail);
			
			//Popout the detail form
			FlightDetailForm detailView = new FlightDetailForm(flight, mainFrame);
			detailView.setVisible(true);
			
		});
		
		//Get detail view first
		FlightListDetailView detailView = controllerView.getFlightListDetailView();
		detailView.getBookButton().addActionListener(event -> {
			
			//Lazy query for the flight detail
			Flight flight = table.getSelectedFlight();
			
			//Do nothing if nothing is selected
			if (null == flight) {
				return;
			}
			
			//Get flight detail for the flight
			FlightDetail flightDetail = flightService.findFlightDetailByFlight(flight);
			flight.setDetail(flightDetail);
			
			//Build the ticketForm
			TicketForm ticketForm = new TicketForm(flight, mainFrame);
			initTicketForm(ticketForm);
			
			//Open the ticket form
			ticketForm.setVisible(true);
			
		});		
		
		
	}
	
}
