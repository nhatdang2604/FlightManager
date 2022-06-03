package com.tkpm.view.widget;

import java.awt.Component;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MultiButtonRenderer extends DefaultTableCellRenderer {

    private MultiButtonPane pane;

    public MultiButtonRenderer(List<JButton> buttons) {
        pane = new MultiButtonPane(buttons);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
    	if (isSelected) {
            pane.setBackground(table.getSelectionBackground());
        } else {
            pane.setBackground(table.getBackground());
        }
        return pane;
    }

}
