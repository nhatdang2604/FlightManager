package com.tkpm.view.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

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

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class ComboBox<E> extends JComboBox<E> {
	
	private String labelText;
	private Color lineColor = new Color(3, 155, 216);
	private boolean mouseHover;
	
	public ComboBox(String label) {
		this.labelText = label;
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
		
		private final Animator animator;
		private boolean animateHintText = true;
		private float location;
		private boolean show;
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
			
			addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					showing(false);
				}
				@Override
				public void focusLost(FocusEvent e) {
					showing(true);
				}
			});
			
			addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent ie) {
                    if (!isFocusOwner()) {
                        if (getSelectedIndex() == -1) {
                            showing(true);
                        } else {
                            showing(false);
                        }
                    }
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
			
			TimingTarget target = new TimingTargetAdapter() {
				@Override
				public void begin() {
					animateHintText = getSelectedIndex() == -1;
				}
				@Override
				public void timingEvent(float fraction) {
					location = fraction;
					repaint();
				}
			};
			animator = new Animator(300, target);
			animator.setResolution(0);
			animator.setAcceleration(0.5f);
			animator.setDeceleration(0.5f);
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
			createHintText(graphics2d);
			createLineStyle(graphics2d);
			graphics2d.dispose();
		}
		
		private void createHintText(Graphics2D graphics2d) {
			Insets insets = getInsets();
			graphics2d.setColor(new Color(160, 160, 160));
			FontMetrics fontMetrics = graphics2d.getFontMetrics();
			Rectangle2D rectangle2d = fontMetrics.getStringBounds(labelText, graphics2d);
			double height = getHeight() - insets.top - insets.bottom;
			double textY = (height - rectangle2d.getHeight()) / 2;
			double size;
			if (animateHintText) {
				if (show) {
					size = (18 * (1 - location));
				}
				else {
					size = 18 * location;
				}
			}
			else {
				size = 18;
			}
			graphics2d.drawString(labelText, insets.right, (int)(insets.top+textY+fontMetrics.getAscent() - size));
		}
		
		private void createLineStyle(Graphics2D graphics2d) {
			if (isFocusOwner()) {
				double width = getWidth()-4;
				int height = getHeight();
				graphics2d.setColor(lineColor);
				double size;
				if (show) {
					size = width * (1 - location);
				}
				else {
					size = width * location;
				}
				double x = (width - size);
				graphics2d.fillRect((int)(x + 2), height-2, (int)size, 2);
			}
		}

		
		@Override
		public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
			
		}
		
		private void showing(boolean action) {
			if (animator.isRunning()) {
				animator.stop();
			}
			else {
				location = 1;
			}
			animator.setStartFraction(1f-location);
			show = action;
			location = 1f - location;
			animator.start();
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
