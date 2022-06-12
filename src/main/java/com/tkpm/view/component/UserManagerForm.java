package com.tkpm.view.component;

import com.tkpm.view.feature_view.detail_view.UserCRUDDetailView;
import com.tkpm.view.feature_view.table.UserCRUDTableView;

public class UserManagerForm extends BaseForm {

	public UserManagerForm() {
		super();
	}
	
	@Override
	protected void initComponents() {
		super.initComponents();
		tabList.add(new ListPane(new UserCRUDTableView()));
		tabName.add("Tài khoản");
		detailPane.add(new UserCRUDDetailView());
	}
}
