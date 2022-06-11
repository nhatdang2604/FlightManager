package com.tkpm.view.frame.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.tkpm.entities.AdminAccount;
import com.tkpm.entities.Airport;
import com.tkpm.entities.CustomerAccount;
import com.tkpm.entities.Flight;
import com.tkpm.entities.FlightDetail;
import com.tkpm.entities.ManagerAccount;
import com.tkpm.entities.Transition;
import com.tkpm.entities.User;
import com.tkpm.entities.User.USER_ROLE;
import com.tkpm.view.feature_view.table.TransitionCRUDTableView;

public class UpdateAccountForm extends JDialog {

	final protected int HEIGHT = 500;
	final protected int WIDTH = 600;
	
	private JPanel centerPanel;
	private JPanel footerPanel;
	private JPanel contentPane;
	
	private List<JLabel> labels;
	private List<JTextField> textFields;
	private List<JTextField> numericTextFields;	
	private JComboBox<USER_ROLE> roleComboBox;
	
	private JButton okButton;
	private JButton cancelButton;
	
	private User model;
	private JLabel warningText;	
	
	private static final String[] ERRORS = {
			"",
			"Có ít nhất một ô không có thông tin",
			"Ô phải mang giá trị số",
			"Tên đăng nhập đã tồn tại",

	};
	
	private static final int NO_ERROR = 0;
	private static final int EMPTY_FIELD_ERROR = 1;
	public static final int NUMBER_FIELD_ERROR = 2;
	public static final int EXISTED_USERNAME_ERROR = 3;
	
	public UpdateAccountForm setError(int errorCode) {
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
		
		centerPanel = new JPanel();
		labels = new ArrayList<>(Arrays.asList(
				new JLabel("Tên đăng nhập (*)"),
				new JLabel("Mật khẩu đã mã hóa"),
				new JLabel("Chức vụ"),
				new JLabel("Họ và tên"),
				new JLabel("CMND/CCCD"),
				new JLabel("Số điện thoại")));
		
		//Init text fields
		textFields = new ArrayList<>();
		int numberOfTextField = 3;
		for (int i = 0; i < numberOfTextField; ++i) {
			textFields.add(new JTextField());
		}	
		textFields.get(1).setEditable(false);	//make encrypted password field uneditable
		
		
		//Init combo box
		roleComboBox = new JComboBox<>();
		for (USER_ROLE role: USER_ROLE.values()) {
			roleComboBox.addItem(role);
		}
	
		
		//Init numeric fields
		numericTextFields = new ArrayList<>();
		int numberOfNumericField = 2;
		for (int i = 0; i < numberOfNumericField; ++i) {
			numericTextFields.add(new JTextField());
		}
	
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
				FormSpecs.DEFAULT_ROWSPEC}));
		
		
		for (int i = 0; i<labels.size(); ++i) {
			String metaLayout = "4, " + (i+2)*2 + ", right, default";
			centerPanel.add(labels.get(i), metaLayout);
		}
		
		int offset = 1;
		centerPanel.add(warningText, "6, " + offset * 2 + ", center, default");
		++offset;
		centerPanel.add(textFields.get(0), "6, " + offset * 2 + ", fill, default");	//add username
		++offset;
		centerPanel.add(textFields.get(0), "6, " + offset * 2 + ", fill, default");	//add encrypted password
		++offset;
		centerPanel.add(roleComboBox, "6, " + offset * 2 + ", fill, default");	//add role
		++offset;
		centerPanel.add(textFields.get(1), "6, " + offset * 2 + ", fill, default");	//add name
		++offset;
		for (int i = 0; i < numericTextFields.size(); ++i) {
			centerPanel.add(numericTextFields.get(i), "6, " + (i + offset) * 2 + ", fill, default");
		}
		
		
		//Footer setup
		contentPane.add(footerPanel, BorderLayout.SOUTH);
		footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		footerPanel.add(okButton);
		footerPanel.add(cancelButton);
				
	}
	
	public void init() {
		initComponents();
		setLayout();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		setContentPane(contentPane);
		
	}
	
	private void initModel() {
		model = new User();
		model.setAccount(new CustomerAccount());
		
	}
	

	public UpdateAccountForm(User model, JFrame owner) {
		super(owner, true);
		init();
		setModel(model);
		setSize(WIDTH, HEIGHT);
	}
	
	public UpdateAccountForm(JFrame owner) {
		super(owner, true);
		init();
		initModel();
		setSize(WIDTH, HEIGHT);
	}
	
	
	public JButton getOkButton() {return okButton;}
	public JDialog setModel(User model) {
		this.model = model;
		
		textFields.get(0).setText(model.getUsername());
		textFields.get(1).setText(model.getEncryptedPassword());
		
		USER_ROLE role = USER_ROLE.convertStringToUSER_ROLE(model.getRole());
		roleComboBox.setSelectedItem(role);
		
		String name, identity_code, phone;
		name = identity_code = phone = null;
		
		if (role.equals(USER_ROLE.Admin)) {
			AdminAccount acc = (AdminAccount) model.getAccount();
			name = acc.getName();
			identity_code = acc.getIdentityCode();
			phone = acc.getPhoneNumber();
			
		} else if (role.equals(USER_ROLE.Customer)) {
			CustomerAccount acc = (CustomerAccount) model.getAccount();
			name = acc.getName();
			identity_code = acc.getIdentityCode();
			phone = acc.getPhoneNumber();
			
		} else if (role.equals(USER_ROLE.Manager)) {
			ManagerAccount  acc = (ManagerAccount) model.getAccount();
			name = acc.getName();
			identity_code = acc.getIdentityCode();
			phone = acc.getPhoneNumber();
			
		}
		
		textFields.get(2).setText(name);
		numericTextFields.get(0).setText(identity_code);
		numericTextFields.get(1).setText(phone);
		
		return this;
	}
	
	
	
	public UpdateAccountForm setAirports(List<Airport> airports) {
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
		UpdateAccountForm form = new UpdateAccountForm(null);
		form.setVisible(true);
	}
}
