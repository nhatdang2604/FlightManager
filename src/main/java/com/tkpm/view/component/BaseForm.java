package com.tkpm.view.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

import com.tkpm.view.feature_view.detail_view.BaseDetailView;
import com.tkpm.view.frame.BaseMain;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.GridLayout;
import java.awt.Dimension;

public class BaseForm extends JPanel{

	protected Header header;
	private Tabbed tabPane;
	private JPanel detailPanel;
	protected ArrayList<ListPane> tabList;
	protected ArrayList<String> tabName;
	protected ArrayList<BaseDetailView> detailPane;
	
	public BaseForm() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 10));
		
		initComponents();
		initMainChildrenComponents();
		
		detailPanel.add(detailPane.get(0));
		add(tabPane, BorderLayout.CENTER);
		add(detailPanel, BorderLayout.EAST);
	}
	
	protected void initComponents() {
		header = new Header();
		header.getMenuButton().setFocusPainted(false);
		header.getMenuButton().setBorder(null);
		header.getMenuButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BaseMain.showMenu();
			}
		});
		add(header, BorderLayout.NORTH);
		
		tabPane = new Tabbed();
		detailPanel = new JPanel();
		detailPanel.setLayout(new GridLayout(1, 0, 0, 0));
		detailPanel.setPreferredSize(new Dimension(300, getHeight()));
		detailPanel.setBackground(Color.WHITE);
		
		tabList = new ArrayList<>();
		tabName = new ArrayList<>();
		detailPane = new ArrayList<>();
		
		tabPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				detailPanel.removeAll();
				detailPanel.add(detailPane.get(tabPane.getSelectedIndex()));
				repaint();
				revalidate();
			}
		});
	}
	
	private void initMainChildrenComponents() {
		for (int index = 0; index < tabList.size(); index++) {
			tabPane.addTab(tabName.get(index), tabList.get(index));
		}
		
		for (BaseDetailView view : detailPane) {
			view.setOpaque(false);
			view.setBackground(Color.WHITE);
		}
	}
}
