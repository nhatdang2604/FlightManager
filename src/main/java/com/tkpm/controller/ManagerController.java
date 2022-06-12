package com.tkpm.controller;

import java.util.ArrayList;
import java.util.List;

import com.tkpm.entities.Airport;
import com.tkpm.service.AirportService;
import com.tkpm.view.feature_view.FlightManagerFeatureView;
import com.tkpm.view.feature_view.detail_view.AirportCRUDDetailView;
import com.tkpm.view.feature_view.detail_view.CRUDDetailView;
import com.tkpm.view.feature_view.tabbed_controller_view.FlightManagerTabbedControllerView;
import com.tkpm.view.feature_view.table.AirportCRUDTableView;
import com.tkpm.view.frame.BaseMainFrame;
import com.tkpm.view.frame.ManagerMainFrame;
import com.tkpm.view.frame.form.AirportForm;

public class ManagerController extends CustomerController {

	//Forms
	protected AirportForm airportForm;
	
	//Services
	protected AirportService airportService;
	
	public ManagerController(BaseMainFrame mainFrame) {
		super(mainFrame);
		airportService = AirportService.INSTANCE;
		airportForm = new AirportForm(mainFrame);
	}

	@Override
	protected void initFeatures() {
		
		//Features from Customer role
		super.initFeatures();
		
		initFlightManagerFeatures();
		initReportFeatures();
	}
	
	protected void initFlightManagerFeatures() {
		initAirportCRUDFeature();
		initFlightCRUDFeature();
	}
	
	protected void initAirportCRUDFeature() {
		FlightManagerFeatureView featureView = (FlightManagerFeatureView) mainFrame
				.getFeatureViews()
				.get(ManagerMainFrame.FLIGHT_MANAGER_FEATURE_INDEX);
		
		FlightManagerTabbedControllerView controllerView = featureView.getTabbedControllerView();
		
		initAirportCreate(controllerView);
		initAirportRead(controllerView);
	};
	
	protected void initAirportCreate(FlightManagerTabbedControllerView controllerView) {
		AirportCRUDDetailView detail = controllerView.getAirporCRUDDetailView();
		
		//Init "Create airport" button
		detail.getButtons().get(CRUDDetailView.CREATE_BUTTON_INDEX).addActionListener(event -> {
			airportForm.setTitle("Tạo chuyến bay");
			airportForm.open();
		});
		
		//Init submit button of the airport form
		airportForm.getSubmitButton().addActionListener(event -> {
			
			//Validate the form
			if (airportForm.areThereAnyEmptyStarField()) {
				airportForm.setError(AirportForm.EMPTY_STAR_FIELD_ERROR);
				return;
			}
			
			//Check if there is an airport with the same name existed
			Airport airport = airportForm.submit();
			Airport testAirport = airportService.findAirportByName(airport.getName());
			if (null != testAirport) {
				airportForm.setError(AirportForm.NAME_EXISTED_FIELD_ERROR);
				return;
			}
			
			//validate success case
			airport = airportService.createAirport(airport);
			
			//Update the table view
			initAirportRead(controllerView);
			
			//Close the form
			airportForm.close();
			
		});
	}
	
	protected void initAirportRead(FlightManagerTabbedControllerView controllerView) {
		List<Airport> airports = airportService.findAllAirports();
		AirportCRUDTableView table = controllerView.getAirportCRUDTableView();
		table.setAirports(airports);
		table.update();
	}
	
	protected void initAirportUpdate() {
		
	}
	
	protected void initAirportDelete() {
		
	}
	
	protected void initFlightCRUDFeature() {
		//TODO:
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
