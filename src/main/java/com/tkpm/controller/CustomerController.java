package com.tkpm.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.tkpm.entities.BaseAccount;
import com.tkpm.entities.Flight;
import com.tkpm.entities.FlightDetail;
import com.tkpm.entities.Reservation;
import com.tkpm.entities.Ticket;
import com.tkpm.entities.TicketClass;
import com.tkpm.service.FlightService;
import com.tkpm.service.PolicyService;
import com.tkpm.service.ReservationService;
import com.tkpm.service.TicketClassService;
import com.tkpm.service.TicketService;
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
	protected TicketService ticketService;
	protected ReservationService reservationService;
	protected PolicyService policyService;
	
	protected BaseAccount account;
	
	public void setAccount(BaseAccount account) {
		this.account = account;
	}
	
	public CustomerController(BaseMainFrame mainFrame) { 
		this.mainFrame = mainFrame;
		flightService = FlightService.INSTANCE;
		ticketClassService = TicketClassService.INSTANCE;
		reservationService = ReservationService.INSTANCE;
		ticketService = TicketService.INSTANCE;
		policyService = PolicyService.INSTANCE;
		//initFeatures();
	}
	
	protected void initFeatures() {
		initFlightFeatures();
	}
	
	
	protected void initFlightFeatures() {
		initListFlightFeature();
	}
	
	private void initTicketForm(TicketForm form) {
		form.getSubmitButton().addActionListener(event -> {
			String ticketClassName = form.getTicketClass();
			
			Flight flight = form.getFlight();
			
			//Check policy 
			if (policyService.isLateToBook(flight)) {
				form.setError(TicketForm.TIMEOUT_ERROR);
				return;
			}
			
			//Get the icket class to find available ticket
			TicketClass ticketClass = ticketClassService.findTicketClassByName(ticketClassName);
			
			//Check out of stock ticket
			Reservation reservation = reservationService.findAvailableReservationFromFlight(flight, ticketClass);
			if (null == reservation) {
				form.setError(TicketForm.OUT_OF_STOCK_ERROR);
				return;
			}
			
			//If there is a reservation avaialbe => book
			reservation.setBookDate(LocalDate.now());
			reservation.setAccount(account);
			Ticket ticket = reservation.getTicket();
			ticket.setIsBooked(true);
			ticket.setName(form.getSubmitName());
			ticket.setIdentityCode(form.getSubmitIdentityCode());
			ticket.setPhoneNumber(form.getSubmitPhone());
			
			//Update information for booking
			ticketService.updateTicket(ticket);
			reservationService.updateReservation(reservation);
			
			//Close the form
			form.setVisible(false);
		});
	}
	
	protected void initListFlightFeature() {
		FlightFeatureView featureView = (FlightFeatureView) mainFrame
				.getFeatureViews()
				.get(CustomerMainFrame.FLIGHT_FEATURE_INDEX);
		
		FlightTabbedControllerView controllerView = featureView.getTabbedControllerView();
		
		//Init table
		initFlightListRead(controllerView);
		
		//Open detail flight view if clicked
		FlightListTableView table = controllerView.getFlightListTableView();
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
	
	
	protected void initFlightListRead(FlightTabbedControllerView controllerView) {
		FlightListTableView table = controllerView.getFlightListTableView();
		List<Flight> flights = new ArrayList<>(flightService.findAllFlights());
		
		List<Flight> availableFlights = 
				flights
				.stream()
				.filter(flight -> flight.getDateTime().isBefore(LocalDateTime.now()))
				.collect(Collectors.toList());
		table.setFlights( availableFlights);
		table.update();
	}
	
	public void run() {
		initFeatures();
		mainFrame.open();
	}
}
