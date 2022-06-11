package com.tkpm.view.frame;

import java.util.ArrayList;

import com.tkpm.view.component.FlightFeatureForm;
import com.tkpm.view.component.FlightManagerForm;
import com.tkpm.view.component.Menu;
import com.tkpm.view.component.ReportManagerForm;
import com.tkpm.view.component.UserManagerForm;
import com.tkpm.view.feature_view.BaseFeatureView;
import com.tkpm.view.feature_view.FlightFeatureView;
import com.tkpm.view.feature_view.FlightManagerFeatureView;
import com.tkpm.view.feature_view.ReportFeatureView;
import com.tkpm.view.feature_view.UserManagerFeatureView;
import com.tkpm.view.feature_view.detail_view.AirportCRUDDetailView;
import com.tkpm.view.feature_view.detail_view.FlightListDetailView;
import com.tkpm.view.widget.Category;

//public class AdminMainFrame extends BaseMainFrame {
//
//	//public static final int ADMIN_FEATURE_INDEX = 1;
//	
//	protected BaseFeatureView featureView;
//	protected BaseFeatureView featureView0;
//	protected BaseFeatureView featureView1;
//	protected BaseFeatureView featureView2;
//	
//	@Override
//	protected void initValueForComponents() {
//		
//		featureView = new FlightFeatureView();
//		featureView0 = new FlightManagerFeatureView();
//		featureView1 = new ReportFeatureView();
//		featureView2 = new UserManagerFeatureView();
//		
//		this.categories.add(new Category("Danh sách chuyến bay"));
//		this.categories.add(new Category("Quản lý chuyến bay"));
//		this.categories.add(new Category("Lập báo cáo chi tiết"));
//		this.categories.add(new Category("Quản lý tài khoản"));
//		
//		this.featureViews.add(featureView);
//		this.featureViews.add(featureView0);
//		this.featureViews.add(featureView1);
//		this.featureViews.add(featureView2);
//
//	}
//	
//	public AdminMainFrame() {
//		super();
//		
//	}
//	
//}

public class AdminMainFrame extends BaseMain {
	
	@Override
	protected void initValueForComponents() {
		menuNames = new ArrayList<String>();
		menuNames.add("Danh sách chuyến bay");
		menuNames.add("Quản lý chuyến bay");
		menuNames.add("Quản lý báo cáo");
		menuNames.add("Quản lý tài khoản");
		menu = new Menu(menuNames);
		
		listPanels = new ArrayList<>();
		listPanels.add(new FlightFeatureForm());
		listPanels.add(new FlightManagerForm());
		listPanels.add(new ReportManagerForm());
		listPanels.add(new UserManagerForm());
	}
	
	public AdminMainFrame() {
		super();
	}
}