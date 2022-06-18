package com.tkpm.view.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class ScrollBarCustom extends JScrollBar{

	public class ModernScrollBarUI extends BasicScrollBarUI {
		
		private final int THUMB_SIZE = 60;
		
		@Override
		protected Dimension getMaximumThumbSize() {
			if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
				return new Dimension(0, THUMB_SIZE);
			}
			else {
				return new Dimension(THUMB_SIZE, 0);
			}
		}
		
		@Override
		protected Dimension getMinimumThumbSize() {
			if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
				return new Dimension(0, THUMB_SIZE);
			}
			else {
				return new Dimension(THUMB_SIZE, 0);
			}
		}
		
		@Override
		protected JButton createIncreaseButton(int orientation) {
			return new ScrollBarButton();
		}
		
		@Override
		protected JButton createDecreaseButton(int orientation) {
			return new ScrollBarButton();
		}
		
		@Override
		protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
			Graphics2D graphics2d = (Graphics2D)g;
			graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			int orientation = scrollbar.getOrientation();
			int size;
			int x;
			int y;
			int width;
			int height;
			if (orientation==JScrollBar.VERTICAL) {
				size = trackBounds.width/2;
				x = trackBounds.x + ((trackBounds.width - size)/2);
				y = trackBounds.y;
				width = size;
				height = trackBounds.height;
			}
			else {
				size = trackBounds.height/2;
				y = trackBounds.y + ((trackBounds.height - size)/2);
				x = 0;
				width = trackBounds.width;
				height = size;
			}
			graphics2d.setColor(Color.WHITE);
			graphics2d.fillRect(x, y, width, height);
		}
		
		@Override
		protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
			Graphics2D graphics2d = (Graphics2D)g;
			graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			int x = thumbBounds.x;
			int y = thumbBounds.y;
			int width = thumbBounds.width;
			int height = thumbBounds.height;
			if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
				y += 8;
				height -= 16;
			}
			else {
				x += 8;
				width -= 16;
			}
			graphics2d.setColor(new Color(20, 93, 178));
			graphics2d.fillRoundRect(x, y, width, height, 16, 16);
		}
		
		private class ScrollBarButton extends JButton{
			public ScrollBarButton() {
				setBorder(BorderFactory.createEmptyBorder());
			}
		}
	}
	
	public ScrollBarCustom() {
		setUI(new ModernScrollBarUI());
		setPreferredSize(new Dimension(8, 8));
		setForeground(Color.LIGHT_GRAY);
		setBackground(Color.WHITE);
	}
	
}
