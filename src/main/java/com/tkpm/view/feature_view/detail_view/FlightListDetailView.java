package com.tkpm.view.feature_view.detail_view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class FlightListDetailView extends BaseDetailView {
	
	public static final int BOOK_BUTTON_INDEX = 0;
	
	private JButton bookButton;
	
	private static final String[] FIELD_NAMES = {
			"Tình trạng ghế hạng 1",	//Còn/Hết
			"Tình trạng ghế hạng 2",	//Còn/Hết
	};
	
	protected void makeButtons() {
		
		//Create buttons
		bookButton = new JButton("Đặt vé");
		
		//Add Buttons to detail panel
		paddingPanels.get(2).add(bookButton);
		
	}
	
	public FlightListDetailView() {
		super("Vé chuyến bay", FIELD_NAMES);
		makeButtons();	
	}
	
	public JButton getBookButton() {
		return bookButton;
	}

}