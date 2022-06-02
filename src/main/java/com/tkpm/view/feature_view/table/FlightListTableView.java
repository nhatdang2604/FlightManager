package com.tkpm.view.feature_view.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.nhatdang2604.model.entity.Subject;
import com.nhatdang2604.view.display_feature_view.detail.BaseDetailView;
import com.nhatdang2604.view.display_feature_view.detail.CRUD_DetailView;
import com.nhatdang2604.view.widget.ButtonEditor;
import com.nhatdang2604.view.widget.ButtonRenderer;

public class FlightListTableView extends JTable implements TableViewBehaviour{

	protected JPanel headerView;
	protected BaseDetailView detailView;
	protected DefaultTableModel tableModel;
	protected List<Flight> flights;
	
	protected JButton detailButton;
	
	public static final String[] COLUMN_NAMES = {
		"Chọn", "STT", "Mã chuyến bay", "Sân bay đi", "Sân bay đến", "Thời gian", "Thao tác"
	};
	
	public static final int SELECT_COLUMN_INDEX = 0;
	public static final int COLUMN_INDEX = 1;
	public static final int FLIGHT_ID_COLUMN_INDEX = 2;
	public static final int DEPARTURE_AIRPORT_COLUMN_INDEX = 3;
	public static final int ARRIVAL_AIRPORT_COLUMN_INDEX = 4;
	public static final int DATETIME_COLUMN_INDEX = 5;
	public static final int DETAIL_COLUMN_INDEX = COLUMN_NAMES.length - 1;
	
	protected void setupModelTable() {
		//Make uneditable table
		tableModel = new DefaultTableModel(COLUMN_NAMES, 0) {			
					
			@Override
			public boolean isCellEditable(int row, int column) {				
						
				//Make Detail and Select button cell editable
				if (DETAIL_COLUMN_INDEX == column ||
						SELECT_COLUMN_INDEX == column) {
					return true;
				}
						
				//all another cells false
				return false;
			}
			
			@Override
		    public Class<?> getColumnClass(int columnIndex) {
				Class clazz = String.class;
				switch (columnIndex) {
				case COLUMN_INDEX:
					clazz = Integer.class;
					break;
				case FLIGHT_ID_COLUMN_INDEX:
					clazz = Integer.class;
					break;
				case DETAIL_COLUMN_INDEX:
					clazz = Boolean.class;
					break;
				case SELECT_COLUMN_INDEX:
					clazz = Boolean.class;
					break;
		      }
		      return clazz;
		    }
			
		};
				
		//Enable table model
		this.setModel(tableModel);
		
		
	}
	
	protected void initActionButton() {
		
		//Setup for the Update button in cell
		String detailButtonName = "Chi tiết";
		detailButton = new JButton(detailButtonName);
		TableColumn detailColumn = this.getColumn(COLUMN_NAMES[DETAIL_COLUMN_INDEX]);
		detailColumn.setCellRenderer(new ButtonRenderer(detailButtonName));
		detailColumn.setCellEditor(new ButtonEditor(detailButton));			
	
	}
		
	public FlightListTableView() {
		
		setupModelTable();
		initUpdateButton();
		
		
		detailView = new CRUD_DetailView("Môn học", null) ;
		
		//Dummy header: nothing in header
		headerView = new JPanel();
		
	}
	
	public FlightListTableView clearData() {
		
		//Clear the model
		tableModel.setRowCount(0);
		
		return this;
	}
	
	public FlightListTableView readData(List<Subject> data) {
		subjectModels = data;
		return this;
	}
	
	public FlightListTableView update() {
		
		clearData();
		int size = subjectModels.size();
		for (int index = 0; index < size; ++index) {
			
			Subject subject = subjectModels.get(index);
			Object[] row = {index + 1, subject.getSubjectId(), subject.getName()};
			tableModel.addRow(row);		
		}
		return this;
	}
	
	public Subject getSelectedSubject() {
		int selectedIndex = this.getSelectedRow();
		return subjectModels.get(selectedIndex);
	}
	
	public List<Subject> getSelectedSubjects() {
		
		//Full scan the table to get all the selected subject
		List<Subject> result = new ArrayList<>();
		int size = tableModel.getRowCount();
		for (int i = 0; i < size; ++i) {
			Boolean isSelected = (Boolean) tableModel.getValueAt(i, SELECT_COLUMN_INDEX);
			if (null != isSelected && isSelected) {
				result.add(subjectModels.get(i));
			}
			
		}
		
		return result;
	}
	
	@Override
	public JPanel getHeaderView() {
		return headerView;
	}

	@Override
	public BaseDetailView getDetailView() {
		return detailView;
	}

	@Override
	public JTable getTable() {
		return this;
	}

	
	public JButton getUpdateButton() {
		return updateButton;
	}
}
