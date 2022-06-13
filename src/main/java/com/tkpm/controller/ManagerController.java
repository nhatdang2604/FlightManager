package com.tkpm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.tkpm.entities.Airport;
import com.tkpm.entities.Flight;
import com.tkpm.entities.FlightDetail;
import com.tkpm.entities.Transition;
import com.tkpm.service.AirportService;
import com.tkpm.service.TransitionAirportService;
import com.tkpm.view.feature_view.FlightFeatureView;
import com.tkpm.view.feature_view.FlightManagerFeatureView;
import com.tkpm.view.feature_view.detail_view.AirportCRUDDetailView;
import com.tkpm.view.feature_view.detail_view.CRUDDetailView;
import com.tkpm.view.feature_view.detail_view.FlightCRUDDetailView;
import com.tkpm.view.feature_view.tabbed_controller_view.FlightManagerTabbedControllerView;
import com.tkpm.view.feature_view.tabbed_controller_view.FlightTabbedControllerView;
import com.tkpm.view.feature_view.table.AirportCRUDTableView;
import com.tkpm.view.feature_view.table.FlightCRUDTableView;
import com.tkpm.view.feature_view.table.TransitionCRUDTableView;
import com.tkpm.view.frame.BaseMainFrame;
import com.tkpm.view.frame.CustomerMainFrame;
import com.tkpm.view.frame.ManagerMainFrame;
import com.tkpm.view.frame.form.AirportForm;
import com.tkpm.view.frame.form.FlightForm;

public class ManagerController extends CustomerController {

	//Forms
	protected AirportForm createAirportForm;
	protected AirportForm updateAirportForm;
	protected FlightForm createFlightForm;
	protected FlightForm updateFlightForm;
	
	//Services
	protected AirportService airportService;
	protected TransitionAirportService transitionService;
	
	public ManagerController(BaseMainFrame mainFrame) {
		super(mainFrame);
		airportService = AirportService.INSTANCE;
		transitionService = TransitionAirportService.INSTANCE;
	}

	@Override
	protected void initFeatures() {
		
		//Features from Customer role
		super.initFeatures();
		
		initFlightManagerFeatures();
		initReportFeatures();
	}
	
	protected void initFlightManagerFeatures() {
		FlightManagerFeatureView featureView = (FlightManagerFeatureView) mainFrame
				.getFeatureViews()
				.get(ManagerMainFrame.FLIGHT_MANAGER_FEATURE_INDEX);
		
		FlightManagerTabbedControllerView controllerView = featureView.getTabbedControllerView();
		
		initAirportCRUDFeature(controllerView);
		initFlightCRUDFeature(controllerView);
	}
	
	protected void initAirportCRUDFeature(FlightManagerTabbedControllerView controllerView) {
		createAirportForm = new AirportForm(mainFrame);
		updateAirportForm = new AirportForm(mainFrame);
		createAirportForm.setTitle("Tạo sân bay");
		updateAirportForm.setTitle("Cập nhật sân bay");
		
		initAirportClickRowDisplayDetail(controllerView);
		initAirportCreate(controllerView);
		initAirportRead(controllerView);
		initAirportUpdate(controllerView);
		initAirportDelete(controllerView);
	};
	
	protected void initAirportClickRowDisplayDetail(FlightManagerTabbedControllerView controllerView) {
		AirportCRUDDetailView detail = controllerView.getAirporCRUDDetailView();
		AirportCRUDTableView table = controllerView.getAirportCRUDTableView();
		
		table.getSelectionModel().addListSelectionListener(event -> {
			if (table.getSelectedRow() > -1) {
				Airport airport = table.getSelectedAirport();
				if (null != airport) {
					detail.setDataToDetailPanel(airport);
				}
			}
		});
	}
	
