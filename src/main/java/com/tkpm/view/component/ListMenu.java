package com.tkpm.view.component;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

import com.tkpm.view.component.ModelMenu.MenuType;

public class ListMenu<E extends Object> extends JList<E>{
	
	private final DefaultListModel model;
	private int selectedindex = 0;
	private int hoverIndex = -1;
	
	private EventMenuSelected eventMenuSelected;
	
	public void addEventMenuSelected(EventMenuSelected event) {
		this.eventMenuSelected = event;
	}

	public ListMenu() {
		model = new DefaultListModel<>();
		setModel(model);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					int index = locationToIndex(e.getPoint());
					Object object = model.getElementAt(index);
					if (object instanceof ModelMenu) {
						ModelMenu menu = (ModelMenu)object;
						if (menu.getType() == MenuType.MENU) {
							selectedindex = index;
							if (eventMenuSelected != null) {
								eventMenuSelected.selected(index);
							}
						}
					}
					else {
						selectedindex = index;
					}
					repaint();
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				hoverIndex = -1;
				repaint();
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int index = locationToIndex(e.getPoint());
				if (index != hoverIndex) {
					Object object = model.getElementAt(index);
					if(object instanceof ModelMenu) {
						ModelMenu menu = (ModelMenu) object;
						if (menu.getType() == MenuType.MENU) {
							hoverIndex = index;
						}
						else {
							hoverIndex = -1;
						}
						repaint();
					}
				}
			}
		});
	}
	
	@Override
	public ListCellRenderer<? super E> getCellRenderer() {
		return new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				ModelMenu data;
				if (value instanceof ModelMenu) {
					data = (ModelMenu)value;
				}
				else {
					data = new ModelMenu(value + "", MenuType.EMPTY);
				}
				MenuItem item = new MenuItem(data);
				item.setSelected(selectedindex == index);
				item.setHover(hoverIndex == index);
				return item;
			}
		};
	}
	
	public void addItem(ModelMenu item) {
		model.addElement(item);
	}
}
