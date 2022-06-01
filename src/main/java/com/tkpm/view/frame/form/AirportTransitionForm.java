package com.tkpm.view.frame.form;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;

import com.tkpm.utils.HashingUtil;

import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class AirportTransitionForm extends JDialog implements FormBehaviour {

	private JLabel jlbTransitionAir;
	private JLabel jlbAirportId;
	private JPanel panelButton;
	private JPanel panelCancel;
	private JPanel panelInfo;
	
	private JTextField txtAirportId;
	private JLabel jlbTransitionTime;
	private JTextField txtTransitionTime;
	private JLabel jlbNote;
	private JTextField txtNote;
	private JLabel warningText;
	
	private JButton cancelButton;
	private JButton okButton;
	
	public static final int NO_ERROR = 0;
	public static final int EMPTY_STAR_FIELD_ERROR = 1;
	public static final int NUMBER_FIELD_ERROR = 2;
	
	private static final String[] ERRORS = {
			"",
			"Có ít nhất một ô (*) bị trống",
			"Thời gian dừng phải là số nguyên"
	};
	
	
	//Ignore not an number value from an event
	//	And open the flag of nan error in number field
	private void ignoreNANValue(KeyEvent event) {
		char character = event.getKeyChar();
		if ((character < '0') || (character > '9') && (character != KeyEvent.VK_BACK_SPACE)) {
			event.consume();
			setError(NUMBER_FIELD_ERROR);
		} else {
			setError(NO_ERROR);
		}
	}
	
	private void initNumberFields() {
		txtTransitionTime.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ignoreNANValue(e);
			}
		});
	}
	
	private void initButtons() {
		cancelButton.addActionListener((event)->{
			close();
		});
		
	}
	
	private void initComponents() {
		jlbTransitionAir = new JLabel("Sân bay trung gian");
		jlbTransitionAir.setPreferredSize(new Dimension(91, 50));
		jlbTransitionAir.setFont(new Font("Noto Sans", Font.BOLD, 18));
		jlbTransitionAir.setHorizontalAlignment(JLabel.CENTER);
		
		panelInfo = new JPanel();
		panelInfo.setBackground(Color.WHITE);
		panelInfo.setBorder(new EmptyBorder(25, 50, 10, 50));
		
		jlbAirportId = new JLabel("Mã sân bay");
		jlbAirportId.setPreferredSize(new Dimension(55, 30));
		jlbAirportId.setFont(new Font("Noto Sans", Font.BOLD, 16));
		
		txtAirportId = new JTextField();
		txtAirportId.setPreferredSize(new Dimension(7, 30));
		txtAirportId.setFont(new Font("Noto Sans", Font.PLAIN, 14));
		txtAirportId.setColumns(10);
		
		jlbTransitionTime = new JLabel("Thời gian dừng");
		jlbTransitionTime.setFont(new Font("Noto Sans", Font.BOLD, 16));
		
		txtTransitionTime = new JTextField();
		txtTransitionTime.setPreferredSize(new Dimension(7, 30));
		txtTransitionTime.setColumns(10);
		txtTransitionTime.setFont(new Font("Noto Sans", Font.PLAIN, 14));
		
		jlbNote = new JLabel("Ghi chú");
		jlbNote.setFont(new Font("Noto Sans", Font.BOLD, 16));
		
		txtNote = new JTextField();
		txtNote.setPreferredSize(new Dimension(7, 60));
		txtNote.setColumns(10);
		
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
		
		initNumberFields();
		initButtons();
	}
	
	private void addComponents() {
		getContentPane().add(jlbTransitionAir, BorderLayout.NORTH);
		
		getContentPane().add(panelInfo, BorderLayout.CENTER);
		panelInfo.setLayout(new GridLayout(0, 1, 0, 10));
		panelInfo.add(jlbAirportId);
		panelInfo.add(txtAirportId);
		panelInfo.add(jlbTransitionTime);
		panelInfo.add(txtTransitionTime);
		panelInfo.add(jlbNote);
		panelInfo.add(txtNote);
		panelInfo.add(warningText);
		
		getContentPane().add(panelButton, BorderLayout.SOUTH);
		panelButton.add(panelCancel);
		panelCancel.setLayout(new GridLayout(0, 1, 1, 1));
		panelCancel.add(cancelButton);
		panelButton.add(okButton);
	}
	
	public void init() {
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 500, 480);
		setResizable(false);
		
		initComponents();
		addComponents();
	}

	/**
	 * Create the dialog.
	 */
	public AirportTransitionForm() {
		init();
	}
	
	public AirportTransitionForm(JFrame owner) {
		super(owner, true);
		init();
	}
	
	public boolean areThereAnyEmptyStarField() {
		
		String airportID = txtAirportId.getText().trim();
		if (null == airportID || airportID.equals("")) {
			return true;
		}
		
		String transitionTime = txtTransitionTime.getText().trim();
		if (null == transitionTime || transitionTime.equals("")) {
			return true;
		}
		
		return false;
	}

	@Override
	public JButton submit() {
		// TODO Auto-generated method stub
		return null;
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
		txtAirportId.setText("");
		txtTransitionTime.setText("");
		txtNote.setText("");
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