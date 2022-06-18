package com.tkpm.view.feature_view.tabbed_controller_view;

import java.util.List;

import javax.swing.JPanel;

import com.tkpm.view.action.ChangeTabListener;
import com.tkpm.view.feature_view.detail_view.BookedReservationDetailView;
import com.tkpm.view.feature_view.detail_view.FlightListDetailView;
import com.tkpm.view.feature_view.table.BookedReservationTableView;
import com.tkpm.view.feature_view.table.FlightListTableView;


public class FlightTabbedControllerView extends BaseTabbedControllerView {

	//Tables
	private FlightListTableView flightListTableView;
	private BookedReservationTableView bookedReservationTableView;
	
	//Detail views
	private FlightListDetailView flightListDetailView;
	private BookedReservationDetailView bookedReservationDetailView;
	
	public FlightTabbedControllerView(List<JPanel> backgroundParts) {
		super(backgroundParts);
		
		//Init for flight list tabbed
		flightListTableView = new FlightListTableView();
		flightListDetailView = new FlightListDetailView();
		
		//Init for booked reservation list tabbed
		bookedReservationTableView = new BookedReservationTableView();
		bookedReservationDetailView = new BookedReservationDetailView();
		
		//Add screen for flight list
		this.addCenterAsTable(flightListTableView, "Danh sách chuyến bay");
		this.addDetail(flightListDetailView);
		this.addHeader(new JPanel());	//dummy header
		
		//Change detail panel and header panel while change tab
		this.addCenterAsTable(bookedReservationTableView , "Vé đã đặt");
		this.addDetail(bookedReservationDetailView);
		this.addHeader(new JPanel());	//dummy header
		
		//Change detail panel and header panel while change tab
		tabbedPanel.addMouseListener(new ChangeTabListener(this, backgroundParts));			
		
		initOption();
	}

	public FlightListTableView getFlightListTableView() {return flightListTableView;}
	public FlightListDetailView getFlightListDetailView() {return flightListDetailView;}
	public BookedReservationTableView getBookedReservationTableView() {return bookedReservationTableView;};
	public BookedReservationDetailView getBookedReservationDetailView() {return bookedReservationDetailView;}
}
