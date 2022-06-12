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

public class FlightForm extends JDialog {

	final protected int HEIGHT = 500;
	final protected int WIDTH = 600;
	
	private JPanel centerPanel;
	private JPanel footerPanel;
	private JPanel contentPane;
	
	private List<JLabel> labels;
	private List<JComboBox<Airport>> airportComboBoxes;	//departure airport / arrival airport
	private DateTimePicker flightDateTimePicker;
	
	//flight time / first class seat size/ second class seat size/ first class seat price / second class seat price
	private List<JTextField> numericTextFields;	
	
	private JButton addTransitionButton;
	private JButton deleteTransitionButton;
	private TransitionCRUDTableView table;
	private AirportTransitionForm transitionForm;
	
	private JButton okButton;
	private JButton cancelButton;
	
	private Flight model;
	private JLabel warningText;	
	
	private static final String[] ERRORS = {
			"",
			"Có ít nhất một ô không có thông tin",
			"Ô phải mang giá trị số nguyên dương",
	};
	
	private static final int NO_ERROR = 0;
	private static final int EMPTY_FIELD_ERROR = 1;
	public static final int NUMBER_FIELD_ERROR = 2;
	
	public FlightForm setError(int errorCode) {
		if (0 <= errorCode && errorCode < ERRORS.length) {
			warningText.setText(ERRORS[errorCode]);
		}
		return this;
	}
	
	//Ignore not an number value from an event
	//	And open the flag of nan error in number field
	private void ignoreNANValue(KeyEvent event) {
		char character = event.getKeyChar();
		if ((character < '0') || (character > '9') || (character != KeyEvent.VK_BACK_SPACE)) {
			event.consume();
			setError(NUMBER_FIELD_ERROR);
		} else {
			setError(NO_ERROR);
		}
	}
	
	private void initComponents() {
		warningText = new JLabel();					
		warningText.setForeground(Color.RED);		//Warning have red text
		
		contentPane = new JPanel();
		
		footerPanel = new JPanel();
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		addTransitionButton = new JButton("Thêm");
		deleteTransitionButton = new JButton("Xóa");
		
		centerPanel = new JPanel();
		labels = new ArrayList<>(Arrays.asList(
				new JLabel("Sân bay đi"),
				new JLabel("Sân bay đến"),
				new JLabel("Ngày - giờ"),
				new JLabel("Thời gian bay"),
				new JLabel("Số lượng ghế hạng 1"),
				new JLabel("Số lượng ghế hạng 2"),
				new JLabel("Giá vé hạng 1"),
				new JLabel("Giá vé hạng 2")));
		
		//Init datetime picker
		flightDateTimePicker = new DateTimePicker();
		
		//Init airport combo boxes
		airportComboBoxes = new ArrayList<>();
		int numberOfAirportComboBoxes = 2;
		for (int i = 0; i < numberOfAirportComboBoxes; ++i) {
			airportComboBoxes.add(new JComboBox<>());
		}
			
		//Init numeric fields
		numericTextFields = new ArrayList<>();
		int numberOfNumericField = 5;
		for (int i = 0; i < numberOfNumericField; ++i) {
			numericTextFields.add(new JTextField());
		}
		
		table = new TransitionCRUDTableView();
		transitionForm = new AirportTransitionForm(this);
		
		initButtons();
		initNumericFields();
	}
	
	private void initNumericFields() {
		for (JTextField field: numericTextFields) {
			field.addKeyListener(new KeyAdapter() {
				   public void keyTyped(KeyEvent e) {
					   ignoreNANValue(e);
				   }
			});
		}
	}
	
