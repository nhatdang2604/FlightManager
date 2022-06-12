package com.tkpm.view.frame.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DateTimePicker;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.tkpm.entities.Airport;
import com.tkpm.entities.Flight;
import com.tkpm.entities.FlightDetail;
import com.tkpm.entities.Transition;
import com.tkpm.view.feature_view.table.TransitionCRUDTableView;

public class FlightDetailForm extends JDialog {

	final protected int HEIGHT = 500;
	final protected int WIDTH = 600;
	
	private JPanel centerPanel;
	private JPanel footerPanel;
	private JPanel contentPane;
	
	private List<JLabel> labels;
	private List<JTextField> fields; 
	
	private JButton closeButton;
	
	private Flight model;
	private JLabel warningText;	
	
	private static final String[] ERRORS = {
			"",
	};
	
	private static final int NO_ERROR = 0;

	
	public FlightDetailForm setError(int errorCode) {
		if (0 <= errorCode && errorCode < ERRORS.length) {
			warningText.setText(ERRORS[errorCode]);
		}
		return this;
	}
	
	private void initComponents() {
		warningText = new JLabel();					
		warningText.setForeground(Color.RED);		//Warning have red text
		
		contentPane = new JPanel();
		
		footerPanel = new JPanel();
		closeButton = new JButton("Đóng");
		
		centerPanel = new JPanel();
		labels = new ArrayList<>(Arrays.asList(
				new JLabel("Mã chuyến bay"),
				new JLabel("Sân bay đi"),
				new JLabel("Sân bay đến"),
				new JLabel("Ngày - giờ"),
				new JLabel("Thời gian bay"),
				new JLabel("Số lượng ghế hạng 1"),
				new JLabel("Số lượng ghế hạng 2"),
				new JLabel("Giá vé hạng 1"),
				new JLabel("Giá vé hạng 2")));
		
		
		//Init numeric fields
		fields = new ArrayList<>();
		int numberOfField = labels.size();
		for (int i = 0; i < numberOfField; ++i) {
			fields.add(new JTextField());
			fields.get(i).setEditable(false);
		}
		
		
		
		initButtons();
	}
	
	private void initButtons() {
		closeButton.addActionListener((event)->{
			this.dispose();
		});
		
	}
	
	private void setLayout() {
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//Center setup
		//JScrollPane centerScroll = new JScrollPane(centerPanel);
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		
		for (int i = 0; i<labels.size(); ++i) {
			String metaLayout = "4, " + (i+2)*2 + ", right, default";
			centerPanel.add(labels.get(i), metaLayout);
		}
		
		centerPanel.add(warningText, "6, 2, center, default");
		int offset = 2;
		for (int i = 0; i < fields.size(); ++i) {
			centerPanel.add(fields.get(i), "6, " + (i + offset) * 2 + ", fill, default");
		}
		offset += fields.size();
		
		
		//Footer setup
		contentPane.add(footerPanel, BorderLayout.SOUTH);
		footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		footerPanel.add(closeButton);
				
	}
	
	public void init() {
		initComponents();
		setLayout();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		setContentPane(contentPane);
		
	}
	
	private void initModel() {
		model = new Flight();
		model.setTransitions(new TreeSet<>());
		model.setReservations(new TreeSet<>());
		model.setTickets(new TreeSet<>());
		model.setDetail(new FlightDetail());
		model.setDateTime(null);
		model.setDepartureAirport(new Airport());
		model.setArrivalAirport(new Airport());
		
	}
	

	public FlightDetailForm(Flight model, JFrame owner) {
		super(owner, true);
		init();
		setModel(model);
		setSize(WIDTH, HEIGHT);
	}
	
	public JButton getCloseButton() {return closeButton;}
	
	public JDialog setModel(Flight model) {
		this.model = model;
		
		if (null != model) {
			fields.get(0).setText(model.getId().toString());
			fields.get(1).setText(model.getDepartureAirport().getName());
			fields.get(2).setText(model.getArrivalAirport().getName());
			fields.get(3).setText(model.getDateTime().toString());
			FlightDetail detail = model.getDetail();
			fields.get(4).setText(detail.getFlightTime().toString() + " phút");
			fields.get(5).setText(detail.getNumberOfFirstClassSeat().toString());
			fields.get(6).setText(detail.getNumberOfSecondClassSeat().toString());
			fields.get(7).setText(detail.getPriceOfFirstClassSeat().toString());
			fields.get(8).setText(detail.getPriceOfSecondClassSeat().toString());
		}
		
		return this;
	}
	
	
	public Flight submit() {
		return null;
	}
	

	public JButton getSubmitButton() {
		return closeButton;
	}
	
	public static void main(String[] args) {
		FlightDetailForm form = new FlightDetailForm(null, null);
		form.setVisible(true);
	}
}
