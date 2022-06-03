package com.tkpm.view.widget;

import java.awt.Component;
import java.util.EventObject;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;

public class MultiButtonEditor extends AbstractCellEditor implements TableCellEditor {

    private MultiButtonPane pane;

    public MultiButtonEditor(List<JButton> buttons) {
        pane = new MultiButtonPane(buttons);
        pane.addActionListener(event -> {
        	SwingUtilities.invokeLater(() -> stopCellEditing());
        });
    }

    @Override
    public Object getCellEditorValue() {
        return pane.getState();
    }

    @Override
    public boolean isCellEditable(EventObject e) {
        return true;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (isSelected) {
            pane.setBackground(table.getSelectionBackground());
        } else {
            pane.setBackground(table.getBackground());
        }
        return pane;
    }
}