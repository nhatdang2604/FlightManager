package com.tkpm.view.feature_view.table;

import javax.swing.JPanel;
import javax.swing.JTable;

public interface TableViewBehaviour {

	public JTable getTable();
	public JPanel getHeaderView();
	public JPanel getDetailView();
}
