package com.tkpm.view.feature_view.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.tkpm.entities.Flight;
import com.tkpm.entities.User;
import com.tkpm.view.widget.ButtonEditor;
import com.tkpm.view.widget.ButtonRenderer;

public class UserCRUDTableView extends JTable {

	protected DefaultTableModel tableModel;
	protected List<User> users;
	
	protected List<JButton> actionButtons;
	
	public static final String[] COLUMN_NAMES = {
		"Chọn", "STT", "Id", "Tên đăng nhập", "Chi tiết", "Chỉnh sửa"
	};
	
	public static final int SELECT_COLUMN_INDEX = 0;
	public static final int COLUMN_INDEX = 1;
	public static final int USER_ID_COLUMN_INDEX = 2;
	public static final int USERNAME_COLUMN_INDEX = 3;
	public static final int DETAIL_COLUMN_INDEX = 4;
	public static final int UPDATE_COLUMN_INDEX = 5;
	
	public static final int DETAIL_BUTTON_INDEX = 0;
	public static final int UPDATE_BUTTON_INDEX = 1;
	
	protected void setupModelTable() {
		//Make uneditable table
		tableModel = new DefaultTableModel(COLUMN_NAMES, 0) {			
					
			@Override
			public boolean isCellEditable(int row, int column) {				
						
				//Make Detail button cell editable
				if (DETAIL_COLUMN_INDEX == column ||
					SELECT_COLUMN_INDEX == column ||
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
				case SELECT_COLUMN_INDEX:
					clazz = Boolean.class;
					break;
				case COLUMN_INDEX:
					clazz = Integer.class;
					break;
				case USER_ID_COLUMN_INDEX:
					clazz = Integer.class;
					break;
				case DETAIL_COLUMN_INDEX:
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
	
	protected void initDetailButton() {
		
		//Setup the action buttons for "Action" column
		actionButtons = new ArrayList<>(Arrays.asList(
				new JButton("Chi tiết"),
				new JButton("Cập nhật")));
		
		int offset = DETAIL_COLUMN_INDEX;
		for (int i = 0; i < actionButtons.size(); ++i) {
			TableColumn column = this.getColumn(COLUMN_NAMES[offset + i]);
			column.setCellRenderer(new ButtonRenderer(actionButtons.get(i).getText()));
			column.setCellEditor(new ButtonEditor(actionButtons.get(i)));
		}
	}
		
	public UserCRUDTableView() {
		setupModelTable();
		initDetailButton();
	}
	
	public UserCRUDTableView clearData() {
		
		//Clear the model
		tableModel.setRowCount(0);
		
		return this;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	//Show all object to the table
	public UserCRUDTableView update() {
		
		clearData();
		int size = users.size();
		for (int index = 0; index < size; ++index) {
			
			User user = users.get(index);
			Object[] row = {
					false,
					index + 1, 
					user.getId(), 
					user.getUsername()};
			tableModel.addRow(row);		
		}
		
		return this;
	}
	
	public User getSelectedUser() {
		int index = this.getSelectedRow();
		return users.get(index);
	}
	
	public List<User> getSelectedUsers() {
		
		//Full scan the table to get all the selected objects
		List<User> result = new ArrayList<>();
		int size = tableModel.getRowCount();
		for (int i = 0; i < size; ++i) {
			Boolean isSelected = (Boolean) tableModel.getValueAt(i, SELECT_COLUMN_INDEX);
			if (null != isSelected && isSelected) {
				result.add(users.get(i));
			}
			
		}
		
		return result;
	}
	
	public List<JButton> getActionButtons() {
		return actionButtons;
	}
	
	
}