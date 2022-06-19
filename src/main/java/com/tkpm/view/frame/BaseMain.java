package com.tkpm.view.frame;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.tkpm.view.component.EventMenuSelected;
import com.tkpm.view.component.Menu;
import java.awt.BorderLayout;
import java.awt.Color;

import java.util.ArrayList;


public class BaseMain extends BaseFrame {

	private JPanel contentPane;
	protected static Menu menu;
	protected ArrayList<String> menuNames;
	protected ArrayList<JPanel> listPanels;
	
	
	/**
	 * Create the frame.
	 */
	public BaseMain() {
		initMainComponent();
		initComponents();
		
		setContentPane(contentPane);
		
		contentPane.add(menu, BorderLayout.WEST);
		contentPane.add(listPanels.get(0));
		
		menu.addEventMenuSelected(new EventMenuSelected() {
			
			@Override
			public void selected(int index) {
				contentPane.removeAll();
				contentPane.add(menu, BorderLayout.WEST);
				contentPane.add(listPanels.get(index));
				contentPane.repaint();
				contentPane.revalidate();
			}
		});
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
	
	private void initComponents() {
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