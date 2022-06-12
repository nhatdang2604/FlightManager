package com.tkpm.controller;

import com.tkpm.view.frame.BaseMainFrame;

public class ManagerController extends CustomerController {

	public ManagerController(BaseMainFrame mainFrame) {
		super(mainFrame);
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
		//TODO:
	};
	
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
