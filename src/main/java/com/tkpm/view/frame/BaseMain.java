package com.tkpm.view.frame;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.tkpm.view.component.FlightFeatureForm;
import com.tkpm.view.component.Header;
import com.tkpm.view.component.Menu;
import com.tkpm.view.component.Menu.MenuUserType;

import java.awt.BorderLayout;
import java.awt.Color;

import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;


public class BaseMain extends BaseFrame {

	private JPanel contentPane;
	private Menu menu;
	private final MigLayout layout;
	private final FlightFeatureForm flightListForm;
	
	
	/**
	 * Create the frame.
	 */
	public BaseMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 817, 445);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		flightListForm = new FlightFeatureForm();
		menu = new Menu(MenuUserType.ADMIN);
		menu.setPreferredSize(new Dimension(215, contentPane.getHeight()));
		
		layout = new MigLayout("fill", "0[fill]0", "0[fill]0");
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		contentPane.add(menu, BorderLayout.WEST);
		contentPane.add(flightListForm);
		//contentPane.add(menu, "pos 0 0 0% 100%", 0);
		
		
	}

}
