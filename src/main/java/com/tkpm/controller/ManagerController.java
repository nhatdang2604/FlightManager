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

	protected AirportService airportService;
	
	public ManagerController(BaseMainFrame mainFrame) {
		super(mainFrame);
		airportService = AirportService.INSTANCE;
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
		
		detail.getButtons().get(CRUDDetailView.CREATE_BUTTON_INDEX).addActionListener(event -> {
			AirportForm form = new AirportForm(mainFrame);
			form.setTitle("Tạo chuyến bay");
			initAirportCreateForm(form, controllerView);	
		});
	}

	protected void initAirportCreateForm(AirportForm form, FlightManagerTabbedControllerView controllerView) {
		form.getSubmitButton().addActionListener(event -> {
			
			//Validate the form
			if (form.areThereAnyEmptyStarField()) {
				form.setError(AirportForm.EMPTY_STAR_FIELD_ERROR);
				return;
			}
			
			//Check if there is an airport with the same name existed
			Airport airport = form.submit();
			Airport testAirport = airportService.findAirportByName(airport.getName());
			if (null != testAirport) {
				form.setError(AirportForm.NAME_EXISTED_FIELD_ERROR);
				return;
			}
			
			//validate success case
			airport = airportService.createAirport(airport);
			
			//Update the table view
			initAirportRead(controllerView);
			
			//Close the form
			form.close();
			
		});
		
	}
	
	protected void initAirportRead(FlightManagerTabbedControllerView controllerView) {
		List<Airport> airports = new ArrayList<>(airportService.findAllAirports());
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
