package com.tkpm.view.component;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import com.tkpm.view.feature_view.table.FlightListTableView;

import java.awt.Color;
import javax.swing.ScrollPaneConstants;

public class FlightListPane extends JPanel {

	private FlightListTableView flightTable;
	/**
	 * Create the panel.
	 */
	public FlightListPane() {
		setBackground(Color.WHITE);
		setBorder(null);
		setLayout(new BorderLayout(0, 0));
		
		flightTable = new FlightListTableView();
		flightTable.setOpaque(false);
		flightTable.setBackground(Color.WHITE);
		
		JScrollPane flightTablePane = new JScrollPane(flightTable);
		flightTablePane.setBackground(Color.WHITE);
		flightTablePane.setVerticalScrollBar(new ScrollBarCustom());
		flightTablePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		flightTablePane.setOpaque(false);
		add(flightTablePane, BorderLayout.CENTER);

	}

}
