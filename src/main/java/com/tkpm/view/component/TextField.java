package com.tkpm.view.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class TextField extends JTextField{
	
	private final Animator animator;
	private boolean animateHintText = true;
	private float location;
	private boolean show;
	
	private boolean mouseHover = false;
	private String labelText;
	private final Color lineColor = new Color(20, 93, 178);
	

	public TextField(String label) {
		this.labelText = label;
		setBorder(new EmptyBorder(20, 3, 10, 3));
		setSelectionColor(new Color(141, 195, 248, 80));
		setFont(new Font("Noto Sans", Font.PLAIN, 14));
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
		
		TimingTarget target = new TimingTargetAdapter() {
			@Override
			public void begin() {
				animateHintText = getText().equals("");
			}
			@Override
			public void timingEvent(float fraction) {
				location = fraction;
				repaint();
			}
		};
		animator = new Animator(100, target);
		animator.setResolution(0);
		animator.setAcceleration(0.5f);
		animator.setDeceleration(0.5f);
		
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
	public void paint(Graphics g) {
		super.paint(g);
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
				size = (18*(1 - location));
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
	
//	@Override
//	public void setText(String t) {
//		if (!getText().equals(t)) {
//			showing(t.equals(""));
//		}
//		super.setText(t);
//	}
	
}
