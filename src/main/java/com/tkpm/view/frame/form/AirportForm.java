package com.tkpm.view.frame.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.tkpm.entities.Airport;
import com.tkpm.entities.Transition;

public class AirportForm extends JDialog implements FormBehaviour {

	private JLabel jlbAirportName;
	private JPanel panelButton;
	private JPanel panelCancel;
	private JPanel panelInfo;
	
	private JTextField txtAirportName;
	private JLabel warningText;
	
	private JButton cancelButton;
	private JButton okButton;
	
	private Airport model;
	
	public static final int NO_ERROR = 0;
	public static final int EMPTY_STAR_FIELD_ERROR = 1;
	public static final int NAME_EXISTED_FIELD_ERROR = 2;
	
	private static final String[] ERRORS = {
			"",
			"Có ít nhất một ô (*) bị trống",
			"Tên sân bay đã tồn tại"
	};


	
	private void initButtons() {
		cancelButton.addActionListener((event)->{
			close();
		});
		
	}
	
	private void initComponents() {

		panelInfo = new JPanel();
		panelInfo.setBackground(Color.WHITE);
		panelInfo.setBorder(new EmptyBorder(25, 50, 10, 50));
		
		jlbAirportName = new JLabel("Tên sân bay (*)");
		jlbAirportName.setFont(new Font("Noto Sans", Font.BOLD, 16));
		
		txtAirportName = new JTextField();
		txtAirportName.setPreferredSize(new Dimension(7, 30));
		txtAirportName.setColumns(10);
		txtAirportName.setFont(new Font("Noto Sans", Font.PLAIN, 14));
		
		warningText = new JLabel("");
		warningText.setForeground(Color.RED);
		warningText.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		panelButton = new JPanel();
		panelButton.setBackground(Color.WHITE);
		panelButton.setPreferredSize(new Dimension(10, 60));
		panelButton.setBorder(new EmptyBorder(10, 75, 20, 75));
		panelButton.setLayout(new GridLayout(0, 2, 30, 0));
		
		panelCancel = new JPanel();
		panelCancel.setBorder(new LineBorder(Color.DARK_GRAY));
		panelCancel.setBackground(Color.DARK_GRAY);
		cancelButton = new JButton("Huỷ");
		cancelButton.setBackground(Color.WHITE);
		cancelButton.setBorderPainted(false);
		cancelButton.setFont(new Font("Noto Sans", Font.PLAIN, 16));
		
		okButton = new JButton("Thêm");
		okButton.setFont(new Font("Noto Sans", Font.BOLD, 16));
		okButton.setBorderPainted(false);
		okButton.setBackground(new Color(41, 97, 213));
		okButton.setForeground(Color.WHITE);
		
		initButtons();
	}
	
	private void addComponents() {
		
		getContentPane().add(panelInfo, BorderLayout.CENTER);
		panelInfo.setLayout(new GridLayout(0, 1, 0, 10));
		panelInfo.add(jlbAirportName);
		panelInfo.add(txtAirportName);
		panelInfo.add(warningText);
		
		getContentPane().add(panelButton, BorderLayout.SOUTH);
		panelButton.add(panelCancel);
		panelCancel.setLayout(new GridLayout(0, 1, 1, 1));
		panelCancel.add(cancelButton);
		panelButton.add(okButton);
	}
	
	public void init() {
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 400, 270);
		setResizable(false);
		
		initComponents();
		addComponents();
		
		model = new Airport();
	}

	/**
	 * Create the dialog.
	 */
	public AirportForm() {
		init();
	}
	
	public AirportForm(JFrame owner) {
		super(owner, true);
		init();
	}
	
	public AirportForm(JDialog owner) {
		super(owner, true);
		init();
	}
	
	
	public boolean areThereAnyEmptyStarField() {
		
		String airportName = txtAirportName.getText().trim();
		if (null == airportName || airportName.equals("")) {
			return true;
		}
		
		return false;
	}


	public void setAirport(Airport model) {
		this.model= model;		
		txtAirportName.setText(model.getName());
	}
	
	@Override
	public Airport submit() {
		model.setName(txtAirportName.getText().trim());
		
		return model;
	}

	@Override
	public JButton getSubmitButton() {
		return okButton;
	}

	@Override
	public FormBehaviour setError(int errorCode) {
		if (0 <= errorCode && errorCode < ERRORS.length) {
			warningText.setText(ERRORS[errorCode]);
		}
		return this;
	}

	@Override
	public void clear() {
		txtAirportName.setText("");
	}

	@Override
	public void close() {
		setVisible(false);
	}
	
	public void open() {
		clear();
		setVisible(true);
	}

}