	protected void initAirportCreate(FlightManagerTabbedControllerView controllerView) {
		AirportCRUDDetailView detail = controllerView.getAirporCRUDDetailView();
		
		//Init "Create airport" button
		detail.getButtons().get(CRUDDetailView.CREATE_BUTTON_INDEX).addActionListener(event -> {
			createAirportForm.open();
		});
		
		//Init submit button of the airport form
		createAirportForm.getSubmitButton().addActionListener(event -> {
			
			//Validate the form
			if (createAirportForm.areThereAnyEmptyStarField()) {
				createAirportForm.setError(AirportForm.EMPTY_STAR_FIELD_ERROR);
				return;
			}
			
			//Check if there is an airport with the same name existed
			Airport airport = createAirportForm.submit();
			Airport testAirport = airportService.findAirportByName(airport.getName());
			if (null != testAirport) {
				createAirportForm.setError(AirportForm.NAME_EXISTED_FIELD_ERROR);
				return;
			}
			
			//validate success case
			airport = airportService.createAirport(airport);
			
			//Update the table view
			initAirportRead(controllerView);
			
			//Close the form
			createAirportForm.setError(AirportForm.NO_ERROR);
			createAirportForm.close();
			
		});
	}
	
	protected void initAirportRead(FlightManagerTabbedControllerView controllerView) {
		List<Airport> airports = airportService.findAllAirports();
		AirportCRUDTableView table = controllerView.getAirportCRUDTableView();
		table.setAirports(airports);
		table.update();
	}
	
	protected void initAirportUpdate(FlightManagerTabbedControllerView controllerView) {
		AirportCRUDTableView table = controllerView.getAirportCRUDTableView();
		table.getUpdateButton().addActionListener(event -> {
			Airport airport = table.getSelectedAirport();
			if (null == airport) {
				return;
			}
			updateAirportForm.setAirport(airport);
			updateAirportForm.setVisible(true);
		});
		
		updateAirportForm.getSubmitButton().addActionListener(event -> {
			
			//Validate the form
			if (updateAirportForm.areThereAnyEmptyStarField()) {
				updateAirportForm.setError(AirportForm.EMPTY_STAR_FIELD_ERROR);
				return;
			}
			
			//Check if there is an airport with the same name existed 
			//	(if same name => check if the id is the same or not)
			Airport airport = updateAirportForm.submit();
			Airport testAirport = airportService.findAirportByName(airport.getName());
			if (null != testAirport && !airport.getId().equals(testAirport.getId())) {
				updateAirportForm.setError(AirportForm.NAME_EXISTED_FIELD_ERROR);
				return;
			}
			
			//validate success case
			airport = airportService.updateAirport(airport);
			
			//Update the table view
			initAirportRead(controllerView);
			
			//Close the form
			updateAirportForm.setError(AirportForm.NO_ERROR);
			updateAirportForm.close();
		});
		
	}
	
	protected void initAirportDelete(FlightManagerTabbedControllerView controllerView) {
		AirportCRUDDetailView detail = controllerView.getAirporCRUDDetailView();
		AirportCRUDTableView table = controllerView.getAirportCRUDTableView();
		
		//Init "Delete airport" button
		detail.getButtons().get(CRUDDetailView.DELETE_BUTTON_INDEX).addActionListener(event -> {
			
			int input = JOptionPane.showConfirmDialog(mainFrame,
	        		"Bạn có chắc chắn muốn xóa ?\nDữ liệu bị xóa sẽ không thể khôi phục lại được.",
	        		"Xóa",
	        		JOptionPane.YES_NO_OPTION);
			
			if (JOptionPane.YES_OPTION == input) {
				List<Airport> airports = table.getSelectedAirports();
				
				//Get the id from the airport
				List<Integer> ids = airports
						.stream()
						.map(airport -> airport.getId())
						.collect(Collectors.toList());
				
				//Delete thoose airports
				airportService.deleteAirports(ids);
				
				//Update the table view
				initAirportRead(controllerView);
				
				//Success message
				JOptionPane.showMessageDialog(null, "Đã xóa thành công.");
			}
			
			
			
		});
	}
	