	private void initButtons() {
		cancelButton.addActionListener((event)->{
			this.dispose();
		});
		
		addTransitionButton.addActionListener(event -> {
			transitionForm.clear();
			transitionForm.open();
		});
		
		transitionForm.getSubmitButton().addActionListener((event) -> {
			if (!transitionForm.areThereAnyEmptyStarField()) {
				Transition transition = transitionForm.submit();
				
				//There is no error
				if (null != transition) {
					table.addTransition(transition);
					transitionForm.close();
				}
			} else {
				transitionForm.setError(AirportTransitionForm.EMPTY_STAR_FIELD_ERROR);
			}
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		
		for (int i = 0; i<labels.size(); ++i) {
			String metaLayout = "4, " + (i+2)*2 + ", right, default";
			centerPanel.add(labels.get(i), metaLayout);
		}
		
		centerPanel.add(warningText, "6, 2, center, default");
		int offset = 2;
		for (int i = 0; i < airportComboBoxes.size(); ++i) {
			centerPanel.add(airportComboBoxes.get(i), "6, " + (i + offset) * 2 + ", fill, default");
		}
		offset += airportComboBoxes.size();
		centerPanel.add(flightDateTimePicker, "6, " + offset * 2 + ", fill, default");
		++offset;
		for (int i = 0; i < numericTextFields.size(); ++i) {
			centerPanel.add(numericTextFields.get(i), "6, " + (i + offset) * 2 + ", fill, default");
		}
		
		
		//Footer setup
		contentPane.add(footerPanel, BorderLayout.SOUTH);
		footerPanel.setLayout(new BorderLayout());
		JPanel controlButtonPanel = new JPanel();
		controlButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		controlButtonPanel.add(addTransitionButton);
		controlButtonPanel.add(deleteTransitionButton);
		JPanel confirmButtonPanel = new JPanel();
		confirmButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		confirmButtonPanel.add(okButton);
		confirmButtonPanel.add(cancelButton);
		JScrollPane tableScroll = new JScrollPane(table);
		tableScroll.setPreferredSize(new Dimension(200, 100));
		footerPanel.add(controlButtonPanel, BorderLayout.NORTH);
		footerPanel.add(tableScroll, BorderLayout.CENTER);
		footerPanel.add(confirmButtonPanel, BorderLayout.SOUTH);
				
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
		model.setTickets(new TreeSet<>());
		model.setDetail(new FlightDetail());
		model.setDateTime(null);
		model.setDepartureAirport(new Airport());
		model.setArrivalAirport(new Airport());
		
	}
	

	public FlightForm(Flight model, JFrame owner) {
		super(owner, true);
		init();
		setModel(model);
		setSize(WIDTH, HEIGHT);
	}
	
	public FlightForm(JFrame owner) {
		super(owner, true);
		init();
		initModel();
		setSize(WIDTH, HEIGHT);
	}
	
	
	public JButton getOkButton() {return okButton;}
	public JDialog setModel(Flight model) {
		this.model = model;
		
		airportComboBoxes.get(0).setSelectedItem(model.getDepartureAirport());
		airportComboBoxes.get(1).setSelectedItem(model.getArrivalAirport());
		
		flightDateTimePicker.setDateTimePermissive(model.getDateTime());
		
		FlightDetail detail = model.getDetail();
		numericTextFields.get(0).setText(detail.getFlightTime().toString());
		numericTextFields.get(1).setText(detail.getNumberOfFirstClassSeat().toString());
		numericTextFields.get(2).setText(detail.getNumberOfSecondClassSeat().toString());
		numericTextFields.get(3).setText(detail.getPriceOfFirstClassSeat().toString());
		numericTextFields.get(4).setText(detail.getPriceOfSecondClassSeat().toString());
	
		table.setTransitions(new ArrayList<>(model.getTransitions()));
		table.update();
		
		return this;
	}
	public FlightForm setAirports(List<Airport> airports) {
		for (JComboBox<Airport> cb: airportComboBoxes) {
			cb.removeAll();
			airports.forEach(airport -> cb.addItem(airport));
		}
		
		return this;
	}
	
	public Flight submit() {
		
		model.setDepartureAirport((Airport) airportComboBoxes.get(0).getSelectedItem());
		model.setArrivalAirport((Airport) airportComboBoxes.get(1).getSelectedItem());
		
		model.setDateTime(flightDateTimePicker.getDateTimePermissive());
		
		FlightDetail detail = model.getDetail();
		detail.setFlightTime(Integer.parseInt(numericTextFields.get(0).getText()));
		detail.setNumberOfFirstClassSeat(Integer.parseInt(numericTextFields.get(1).getText()));
		detail.setNumberOfSecondClassSeat(Integer.parseInt(numericTextFields.get(2).getText()));
		detail.setPriceOfFirstClassSeat(Integer.parseInt(numericTextFields.get(3).getText()));
		detail.setPriceOfSecondClassSeat(Integer.parseInt(numericTextFields.get(4).getText()));
		
		Set<Transition> transitions = new TreeSet<>(table.getTransitions());
		model.setTransitions(transitions);
		
		return model;
	}
	
	public AirportTransitionForm getTransitionForm() {return transitionForm;}
	public TransitionCRUDTableView getTable() {return table;}
	
	public JButton getSubmitButton() {
		return okButton;
	}
	
	public static void main(String[] args) {
		FlightForm form = new FlightForm(null);
		form.setVisible(true);
	}
}
