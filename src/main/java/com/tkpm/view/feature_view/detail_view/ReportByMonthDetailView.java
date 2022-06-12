package com.tkpm.view.feature_view.detail_view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.tkpm.view.component.ComboBox;
import com.tkpm.view.component.TextField;
import com.tkpm.view.model.ReportInfoModel;
import java.awt.Color;

public class ReportByMonthDetailView extends BaseReportDetailView {

	private static final String TITLE = "Báo cáo theo tháng";
	
	private static final String[] DATA_FIELD_NAMES = {
			"Tháng",
			"Năm",	
	};
	
	private TextField yearField;
	private ComboBox<Integer> monthField;
	
	private void setupDataField() {
		yearField = new TextField("");
		yearField.setBorder(new EmptyBorder(0, 3, 10, 3));
		yearField.addKeyListener(new KeyAdapter() {
			   public void keyTyped(KeyEvent e) {
				   ignoreNANValue(e);
			   }
		});
		
		monthField = new ComboBox<>("Tháng");
		
		//Add 12 months into combobox
		for (int i = 1; i <= 12; ++i) {
			monthField.addItem(new Integer(i));
		}

	}
	
	private void setLayoutForDataPanel() {
		
		//Setup layout
		paddingPanels.get(1).setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		//Add labels first
		for (int i = 0; i < attributeFields.size(); ++i) {
			String metaLayout = "4, " + (i+2)*2 + ", right, default";
			paddingPanels.get(1).add(attributeFields.get(i), metaLayout);
		}
		
		//Add data afterward
		paddingPanels.get(1).add(warningField, "6, 2, center, default");
		paddingPanels.get(1).add(monthField, "6, 4, fill, default");
		paddingPanels.get(1).add(yearField, "6, 6, fill, default");
	}
	
	
	public ReportByMonthDetailView() {
		super(TITLE, DATA_FIELD_NAMES);
		setOpaque(true);
		setBackground(Color.WHITE);
		setupDataField();
		setLayoutForDataPanel();
	}

	@Override
	public ReportInfoModel submit() {
		int month = (Integer) monthField.getSelectedItem();
		int year = Integer.parseInt(yearField.getText().trim());
		ReportInfoModel model = new ReportInfoModel(year, month);
		return model;
	}

}
