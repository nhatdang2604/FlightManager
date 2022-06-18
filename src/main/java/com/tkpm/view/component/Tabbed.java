package com.tkpm.view.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JTabbedPane;
import javax.swing.plaf.metal.MetalTabbedPaneUI;

public class Tabbed extends JTabbedPane{
	
	public Tabbed() {
		setUI(new TabbedUI());
		setBackground(Color.WHITE);
	}
	
	public class TabbedUI extends MetalTabbedPaneUI{
		
		private Rectangle currentRectangle;
		
		public TabbedUI() {
			
		}
		
		public void setCurrentRectangle(Rectangle currentRectangle) {
			this.currentRectangle = currentRectangle;
			repaint();
		}
		
		@Override
		protected Insets getTabInsets(int tabPlacement, int tabIndex) {
			return new Insets(10, 10, 10, 10);
		}
		
		@Override
		protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h,
				boolean isSelected) {
			Graphics2D graphics2d = (Graphics2D)g.create();
			graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			graphics2d.setColor(new Color(3, 155, 216));
			if (currentRectangle != null) {
				if (isSelected) {
					currentRectangle = new Rectangle(x, y, w, h);
				}
				graphics2d.fillRect(currentRectangle.x, currentRectangle.y + currentRectangle.height - 3, currentRectangle.width, 3);
			}
			graphics2d.dispose();
		}
		
		@Override
		protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
			Graphics2D graphics2d = (Graphics2D)g.create();
			graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			graphics2d.setColor(new Color(200, 200, 200));
			Insets insets = getTabAreaInsets(tabPlacement);
			int width = tabPane.getWidth();
			int height = tabPane.getHeight();
			int tabHeight = calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight);
			graphics2d.drawLine(insets.left, tabHeight, width-insets.right-1, tabHeight);
			graphics2d.dispose();
		}
		
		@Override
		protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex,
				Rectangle iconRect, Rectangle textRect, boolean isSelected) {
			
		}
	}
}
