package com.tkpm.view.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

public class ComboBox<E> extends JComboBox<E> {
	
	private Color lineColor = new Color(3, 155, 216);
	private boolean mouseHover;
	
	public ComboBox() {
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(0, 3, 5, 3));
		setUI(new ComboUI(this));
		setFocusable(false);
		setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				setBorder(new EmptyBorder(5, 5, 5, 5));
				if (isSelected) {
					component.setBackground(new Color(240, 240, 240));
				}
				return component;
			}
		});
	}

	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}
	
	private class ComboUI extends BasicComboBoxUI {
		
		private ComboBox comboBox;
		
		public ComboUI(ComboBox combo) {
			this.comboBox = combo;
			
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					mouseHover = true;
					repaint();
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					mouseHover = false;
					repaint();
				}
			});
			
			
			
			addPopupMenuListener(new PopupMenuListener() {
                @Override
                public void popupMenuWillBecomeVisible(PopupMenuEvent pme) {
                    arrowButton.setBackground(new Color(200, 200, 200));
                }

                @Override
                public void popupMenuWillBecomeInvisible(PopupMenuEvent pme) {
                    arrowButton.setBackground(new Color(150, 150, 150));
                }

                @Override
                public void popupMenuCanceled(PopupMenuEvent pme) {
                    arrowButton.setBackground(new Color(150, 150, 150));
                }
            });
		}
		
		@Override
		protected ComboPopup createPopup() {
			BasicComboPopup popup = new BasicComboPopup(comboBox) {
				@Override
				protected JScrollPane createScroller() {
					list.setFixedCellHeight(30);
					JScrollPane scrollPane = new JScrollPane(list);
					scrollPane.setBackground(Color.WHITE);
					ScrollBarCustom scrollBar = new ScrollBarCustom();
					scrollBar.setUnitIncrement(30);
					scrollBar.setForeground(new Color(180, 180, 180));
					scrollPane.setVerticalScrollBar(scrollBar);
					return scrollPane;
				}
			};
			popup.setBorder(new LineBorder(new Color(200, 200, 200), 1));
			return popup;
		}
		
		@Override
		public void paint(Graphics g, JComponent c) {
			super.paint(g, c);
			Graphics2D graphics2d = (Graphics2D)g;
			graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
			int width = getWidth();
			int height = getHeight();
			if (mouseHover) {
				graphics2d.setColor(lineColor);
			}
			else {
				graphics2d.setColor(new Color(150, 150, 150));
			}
			graphics2d.fillRect(2, height-1, width-4, 1);
			graphics2d.dispose();
		}
		
		@Override
		public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
			
		}
		
		@Override
		protected JButton createArrowButton() {
			return new ArrowButton();
		}
		
		private class ArrowButton extends JButton {
			public ArrowButton() {
				setContentAreaFilled(false);
				setBorder(new EmptyBorder(5, 5, 5, 5));
				setBackground(new Color(150, 150, 150));
			}
			
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				Graphics2D graphics2d = (Graphics2D)g;
				graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				int width = getWidth();
				int height = getHeight();
				int size = 10;
				int x = (width - size) / 2;
				int y = (height - size) / 2 + 5;
				int px[] = {x, x + size, x + size / 2};
				int py[] = {y, y, y + size};
				graphics2d.setColor(getBackground());
				graphics2d.fillPolygon(px, py, px.length);
				graphics2d.dispose();
			}
		}
	}

}
