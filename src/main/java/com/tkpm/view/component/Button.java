package com.tkpm.view.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Button extends JButton{
	
	private Color enabledColor;
	private Color disabledColor;
	private Color hoverColor;
	private Color pressedColor;
	private Color borderColor;
	private int radius = 20;
	private boolean hover = false;
	
	public Button(String text) {
		super(text);
		setContentAreaFilled(false);
		setBounds(0, 0, 80, 40);
		setMargin(new Insets(8, 24, 8, 24));
		setBorderPainted(false);
	}
	
	public Color getEnabledColor() {
		return enabledColor;
	}
	public void setEnabledColor(Color enabledColor) {
		this.enabledColor = enabledColor;
	}
	public Color getDisabledColor() {
		return disabledColor;
	}
	public void setDisabledColor(Color disabledColor) {
		this.disabledColor = disabledColor;
	}
	public Color getHoverColor() {
		return hoverColor;
	}
	public void setHoverColor(Color hoverColor) {
		this.hoverColor = hoverColor;
	}
	public Color getPressedColor() {
		return pressedColor;
	}
	public void setPressedColor(Color pressedColor) {
		this.pressedColor = pressedColor;
	}
	public Color getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public boolean isHover() {
		return hover;
	}
	public void setHover(boolean hover) {
		this.hover = hover;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D graphics2d = (Graphics2D)g;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// Paint Border
		graphics2d.setColor(borderColor);
		graphics2d.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
		graphics2d.setColor(getBackground());
		// Border set 1 Pixels
		graphics2d.fillRoundRect(1, 1, getWidth()-2, getHeight()-2, radius, radius);
		super.paintComponent(g);
	}
}
