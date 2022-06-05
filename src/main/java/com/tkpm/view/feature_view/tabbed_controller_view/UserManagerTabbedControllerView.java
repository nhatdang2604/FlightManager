package com.tkpm.view.feature_view.tabbed_controller_view;

import java.util.List;

import javax.swing.JPanel;

import com.tkpm.view.action.ChangeTabListener;
import com.tkpm.view.feature_view.detail_view.AirportDetailView;
import com.tkpm.view.feature_view.detail_view.UserCRUDDetailView;
import com.tkpm.view.feature_view.table.AirportTableView;
import com.tkpm.view.feature_view.table.UserCRUDTableView;


public class UserManagerTabbedControllerView extends BaseTabbedControllerView {

	//Tables
	private UserCRUDTableView userCRUDTableView;

	//Detail views
	private UserCRUDDetailView userCRUDDetailView;
	
	public UserManagerTabbedControllerView(List<JPanel> backgroundParts) {
		super(backgroundParts);
		
		//Init for airport CRUD feature
		userCRUDTableView = new UserCRUDTableView();
		userCRUDDetailView = new UserCRUDDetailView();
		
		//Add screen for user CRUD tab
		this.addTable(userCRUDTableView, "Tài khoản");
		this.addDetail(userCRUDDetailView);
		this.addHeader(new JPanel());	//dummy header
		
		//Change detail panel and header panel while change tab
		tabbedPanel.addMouseListener(new ChangeTabListener(this, backgroundParts));
			
		initOption();
	}

	public UserCRUDTableView getUserCRUDTableView() {return userCRUDTableView;}
	public UserCRUDDetailView getUserCRUDDetailView() {return userCRUDDetailView;}
	
}
