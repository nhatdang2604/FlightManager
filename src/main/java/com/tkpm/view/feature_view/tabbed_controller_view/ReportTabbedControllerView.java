package com.tkpm.view.feature_view.tabbed_controller_view;

import java.util.List;

import javax.swing.JPanel;

import com.tkpm.view.action.ChangeTabListener;
import com.tkpm.view.feature_view.detail_view.ReportByYearDetailView;
import com.tkpm.view.feature_view.detail_view.UserCRUDDetailView;
import com.tkpm.view.feature_view.report.ReportByYearView;
import com.tkpm.view.feature_view.table.UserCRUDTableView;


public class ReportTabbedControllerView extends BaseTabbedControllerView {

	//Tables
	private ReportByYearView reportByYearView;

	//Detail views
	private ReportByYearDetailView reportByYearDetailView;
	
	public ReportTabbedControllerView(List<JPanel> backgroundParts) {
		super(backgroundParts);
		
		//Init for report by year feature
		reportByYearView = new ReportByYearView();
		reportByYearDetailView = new ReportByYearDetailView();
		
		//Add screen for user CRUD tab
		this.addCenter(reportByYearView, "Báo cáo theo năm");
		this.addDetail(reportByYearDetailView);
		this.addHeader(new JPanel());	//dummy header
		
		//Change detail panel and header panel while change tab
		tabbedPanel.addMouseListener(new ChangeTabListener(this, backgroundParts));
			
		initOption();
	}

	public ReportByYearView getReportByYearView() {return reportByYearView;}
	public ReportByYearDetailView getReportByYearDetailView() {return reportByYearDetailView;}
	
}
