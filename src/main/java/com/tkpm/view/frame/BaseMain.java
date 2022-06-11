package com.tkpm.view.frame;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.tkpm.view.component.FlightFeatureForm;
import com.tkpm.view.component.Header;
import com.tkpm.view.component.Menu;
import com.tkpm.view.feature_view.detail_view.FlightListDetailView;

import java.awt.BorderLayout;
import java.awt.Color;

import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;


public class BaseMain extends BaseFrame {

	private JPanel contentPane;
	protected static Menu menu;
	protected ArrayList<String> menuNames;
	protected ArrayList<JPanel> listPanels;
	protected ArrayList<JPanel> detailPanels;
	
	
	/**
	 * Create the frame.
	 */
	public BaseMain() {
		initMainComponent();
		initCompoents();
		
		setContentPane(contentPane);
		
		contentPane.add(menu, BorderLayout.WEST);
		contentPane.add(listPanels.get(0));
		listPanels.get(0).add(detailPanels.get(0), BorderLayout.EAST);
	}
	
	//Init value for menuPanels and featurePanels
	protected void initValueForComponents() {
		//do some thing in the concrete class
	}
	
	private void initMainComponent() {
		setTitle("Quản lý bán vé máy bay");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 445);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout());
	}
	
	private void initCompoents() {
		initValueForComponents();
		menu.setPreferredSize(new Dimension(215, contentPane.getHeight()));
		menu.setVisible(false);
	}
	
	public static void showMenu() {
		if (menu.isShowing()) {
			menu.setVisible(false);
		}
		else {
			menu.setVisible(true);
		}
	}

}
