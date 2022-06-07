package com.tkpm.view.feature_view.tabbed_controller_view;

import java.util.List;

import javax.swing.JPanel;

import com.tkpm.view.action.ChangeTabListener;
import com.tkpm.view.feature_view.detail_view.ReportByMonthDetailView;
import com.tkpm.view.feature_view.detail_view.ReportByYearDetailView;
import com.tkpm.view.feature_view.header_view.ReportByMonthHeader;
import com.tkpm.view.feature_view.header_view.ReportByYearHeader;
import com.tkpm.view.feature_view.table.ReportByMonthTable;
import com.tkpm.view.feature_view.table.ReportByYearTable;


public class ReportTabbedControllerView extends BaseTabbedControllerView {

	//Header
	private ReportByYearHeader reportByYearHeader;
	private ReportByMonthHeader reportByMonthHeader;
	
	//Tables
	private ReportByYearTable reportByYearTable;
	private ReportByMonthTable reportByMonthTable;
	
	//Detail views
	private ReportByYearDetailView reportByYearDetailView;
	private ReportByMonthDetailView reportByMonthDetailView;
	
	public ReportTabbedControllerView(List<JPanel> backgroundParts) {
		super(backgroundParts);
		
		//Init for report by year feature
		reportByYearHeader = new ReportByYearHeader();
		reportByYearTable = new ReportByYearTable();
		reportByYearDetailView = new ReportByYearDetailView();
		
		//Init for report by month feature
		reportByMonthHeader = new ReportByMonthHeader();
		reportByMonthTable = new ReportByMonthTable();
		reportByMonthDetailView = new ReportByMonthDetailView();
		
		//Add screen for report by month tab
		this.addCenterAsTable(reportByYearTable, "Báo cáo theo năm");
		this.addDetail(reportByYearDetailView);
		this.addHeader(reportByYearHeader);
		
		//Add screen for report by year tab
		this.addCenterAsTable(reportByMonthTable, "Báo cáo theo tháng");
		this.addDetail(reportByMonthDetailView);
		this.addHeader(reportByMonthHeader);
		
		//Change detail panel and header panel while change tab
		tabbedPanel.addMouseListener(new ChangeTabListener(this, backgroundParts));
			
		initOption();
	}

	public ReportByYearTable getReportByYearTable() {return reportByYearTable;}
	public ReportByYearHeader getReportByYearHeader() {return reportByYearHeader;}
	public ReportByYearDetailView getReportByYearDetailView() {return reportByYearDetailView;}

	public ReportByMonthHeader getReportByMonthHeader() {return reportByMonthHeader;}
	public ReportByMonthTable getReportByMonthTable() {return reportByMonthTable;}
	public ReportByMonthDetailView getReportByMonthDetailView() {return reportByMonthDetailView;}
	
}
