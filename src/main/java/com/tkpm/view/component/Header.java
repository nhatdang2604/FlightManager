package com.tkpm.view.component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import javax.swing.JButton;

public class Header extends JPanel {
	
	private JButton menuButton;

	
	public JButton getMenuButton() {
		return menuButton;
	}

	/**
	 * Create the panel.
	 */
	public Header() {
		setBorder(new EmptyBorder(0, 20, 0, 20));
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(320, 40));
		setOpaque(false);
		setLayout(new BorderLayout(0, 0));
		
		JPanel iconPanel = new JPanel();
		iconPanel.setBackground(getBackground());
		iconPanel.setBorder(new EmptyBorder(4, 0, 4, 0));
		add(iconPanel, BorderLayout.WEST);
		iconPanel.setLayout(new GridLayout(0, 2, 10, 0));
		
		menuButton = new JButton("");
		menuButton.setIcon(new ImageIcon(Header.class.getResource("/com/tkpm/view/component/menu.png")));
		menuButton.setBorderPainted(false);
		menuButton.setBackground(Color.WHITE);
		iconPanel.add(menuButton);
		
		JLabel jlbSearchIcon = new JLabel("");
		jlbSearchIcon.setIcon(new ImageIcon(Header.class.getResource("/com/tkpm/view/component/search.png")));
		iconPanel.add(jlbSearchIcon);
		
		SearchText searchText = new SearchText();
		searchText.setOpaque(false);
		add(searchText);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D graphics2d = (Graphics2D)g;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2d.setColor(getBackground());
		graphics2d.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
		super.paintComponent(g);
	}

}
