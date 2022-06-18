package com.tkpm.view.component;

import com.tkpm.view.feature_view.detail_view.AirportCRUDDetailView;
import com.tkpm.view.feature_view.detail_view.FlightCRUDDetailView;
import com.tkpm.view.feature_view.table.AirportCRUDTableView;
import com.tkpm.view.feature_view.table.FlightCRUDTableView;

@SuppressWarnings("serial")
public class FlightManagerForm extends BaseForm {

	/**
	 * Create the panel.
	 */
	public FlightManagerForm() {
		super();
	}
	
	@Override
	protected void initComponents() {
		super.initComponents();
		tabList.add(new ListPane(new AirportCRUDTableView()));
		tabList.add(new ListPane(new FlightCRUDTableView()));
		tabName.add("Sân bay");
		tabName.add("Chuyến bay");
		detailPane.add(new AirportCRUDDetailView());
		detailPane.add(new FlightCRUDDetailView());
	}

}
