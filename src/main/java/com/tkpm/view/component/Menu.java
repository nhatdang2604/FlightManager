package com.tkpm.view.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import com.tkpm.view.component.ModelMenu.MenuType;

public class Menu extends JPanel {
	
	private JPanel panelLogoApp;
	private JLabel lbNameApp;
	private ListMenu<ModelMenu> menuList;
 	
	private int x;
	private int y;
	
	public static enum MenuUserType {
		CUSTOMER, MANAGER, ADMIN
	}

	/**
	 * Create the panel.
	 */
	public Menu(MenuUserType userType) {
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
		init(userType);
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
	
	
	private void init(MenuUserType type) {
		if (type == MenuUserType.CUSTOMER) {
			menuList.addItem(new ModelMenu("Danh sách chuyến bay", MenuType.MENU));
			menuList.addItem(new ModelMenu("Quản lý chuyến bay", MenuType.MENU));
		}
		else if (type == MenuUserType.MANAGER) {
			menuList.addItem(new ModelMenu("Danh sách chuyến bay", MenuType.MENU));
			menuList.addItem(new ModelMenu("Quản lý chuyến bay", MenuType.MENU));
			menuList.addItem(new ModelMenu("Quản lý báo cáo", MenuType.MENU));
		}
		else {
			menuList.addItem(new ModelMenu("Danh sách chuyến bay", MenuType.MENU));
			menuList.addItem(new ModelMenu("Quản lý chuyến bay", MenuType.MENU));
			menuList.addItem(new ModelMenu("Quản lý báo cáo", MenuType.MENU));
			menuList.addItem(new ModelMenu("Quản lý tài khoản", MenuType.MENU));
		}
	}
}
