package com.tkpm.view.frame;

import com.tkpm.view.feature_view.BaseFeatureView;
import com.tkpm.view.widget.Category;

public class ManagerMainFrame extends BaseMainFrame {

	//public static final int MANAGER_FEATURE_INDEX = 1;
	
	protected BaseFeatureView featureView;
	protected BaseFeatureView featureView0;
	
	@Override
	protected void initValueForComponents() {
		
		featureView = new BaseFeatureView();
		featureView0 = new BaseFeatureView();
		
		
		this.categories.add(new Category("Danh sách chuyến bay"));
		this.categories.add(new Category("Quản lý chuyến bay"));
		
		this.featureViews.add(featureView);
		this.featureViews.add(featureView0);
	}
	
	public ManagerMainFrame() {
		super();
		
	}
	
}
