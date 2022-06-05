package com.tkpm.view.feature_view.tabbed_controller_view;

import java.util.List;

import javax.swing.JPanel;

import com.tkpm.view.action.ChangeTabListener;
import com.tkpm.view.feature_view.detail_view.AirportDetailView;
import com.tkpm.view.feature_view.detail_view.FlightCRUDDetailView;
import com.tkpm.view.feature_view.table.AirportTableView;
import com.tkpm.view.feature_view.table.FlightCRUDTableView;


public class FlightManagerTabbedControllerView extends BaseTabbedControllerView {

	//Tables
	private AirportTableView airportTableView;
	private FlightCRUDTableView flightCRUDTableView;
	
	//Detail views
	private AirportDetailView airportDetailView;
	private FlightCRUDDetailView flightCRUDDetailView;
	
	public FlightManagerTabbedControllerView(List<JPanel> backgroundParts) {
		super(backgroundParts);
		
		//Init for airport CRUD feature
		airportTableView = new AirportTableView();
		airportDetailView = new AirportDetailView();
		
		//Init for flight CRUD feature
		flightCRUDTableView = new FlightCRUDTableView();
		flightCRUDDetailView = new FlightCRUDDetailView();
		
		//Add screen for airport CRUD tab
		this.addTable(airportTableView, "Sân bay");
		this.addDetail(airportDetailView);
		this.addHeader(new JPanel());	//dummy header
		
		//Add screen for flight CRUD tab
		this.addTable(flightCRUDTableView, "Chuyến bay");
		this.addDetail(flightCRUDDetailView);
		this.addHeader(new JPanel());	//dummy header
		
		//Change detail panel and header panel while change tab
		tabbedPanel.addMouseListener(new ChangeTabListener(this, backgroundParts));
			
		initOption();
	}

	public AirportTableView getAirportTableView() {return airportTableView;}
	public AirportDetailView getAirportDetailView() {return airportDetailView;}
	
}
