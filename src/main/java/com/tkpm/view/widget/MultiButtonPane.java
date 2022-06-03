package com.tkpm.view.widget;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MultiButtonPane  extends JPanel {

    private List<JButton> buttons;
    private String state;

    public void setButtons(List<JButton> buttons) {
    	this.buttons = buttons;
    }
    
    public MultiButtonPane(List<JButton> buttons) {
        setLayout(new GridBagLayout());
        
        ActionListener listener = (event) -> {state = event.getActionCommand();};

        for (JButton button: buttons) {
        	button.setActionCommand(button.getText());
        	button.addActionListener(listener);
        	add(button);
        }    
    
        setButtons(buttons);
    }

    public void addActionListener(ActionListener listener) {
    	 for (JButton button: buttons) {
         	button.addActionListener(listener);
         }
    }

    public String getState() {
        return state;
    }

}
