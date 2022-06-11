package com.tkpm.view.component;

import javax.swing.JPanel;

import com.tkpm.view.frame.BaseMain;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.util.ArrayList;

public class FlightFeatureForm extends JPanel {
	
	private Header header;
	private Tabbed tabPane;
	private ArrayList<JPanel> tabList;

	/**
	 * Create the panel.
	 */
	public FlightFeatureForm() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 10));
		
		header = new Header();
		header.getMenuButton().setFocusPainted(false);
		header.getMenuButton().setBorder(null);
		add(header, BorderLayout.NORTH);
		
		tabPane = new Tabbed();
		add(tabPane, BorderLayout.CENTER);
		
		tabList = new ArrayList<>();
		tabList.add(new FlightListPane());
		for (JPanel jPanel : tabList) {
			jPanel.setBackground(Color.WHITE);
			tabPane.addTab("Danh sach chuyen bay", jPanel);
		}
		
		header.getMenuButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BaseMain.showMenu();
			}
		});
	}

}