	protected void initFlightCRUDFeature(FlightManagerTabbedControllerView controllerView) {
		createFlightForm = new FlightForm(mainFrame);
		updateFlightForm = new FlightForm(mainFrame);
		createFlightForm.setTitle("Tạo chuyến bay");
		updateFlightForm.setTitle("Cập nhật chuyến bay");
		
		initFlightClickRowDisplayDetail(controllerView);
		initFlightCreate(controllerView);
		initFlightRead(controllerView);
		initFlightUpdate(controllerView);
		initFlightDelete(controllerView);
	}
	
	protected void initFlightClickRowDisplayDetail(FlightManagerTabbedControllerView controllerView) {
		FlightCRUDDetailView detail = controllerView.getFlightCRUDDetailView();
		FlightCRUDTableView table = controllerView.getFlightCRUDTableView();
		
		table.getSelectionModel().addListSelectionListener(event -> {
			if (table.getSelectedRow() > -1) {
				Flight flight = table.getSelectedFlight();
				if (null != flight) {
					detail.setDataToDetailPanel(flight);
				}
			}
		});
	}
	
	protected void initFlightCreate(FlightManagerTabbedControllerView controllerView) {
		FlightCRUDDetailView detail = controllerView.getFlightCRUDDetailView();
		
		//Init "Create flight" button
		detail.getButtons().get(CRUDDetailView.CREATE_BUTTON_INDEX).addActionListener(event -> {
			
			//Load the airport to choose for the form
			List<Airport>  airports = airportService.findAllAirports();
			createFlightForm.setAirports(airports);
			
			//Open the form
			createFlightForm.open();
		});
		
		//Init add transition button
		createFlightForm.getAddTransitionButton().addActionListener(event -> {
			List<Airport> airports = airportService.findAllAirports();
			createFlightForm.getTransitionForm().loadAirport(airports);
			createFlightForm.getTransitionForm().open();
		});
		
		//Init delete transition button
		createFlightForm.getDeleteTransitionButton().addActionListener(event -> {
			int input = JOptionPane.showConfirmDialog(createFlightForm,
	        		"Bạn có chắc chắn muốn xóa ?",
	        		"Xóa",
	        		JOptionPane.YES_NO_OPTION);
			
			
			TransitionCRUDTableView table = createFlightForm.getTable();
			
			if (JOptionPane.YES_OPTION == input) {
				List<Transition> original = table.getTransitions();
				List<Transition> selected = table.getSelectedTransitions();
				
				//Delete the reference
				for (Transition trans: selected) {
					original.removeIf(iter -> iter == trans);
				}
				
				table.setTransitions(original);
				table.update();
				
				//Success message
				JOptionPane.showMessageDialog(createFlightForm, "Đã xóa thành công.");
			}
		});
		
		//Init update button of the transition
		createFlightForm
			.getTable()
			.getActionButtons()
			.get(TransitionCRUDTableView.UPDATE_BUTTON_INDEX)
			.addActionListener(event -> {
				Transition model = createFlightForm.getTable().getSelectedTransition();
		
				if (null != model) {
					createFlightForm.getTransitionForm().loadModel(model);
					createFlightForm.getTransitionForm().setVisible(true);
				}
		});
		
		//Init submit button of the flight form
		createFlightForm.getSubmitButton().addActionListener(event -> {
			
			//Validate the form
			if (createFlightForm.areThereAnyEmptyStarField()) {
				createFlightForm.setError(FlightForm.EMPTY_FIELD_ERROR);
				return;
			}
			
			if (createFlightForm.areTheseAirportMatch()) {
				createFlightForm.setError(FlightForm.AIRPORT_MATCH_ERROR);
				return;
			}
			
			//Create the flight
			Flight flight = createFlightForm.submit();
			flight = flightService.createFlight(flight);
			
			//Update the table view
			initFlightRead(controllerView);
			
			//Update the flight view for customer
			FlightFeatureView featureView = (FlightFeatureView) mainFrame
					.getFeatureViews()
					.get(CustomerMainFrame.FLIGHT_FEATURE_INDEX);
			
			FlightTabbedControllerView customerControllerView = featureView.getTabbedControllerView();
			initFlightListRead(customerControllerView);
			
			//Close the form
			createFlightForm.setError(AirportForm.NO_ERROR);
			createFlightForm.close();
			
		});
	}
	
