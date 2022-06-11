package com.tkpm.view.component;

import com.tkpm.view.feature_view.detail_view.FlightListDetailView;
import com.tkpm.view.feature_view.table.FlightListTableView;

public class FlightFeatureForm extends BaseForm {

	/**
	 * Create the panel.
	 */
	public FlightFeatureForm() {
		super();
	}
	
	@Override
	protected void initComponents() {
		super.initComponents();
		tabList.add(new ListPane(new FlightListTableView()));
		tabName.add("Danh sách chuyến bay");
		detailPane.add(new FlightListDetailView());
	}

}
