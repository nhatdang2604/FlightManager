package com.tkpm.view.frame;

import java.util.ArrayList;

import com.tkpm.view.component.FlightFeatureForm;
import com.tkpm.view.component.Menu;
import com.tkpm.view.component.ModelMenu;
import com.tkpm.view.component.ModelMenu.MenuType;
import com.tkpm.view.feature_view.BaseFeatureView;
import com.tkpm.view.feature_view.FlightFeatureView;
import com.tkpm.view.feature_view.detail_view.FlightListDetailView;
import com.tkpm.view.widget.Category;

//public class CustomerMainFrame extends BaseMainFrame {
//
//	//public static final int CUSTOMER_FEATURE_INDEX = 0;
//	
//	protected BaseFeatureView featureView;
//	
//	@Override
//	protected void initValueForComponents() {
//		
//		featureView = new FlightFeatureView();
//		this.categories.add(new Category("Danh sách chuyến bay"));
//		
//		this.featureViews.add(featureView);
//	}
//	
//	public CustomerMainFrame() {
//		super();
//		
//	}
//	
//}

public class CustomerMainFrame extends BaseMain {
	
	@Override
	protected void initValueForComponents() {
		menuNames = new ArrayList<String>();
		menuNames.add("Danh sách chuyến bay");
		menuNames.add("Quản lý chuyến bay");
		menu = new Menu(menuNames);
		
		listPanels = new ArrayList<>();
		listPanels.add(new FlightFeatureForm());
		
		detailPanels = new ArrayList<>();
		detailPanels.add(new FlightListDetailView());
	}
	
	public CustomerMainFrame() {
		super();
	}
	
	
	
}