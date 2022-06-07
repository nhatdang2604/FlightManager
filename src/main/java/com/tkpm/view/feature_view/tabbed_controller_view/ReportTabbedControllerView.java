package com.tkpm.view.feature_view.tabbed_controller_view;

import java.util.List;

import javax.swing.JPanel;

import com.tkpm.view.action.ChangeTabListener;
import com.tkpm.view.feature_view.detail_view.ReportByYearDetailView;
import com.tkpm.view.feature_view.header_view.ReportByYearHeader;
import com.tkpm.view.feature_view.table.ReportByYearTable;


public class ReportTabbedControllerView extends BaseTabbedControllerView {

	//Header
	private ReportByYearHeader reportByYearHeader;
	
	//Tables
	private ReportByYearTable reportByYearTable;

	//Detail views
	private ReportByYearDetailView reportByYearDetailView;
	
	public ReportTabbedControllerView(List<JPanel> backgroundParts) {
		super(backgroundParts);
		
		//Init for report by year feature
		reportByYearHeader = new ReportByYearHeader();
		reportByYearTable = new ReportByYearTable();
		reportByYearDetailView = new ReportByYearDetailView();
		
		//Add screen for user CRUD tab
		this.addCenterAsTable(reportByYearTable, "Báo cáo theo năm");
		this.addDetail(reportByYearDetailView);
		this.addHeader(reportByYearHeader);
		
		//Change detail panel and header panel while change tab
		tabbedPanel.addMouseListener(new ChangeTabListener(this, backgroundParts));
			
		initOption();
	}

	public ReportByYearTable getReportByYearTable() {return reportByYearTable;}
	public ReportByYearHeader getReportByYearHeader() {return reportByYearHeader;}
	public ReportByYearDetailView getReportByYearDetailView() {return reportByYearDetailView;}
	
}
