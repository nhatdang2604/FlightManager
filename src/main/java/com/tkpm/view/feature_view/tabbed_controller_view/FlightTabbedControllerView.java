package com.tkpm.view.feature_view.tabbed_controller_view;

import java.util.List;

import javax.swing.JPanel;

import com.tkpm.view.action.ChangeTabListener;
import com.tkpm.view.feature_view.detail_view.FlightListDetailView;
import com.tkpm.view.feature_view.table.FlightListTableView;


public class FlightTabbedControllerView extends BaseTabbedControllerView {

	//Tables
	private FlightListTableView flightListTableView;
	
	//Detail views
	private FlightListDetailView flightListDetailView;
	
	public FlightTabbedControllerView(List<JPanel> backgroundParts) {
		super(backgroundParts);
		
		//Init for flight list tabbed
		flightListTableView = new FlightListTableView();
		flightListDetailView = new FlightListDetailView();
		
		//Add screen for flight list
		this.addTable(flightListTableView, "Danh sách chuyến bay");
		this.addDetail(flightListDetailView);
		this.addHeader(new JPanel());	//dummy header
		
		//Change detail panel and header panel while change tab
		tabbedPanel.addMouseListener(new ChangeTabListener(this, backgroundParts));
			
		initOption();
	}

	public FlightListTableView getFlightListTableView() {return flightListTableView;}
	public FlightListDetailView getFlightListDetailView() {return flightListDetailView;}
}
