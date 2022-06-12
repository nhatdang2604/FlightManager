package com.tkpm.view.feature_view.detail_view;

@SuppressWarnings("serial")
public class AirportCRUDDetailView extends CRUDDetailView {
	
	public static final String TITLE = "Sân bay";
	
	private static final String[] FIELD_NAMES = {
			"Mã sân bay",
			"Tên sân bay",
	};
	
	public AirportCRUDDetailView() {
		super(TITLE, FIELD_NAMES);
	}
	
}