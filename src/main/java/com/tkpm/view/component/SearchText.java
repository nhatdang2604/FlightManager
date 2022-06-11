package com.tkpm.view.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SearchText extends JTextField {

	public SearchText() {
		setBorder(new EmptyBorder(5, 10, 5, 10));
		setSelectionColor(new Color(220, 204, 182));
		setFont(new Font("Nono Sans", Font.PLAIN, 14));
	}
	
	private final String hint = "Search here ...";
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (getText().length() == 0) {
			int height = getHeight();
			((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			Insets insets = getInsets();
			FontMetrics fMetrics = g.getFontMetrics();
			int color0 = Color.lightGray.getRGB();
			int color1 = Color.lightGray.getRGB();
			int m = 0xfefefefe;
			int color2 = ((color0 & m) >>> 1) + ((color1 & m) >>> 1);
			g.setColor(new Color(color2, true));
			g.drawString(hint, insets.left, height/2 + fMetrics.getAscent()/2 - 2);
		}
	}
	
}
