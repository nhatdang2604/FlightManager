package com.tkpm.view.feature_view.detail_view;

import javax.swing.JButton;

import com.tkpm.view.component.FilledButton;
import java.awt.Color;

@SuppressWarnings("serial")
public class FlightListDetailView extends BaseReadOnlyDetailView {
	
	public static final int BOOK_BUTTON_INDEX = 0;
	
	private FilledButton bookButton;
	
	private static final String[] FIELD_NAMES = {
			"Tình trạng ghế hạng 1",	// Còn/Hết
			"Tình trạng ghế hạng 2",	// Còn/Hết
	};
	
	protected void makeButtons() {
		
		//Create buttons
		bookButton = new FilledButton("Đặt vé");
		bookButton.setBorderPainted(false);
		
		//Add Buttons to detail panel
		paddingPanels.get(2).add(bookButton);
		
	}
	
	public FlightListDetailView() {
		super("Vé chuyến bay", FIELD_NAMES);
		setBackground(Color.WHITE);
		makeButtons();	
	}
	
	public JButton getBookButton() {
		return bookButton;
	}

}