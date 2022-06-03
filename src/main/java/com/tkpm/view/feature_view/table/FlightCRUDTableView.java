package com.tkpm.view.feature_view.table;

import java.awt.BorderLayout;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.tkpm.entities.Airport;
import com.tkpm.entities.Flight;
import com.tkpm.view.widget.MultiButtonEditor;
import com.tkpm.view.widget.MultiButtonRenderer;

public class FlightCRUDTableView extends JTable {

	protected DefaultTableModel tableModel;
	protected List<Flight> flights;
	
	protected List<JButton> actionButtons;
	
	public static final String[] COLUMN_NAMES = {
		"Chọn", "STT", "Mã chuyến bay", "Sân bay đi", "Sân bay đến", "Thời gian", "Thao tác"
	};
	
	public static final int SELECT_COLUMN_INDEX = 0;
	public static final int COLUMN_INDEX = 1;
	public static final int FLIGHT_ID_COLUMN_INDEX = 2;
	public static final int DEPARTURE_AIRPORT_COLUMN_INDEX = 3;
	public static final int ARRIVAL_AIRPORT_COLUMN_INDEX = 4;
	public static final int DATETIME_COLUMN_INDEX = 5;
	public static final int ACTION_COLUMN_INDEX = 6;
	
	public static final int DETAIL_BUTTON_INDEX = 0;
	public static final int UPDATE_BUTTON_INDEX = 1;
	
	protected void setupModelTable() {
		//Make uneditable table
		tableModel = new DefaultTableModel(COLUMN_NAMES, 0) {			
					
			@Override
			public boolean isCellEditable(int row, int column) {				
						
				//Make Detail button cell editable
				if (ACTION_COLUMN_INDEX == column) {
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
				case ACTION_COLUMN_INDEX:
					clazz = Object.class;
					break;
		      }
		      return clazz;
		    }
			
		};
				
		//Enable table model
		this.setModel(tableModel);
		
		
	}
	
	protected void initDetailButton() {
		
		//Setup the action buttons for "Action" column
		actionButtons = new ArrayList<>(Arrays.asList(
				new JButton("Chi tiết"),
				new JButton("Cập nhật")));
		
		TableColumn detailColumn = this.getColumn(COLUMN_NAMES[ACTION_COLUMN_INDEX]);
		detailColumn.setCellRenderer(new MultiButtonRenderer(actionButtons));
		detailColumn.setCellEditor(new MultiButtonEditor(actionButtons));			
	
	}
		
	public FlightCRUDTableView() {
		setupModelTable();
		initDetailButton();
	}
	
	public FlightCRUDTableView clearData() {
		
		//Clear the model
		tableModel.setRowCount(0);
		
		return this;
	}
	
	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
	
	//Show all flight in flights to the table
	public FlightCRUDTableView update() {
		
		clearData();
		int size = flights.size();
		for (int index = 0; index < size; ++index) {
			
			Flight flight = flights.get(index);
			Object[] row = {
					false,
					index + 1, 
					flight.getId(), 
					flight.getDepartureAirport().getName(),
					flight.getArrivalAirport().getName(),
					flight.getDateTime()};
			tableModel.addRow(row);		
		}
		return this;
	}
	
	public Flight getSelectedFlight() {
		int index = this.getSelectedRow();
		return flights.get(index);
	}
	
	public List<JButton> getActionButtons() {
		return actionButtons;
	}
	
	public static void main(String[] args) {
		Flight f = new Flight();
		Airport a = new Airport();
		a.setName("test");
		f.setId(123);
		f.setDepartureAirport(a);
		f.setArrivalAirport(a);
		f.setDateTime(LocalDateTime.now());
		
		List<Flight> fs = new ArrayList<>();
		fs.add(f);
		
		FlightCRUDTableView t = new FlightCRUDTableView();
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(t, BorderLayout.CENTER);
		
		
		t.setFlights(fs);
		t.update();
		
		frame.setVisible(true);
	}
}
