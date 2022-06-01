package com.tkpm.view.action;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.nhatdang2604.view.BaseView;
import com.nhatdang2604.view.display_feature_view.BaseFeatureView;

public class MappingFeatureActionListener implements ActionListener {

	private JPanel mapper;
	private BaseFeatureView mappee;
	
	public MappingFeatureActionListener(JPanel mapper, BaseFeatureView mappee) {
		this.mapper = mapper;
		this.mappee = mappee;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		mapper.removeAll();
		
		mapper.setLayout(new BorderLayout());
		mapper.add(mappee, BorderLayout.CENTER);
		
		mappee.repaint();
		mapper.revalidate();
		
	}

}
