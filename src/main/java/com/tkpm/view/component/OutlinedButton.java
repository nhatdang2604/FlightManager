package com.tkpm.view.component;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OutlinedButton extends Button{

	public OutlinedButton(String text) {
		super(text);

		// Initialize color
		setEnabledColor(new Color(255, 255, 255));
		setDisabledColor(new Color(27, 27, 29, 12));
		setHoverColor(new Color(213, 227, 255, 240));
		setPressedColor(new Color(213, 227, 255, 210));
		setBorderColor(new Color(116, 119, 127));
		
		setBackground(getEnabledColor());
		setForeground(new Color(20, 93, 178));
		
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
