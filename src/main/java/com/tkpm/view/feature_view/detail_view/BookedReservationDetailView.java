package com.tkpm.view.feature_view.detail_view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class BookedReservationDetailView extends BaseReadOnlyDetailView {
	
	public static final int BOOK_BUTTON_INDEX = 0;
	
	private JButton bookButton;
	
	private static final String[] FIELD_NAMES = {
			"Mã chuyến bay",
			"Thời gian",	
			"Sân bay đi",
			"Sân bay đến",
			"Ngày đặt vé",
			"Giá"
	};

	
	public BookedReservationDetailView() {
		super("Vé đã đặt",FIELD_NAMES);
	}
	
	public JButton getBookButton() {
		return bookButton;
	}

}