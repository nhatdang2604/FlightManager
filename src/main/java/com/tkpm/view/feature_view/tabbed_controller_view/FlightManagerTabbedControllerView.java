package com.tkpm.view.feature_view.tabbed_controller_view;

import java.util.List;

import javax.swing.JPanel;

import com.tkpm.view.action.ChangeTabListener;
import com.tkpm.view.feature_view.detail_view.AirportDetailView;
import com.tkpm.view.feature_view.detail_view.FlightListDetailView;
import com.tkpm.view.feature_view.table.AirportTableView;
import com.tkpm.view.feature_view.table.FlightListTableView;


public class FlightManagerTabbedControllerView extends BaseTabbedControllerView {

	//Tables
	private AirportTableView airportTableView;
	
	//Detail views
	private AirportDetailView airportDetailView;
	
	public FlightManagerTabbedControllerView(List<JPanel> backgroundParts) {
		super(backgroundParts);
		
		//Init for flight list tabbed
		airportTableView = new AirportTableView();
		airportDetailView = new AirportDetailView();
		
		//Add screen for flight list
		this.addTable(airportTableView, "Sân bay");
		this.addDetail(airportDetailView);
		this.addHeader(new JPanel());	//dummy header
		
		//Change detail panel and header panel while change tab
		tabbedPanel.addMouseListener(new ChangeTabListener(this, backgroundParts));
			
		initOption();
	}

	public AirportTableView getAirportTableView() {return airportTableView;}
	public AirportDetailView getAirportDetailView() {return airportDetailView;}
	
}
