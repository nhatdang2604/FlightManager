package com.tkpm.view.feature_view.header_view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.tkpm.view.model.ReportInfoModel;

public class BaseHeader extends JPanel{

	private List<JPanel> panels;
	
	private static final int NAV_BAR_PANEL_INDEX = 0;
	
	private JButton logoutButton;
	
	private void initComponents() {
		panels = new ArrayList<>(Arrays.asList(
					new JPanel(),
					new JPanel()
				));
		logoutButton = new JButton("Đăng xuất");
	}
	
	private void reupdatePanels() {
		setLayout(new GridLayout(panels.size(), 1));
		for (JPanel panel: panels) {
			add(panel);
		}
	}
	
	public void setLayout() {
		reupdatePanels();
		
		//Setup the nav bar
		JPanel navBarPanel = panels.get(NAV_BAR_PANEL_INDEX);
		navBarPanel.setLayout(new BoxLayout(navBarPanel, BoxLayout.X_AXIS));
		navBarPanel.add(Box.createHorizontalGlue());
		navBarPanel.add(logoutButton);
	}
	
	public BaseHeader() {
		initComponents();
		setLayout();
	}
	
	public void addPanel(JPanel panel) {
		panels.add(panel);
		reupdatePanels();
	}
	
	public JButton getLogoutButton() {return logoutButton;}
	
}
