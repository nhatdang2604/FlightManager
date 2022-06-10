package com.tkpm.view.component;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FilledButton extends Button{
	
	public FilledButton(String text) {
		super(text);
		
		// Initialize color
		setEnabledColor(new Color(20, 93, 178));
		setDisabledColor(new Color(27, 27, 29, 12));
		setHoverColor(new Color(0, 27, 63, 20));
		setPressedColor(new Color(0, 27, 63, 30));
		setBorderColor(getEnabledColor());
		
		setBackground(getEnabledColor());
		setForeground(Color.WHITE);
		
		//Add event mouse
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					setBackground(getHoverColor());
					setHover(true);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					setBackground(getEnabledColor());
					setHover(false);
				}
				@Override
				public void mousePressed(MouseEvent e) {
					setBackground(getPressedColor());
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					if(isHover()) {
						setBackground(getHoverColor());
					}
					else {
						setBackground(getEnabledColor());
					}
				}
			});
	}
	
}
