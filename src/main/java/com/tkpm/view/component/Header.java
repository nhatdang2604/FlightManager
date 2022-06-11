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

public class Header extends JPanel {

	/**
	 * Create the panel.
	 */
	public Header() {
		setBorder(new EmptyBorder(0, 20, 0, 20));
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(320, 40));
		setOpaque(false);
		setLayout(new BorderLayout(10, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Header.class.getResource("/com/tkpm/view/component/search.png")));
		add(lblNewLabel, BorderLayout.WEST);
		
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
