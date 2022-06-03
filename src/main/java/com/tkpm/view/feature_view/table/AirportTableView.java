package com.tkpm.view.feature_view.table;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.tkpm.entities.Airport;
import com.tkpm.entities.Flight;
import com.tkpm.view.widget.ButtonEditor;
import com.tkpm.view.widget.ButtonRenderer;

public class AirportTableView extends JTable {

	protected DefaultTableModel tableModel;
	protected List<Airport> airports;
	
	protected JButton updateButton;
	
	public static final String[] COLUMN_NAMES = {
		"Chọn", "STT", "Tên sân bay", "Thao tác"
	};
	
	public static final int SELECT_COLUMN_INDEX = 0;
	public static final int COLUMN_INDEX = 1;
	public static final int NAME_COLUMN_INDEX = 2;
	public static final int UPDATE_COLUMN_INDEX = 3;
	
	protected void setupModelTable() {
		//Make uneditable table
		tableModel = new DefaultTableModel(COLUMN_NAMES, 0) {			
					
			@Override
			public boolean isCellEditable(int row, int column) {				
						
				//Make Select checkbox and Update button cell editable
				if (SELECT_COLUMN_INDEX == column ||
						UPDATE_COLUMN_INDEX == column) {
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
				case SELECT_COLUMN_INDEX:
					clazz = Boolean.class;
					break;
				case UPDATE_COLUMN_INDEX:
					clazz = Boolean.class;
					break;
		      }
		      return clazz;
		    }
			
		};
				
		//Enable table model
		this.setModel(tableModel);
		
		
	}
	
	protected void initUpdateButton() {
		
		//Setup for the Update button in cell
		String updateButtonName = "Cập nhật";
		updateButton = new JButton(updateButtonName);
		TableColumn updateColumn = this.getColumn(COLUMN_NAMES[UPDATE_COLUMN_INDEX]);
		updateColumn.setCellRenderer(new ButtonRenderer(updateButtonName));
		updateColumn.setCellEditor(new ButtonEditor(updateButton));			
	
	}
		
	public AirportTableView() {
		setupModelTable();
		initUpdateButton();
	}
	
	public AirportTableView clearData() {
		
		//Clear the model
		tableModel.setRowCount(0);
		
		return this;
	}
	
	public void setAirports(List<Airport> airports) {
		this.airports = airports;
	}
	
	//Show all airport in airports to the table
	public AirportTableView update() {
		
		clearData();
		int size = airports.size();
		for (int index = 0; index < size; ++index) {
			
			Airport airport = airports.get(index);
			Object[] row = {
					false,
					index + 1, 
					airport.getName()};
			
			tableModel.addRow(row);		
		}
		return this;
	}
	
	public Airport getSelectedAirport() {
		int index = this.getSelectedRow();
		return airports.get(index);
	}
	
	public JButton getUpdateButton() {
		return updateButton;
	}
}
