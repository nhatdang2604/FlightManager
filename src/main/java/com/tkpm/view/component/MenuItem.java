package com.tkpm.view.component;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.border.EmptyBorder;

import com.tkpm.view.component.ModelMenu.MenuType;

public class MenuItem extends JPanel {
	
	private JLabel jlbMenuName;
	private boolean selected;
	private boolean hover;

	/**
	 * Create the panel.
	 */
	public MenuItem(ModelMenu data) {
		setOpaque(false);
		setBorder(new EmptyBorder(8, 20, 8, 20));
		setLayout(new BorderLayout(0, 0));
		
		jlbMenuName = new JLabel("Menu Name");
		jlbMenuName.setFont(new Font("Nono Sans", Font.PLAIN, 14));
		jlbMenuName.setForeground(Color.WHITE);
		
		if (data.getType() == MenuType.MENU) {
			jlbMenuName.setText(data.getName());
		}
		else if (data.getType() == MenuType.TITLE) {
			jlbMenuName.setVisible(false);
		}
		else {
			jlbMenuName.setText(" ");
		}
		
		add(jlbMenuName);

	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if (selected || hover) {
			Graphics2D graphics2d = (Graphics2D)g;
			graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			if (selected) {
				graphics2d.setColor(new Color(255, 255, 255, 80));
			}
			else {
				graphics2d.setColor(new Color(255, 255, 255, 20));
			}			
			graphics2d.fillRoundRect(10, 0, getWidth() - 20, getHeight(), 5, 5);
		}
		super.paintComponent(g);
	}
	
	public void setHover(boolean hover) {
		this.hover = hover;
		repaint();
	}

}
