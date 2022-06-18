package com.tkpm.view.component;

import com.tkpm.view.feature_view.detail_view.ReportByMonthDetailView;
import com.tkpm.view.feature_view.detail_view.ReportByYearDetailView;
import com.tkpm.view.feature_view.table.ReportByMonthTable;
import com.tkpm.view.feature_view.table.ReportByYearTable;

@SuppressWarnings("serial")
public class ReportManagerForm extends BaseForm {
	
	public ReportManagerForm() {
		super();
	}
	
	@Override
	protected void initComponents() {
		super.initComponents();
		tabList.add(new ListPane(new ReportByYearTable()));
		tabList.add(new ListPane(new ReportByMonthTable()));
		tabName.add("Báo cáo theo năm");
		tabName.add("Báo cáo theo tháng");
		detailPane.add(new ReportByYearDetailView());
		detailPane.add(new ReportByMonthDetailView());
	}
	
}
