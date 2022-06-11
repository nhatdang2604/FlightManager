package com.tkpm.view.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import com.tkpm.view.component.ModelMenu.MenuType;

public class Menu extends JPanel {
	
	private EventMenuSelected eventMenuSelected;
	
	public void addEventMenuSelected(EventMenuSelected event) {
		this.eventMenuSelected = event;
		menuList.addEventMenuSelected(eventMenuSelected);
	}
	
	private JPanel panelLogoApp;
	private JLabel lbNameApp;
	private ListMenu<ModelMenu> menuList;
 	
	private int x;
	private int y;
	/**
	 * Create the panel.
	 */
	public Menu(ArrayList<String> menuNames) {
		setBorder(new EmptyBorder(15, 0, 0, 0));
		setOpaque(false);
		setLayout(new BorderLayout(0, 20));
		
		panelLogoApp = new JPanel();
		panelLogoApp.setOpaque(false);
		panelLogoApp.setBorder(new EmptyBorder(0, 15, 0, 15));
		add(panelLogoApp, BorderLayout.NORTH);
		panelLogoApp.setLayout(new BorderLayout(0, 0));
		
		lbNameApp = new JLabel("US Airline");
		lbNameApp.setFont(new Font("Nono Sans", Font.BOLD, 18));
		lbNameApp.setForeground(Color.white);
		panelLogoApp.add(lbNameApp, BorderLayout.NORTH);
		
		menuList = new ListMenu<>();
		menuList.setOpaque(false);
		init(menuNames);
		add(menuList);
	}
	
	@Override
	protected void paintChildren(Graphics g) {
		Graphics2D graphics2d = (Graphics2D)g;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		GradientPaint gPaint = new GradientPaint(0, 0, Color.decode("#56CCF2"), 0, getHeight(), Color.decode("#2F80ED"));
		graphics2d.setPaint(gPaint);
		graphics2d.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
		graphics2d.fillRect(getWidth()-20, 0, getWidth(), getHeight());
		super.paintChildren(g);
	}
	
	
	private void init(ArrayList<String> names) {
		for (String name : names) {
			menuList.addItem(new ModelMenu(name, MenuType.MENU));
		}
	}
}
