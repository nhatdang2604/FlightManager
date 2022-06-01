package com.tkpm.view.frame;

import com.tkpm.view.feature_view.BaseFeatureView;
import com.tkpm.view.widget.Category;

public class CustomerMainFrame extends BaseMainFrame {

	//public static final int CUSTOMER_FEATURE_INDEX = 0;
	
	protected BaseFeatureView featureView;
	
	@Override
	protected void initValueForComponents() {
		
		featureView = new BaseFeatureView();
		
		//No need, because we only have 1 option in Customer Role
		this.categories.add(new Category("Danh sách chuyến bay"));
		
		this.featureViews.add(featureView);	
	}
	
	public CustomerMainFrame() {
		super();
		
	}
	
}
