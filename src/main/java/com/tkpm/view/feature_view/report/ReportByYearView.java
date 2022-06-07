package com.tkpm.view.feature_view.report;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.tkpm.view.feature_view.table.ReportByYearTableView;

public class ReportByYearView extends JPanel{

	private Integer year;
	private JLabel titleLabel;
	private ReportByYearTableView table;
	
	private void initComponents() {
		titleLabel = new JLabel("Báo cáo doanh thu năm");
		table = new ReportByYearTableView();
	}
	
	private void setLayout() {
		setLayout(new BorderLayout());
		add(titleLabel, BorderLayout.NORTH);
		JScrollPane scroll = new JScrollPane(table);
		add(scroll, BorderLayout.CENTER);
	}
	
	public ReportByYearView() {
		initComponents();
		setLayout();
	}
	
	public void updateTitle() {
		String title = "Báo cáo doanh thu năm" + (null != year?" " + year:"");
		titleLabel.setText(title);
		this.validate();
	}
	
	public void setYear(Integer year) {
		this.year = year;
		updateTitle();
	}
	
	public ReportByYearTableView getTable() {return table;}
}
