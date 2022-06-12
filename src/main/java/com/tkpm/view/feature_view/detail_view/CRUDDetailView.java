package com.tkpm.view.feature_view.detail_view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import com.tkpm.view.component.Button;
import com.tkpm.view.component.FilledButton;
import com.tkpm.view.component.OutlinedButton;

@SuppressWarnings("serial")
public class CRUDDetailView extends BaseReadOnlyDetailView {
	
	protected List<Button> buttons;
	
	public static final int CREATE_BUTTON_INDEX = 0;
	public static final int DELETE_BUTTON_INDEX = 1;
	
	protected void makeButtons() {
		
		//Create buttons
		buttons = new ArrayList<>();
		buttons.add(new FilledButton("Thêm"));
		buttons.add(new OutlinedButton("Xóa"));
		
		
		//Add Buttons to detail panel
		for (Button button: buttons) {
			paddingPanels.get(2).add(button);
		}
	}
	
	public CRUDDetailView(String titled, String[] attributeFieldNames) {
		super(titled, attributeFieldNames);
		makeButtons();
		
	}
	
	
	public List<Button> getButtons() {
		return buttons;
	}
}