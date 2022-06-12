package com.tkpm.view.feature_view.table;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.tkpm.wrapper.FlightStatisticWrapper;
import com.tkpm.wrapper.report.BaseReport;

public class ReportByMonthTableView extends JTable {

	protected DefaultTableModel tableModel;
	protected BaseReport report;
	
	public static final String[] COLUMN_NAMES = {
		"STT", "Mã chuyến bay", "Số vé", "Tỉ lệ", "Doanh thu"
	};
	
	public static final int COLUMN_INDEX = 0;
	public static final int FLIGHT_ID_COLUMN_INDEX = 1;
	public static final int NUMBER_OF_TICKETS_COLUMN_INDEX = 2;
	public static final int RATIO_COLUMN_INDEX = 3;
	public static final int TURNOVER_COLUMN_INDEX = 4;
	
	protected void setupModelTable() {
		//Make uneditable table
		tableModel = new DefaultTableModel(COLUMN_NAMES, 0) {			
					
			@Override
			public boolean isCellEditable(int row, int column) {				
						
				//all another cells false
				return false;
			}
			
			@Override
		    public Class<?> getColumnClass(int columnIndex) {
				Class clazz = Integer.class;
				switch (columnIndex) {
				case RATIO_COLUMN_INDEX:
					clazz = Double.class;
					break;
				
		      }
		      return clazz;
		    }
			
		};
				
		//Enable table model
		this.setModel(tableModel);
		
	}
		
	public ReportByMonthTableView() {
		setupModelTable();
		
		getTableHeader().setReorderingAllowed(false);
	}
	
	public ReportByMonthTableView clearData() {
		
		//Clear the model
		tableModel.setRowCount(0);
		
		return this;
	}
	
	public void setReport(BaseReport report) {
		this.report = report;
	}
	
	//Show all flight in flights to the table
	public ReportByMonthTableView update() {
		
		clearData();
		
		//Print data
		int totalTurnover = report.getTurnover();
		int size = report.getWrappers().size();
		for (int index = 0; index < size; ++index) {
			
			
			FlightStatisticWrapper wrapper = report.getWrappers().get(index);
			double ratio = (double)(wrapper.getTurnover()/totalTurnover);
			Object[] row = {
					index + 1, 
					wrapper.getFlight().getId(),
					wrapper.getTotalNumberOfSeat(),
					ratio,
					wrapper.getTurnover()};
			
			tableModel.addRow(row);		
		}
		return this;
	}
	

}
