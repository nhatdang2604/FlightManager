package com.tkpm.view.component;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

public class ListPane extends JPanel {
	
	/**
	 * Create the panel.
	 */
	public ListPane(JTable table) {
		setBackground(Color.WHITE);
		setBorder(null);
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane flightTablePane = new JScrollPane(table);
		flightTablePane.setBackground(Color.WHITE);
		flightTablePane.setVerticalScrollBar(new ScrollBarCustom());
		flightTablePane.setOpaque(false);
		add(flightTablePane, BorderLayout.CENTER);

	}

}
