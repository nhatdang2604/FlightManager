package com.tkpm.view.frame;

import com.tkpm.view.feature_view.BaseFeatureView;
import com.tkpm.view.feature_view.FlightFeatureView;
import com.tkpm.view.widget.Category;

public class AdminMainFrame extends BaseMainFrame {

	//public static final int ADMIN_FEATURE_INDEX = 1;
	
	protected BaseFeatureView featureView;
	protected BaseFeatureView featureView0;
	protected BaseFeatureView featureView1;
	
	@Override
	protected void initValueForComponents() {
		
		featureView = new FlightFeatureView();
		featureView0 = new BaseFeatureView();
		featureView1 = new BaseFeatureView();
		
		
		this.categories.add(new Category("Danh sách chuyến bay"));
		this.categories.add(new Category("Quản lý chuyến bay"));
		this.categories.add(new Category("Quản lý tài khoản"));
		
		this.featureViews.add(featureView);
		this.featureViews.add(featureView0);
		this.featureViews.add(featureView1);

	}
	
	public AdminMainFrame() {
		super();
		
	}
	
}
