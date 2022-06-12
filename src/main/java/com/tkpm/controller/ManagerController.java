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