	protected void initFlightRead(FlightManagerTabbedControllerView controllerView) {
		List<Flight> flights = new ArrayList<>(flightService.findAllFlights());
		FlightCRUDTableView table = controllerView.getFlightCRUDTableView();
		table.setFlights(flights);
		table.update();
	}
	
	protected void initFlightUpdate(FlightManagerTabbedControllerView controllerView) {
		FlightCRUDTableView table = controllerView.getFlightCRUDTableView();
		
		//Init update button
		table.getActionButtons().get(FlightCRUDTableView.UPDATE_BUTTON_INDEX).addActionListener(event -> {
			

			Flight flight = table.getSelectedFlight();
			if (null == flight) {
				return;
			}
			
			//Load data to popout detail information
			FlightDetail detail = flightService.findFlightDetailByFlight(flight);
			List<Transition> transitions = transitionService.findTransitionForFlight(flight);
			flight.setDetail(detail);
			flight.setTransitions(transitions);
			
			//Load data for the form
			List<Airport>  airports = airportService.findAllAirports();
			updateFlightForm.setAirports(airports);
			updateFlightForm.setModel(flight);
			
			updateFlightForm.setVisible(true);
			
		});
		
		//Init submit button of the flight form
		updateFlightForm.getSubmitButton().addActionListener(event -> {
					
			//Validate the form
			if (updateFlightForm.areThereAnyEmptyStarField()) {
				updateFlightForm.setError(FlightForm.EMPTY_FIELD_ERROR);
				return;
			}
					
			if (updateFlightForm.areTheseAirportMatch()) {
				updateFlightForm.setError(FlightForm.AIRPORT_MATCH_ERROR);
				return;
			}
					
			//Create the flight
			Flight flight = updateFlightForm.submit();
			flight = flightService.updateFlight(flight);
					
			//Update the table view
			initFlightRead(controllerView);
					
			//Update the flight view for customer
			FlightFeatureView featureView = (FlightFeatureView) mainFrame
					.getFeatureViews()
					.get(CustomerMainFrame.FLIGHT_FEATURE_INDEX);
					
			FlightTabbedControllerView customerControllerView = featureView.getTabbedControllerView();
			initFlightListRead(customerControllerView);
					
			//Close the form
			updateFlightForm.setError(AirportForm.NO_ERROR);
			updateFlightForm.close();
					
		});
	}
	
	protected void initFlightDelete(FlightManagerTabbedControllerView controllerView) {
		FlightCRUDDetailView detail = controllerView.getFlightCRUDDetailView();
		FlightCRUDTableView table = controllerView.getFlightCRUDTableView();
		
		//Init "Delete airport" button
		detail.getButtons().get(CRUDDetailView.DELETE_BUTTON_INDEX).addActionListener(event -> {
			
			int input = JOptionPane.showConfirmDialog(mainFrame,
	        		"Bạn có chắc chắn muốn xóa ?\nDữ liệu bị xóa sẽ không thể khôi phục lại được.",
	        		"Xóa",
	        		JOptionPane.YES_NO_OPTION);
			
			if (JOptionPane.YES_OPTION == input) {
				List<Flight> flights = table.getSelectedFlights();
				
				//Get the id from the airport
				List<Integer> ids = flights
						.stream()
						.map(flight -> flight.getId())
						.collect(Collectors.toList());
				
				//Delete thoose airports
				flightService.deleteFlights(ids);
				
				//Update the table view
				initFlightRead(controllerView);
				
				//Success message
				JOptionPane.showMessageDialog(null, "Đã xóa thành công.");
			}
			
		});
	}
	
	
	protected void initReportFeatures() {
		initReportByMonthFeature();
		initReportByYearFeature();
	}
	
	protected void initReportByMonthFeature() {
		//TODO:
	}
	
	protected void initReportByYearFeature() {
		//TODO:
	}
	
}
