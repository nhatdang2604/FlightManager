package com.tkpm.view.feature_view.detail_view;

@SuppressWarnings("serial")
public class FlightCRUDDetailView extends CRUDDetailView {
	
	private static final String[] FIELD_NAMES = {
			"Thời gian bay",	
			"Số lượng ghế hạng 1",
			"Số lượng ghế hạng 2",
	};
	
	public static final String TITLE = "Chuyến bay";
	
	public FlightCRUDDetailView() {
		super(TITLE, FIELD_NAMES);
	}
	

}