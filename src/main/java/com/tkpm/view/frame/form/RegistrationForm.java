package com.tkpm.view.frame.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import com.tkpm.entities.CustomerAccount;
import com.tkpm.entities.User;
import com.tkpm.utils.HashingUtil;

public class RegistrationForm extends JDialog implements FormBehaviour {

	private JPanel contentPanel;
	private JPanel centerPanel;
	private JPanel footerPanel;
	
	private JLabel warningText;	
	
	private List<JLabel> labels;
	private JTextField usernameField;
	private List<JPasswordField> passwordFields;
	private List<JTextField> informationFields;
	
	private JCheckBox showPasswordCheckbox;
	private JButton okButton;
	private JButton cancelButton;
	
	public static final int NO_ERROR = 0;
	public static final int EMPTY_STAR_FIELD_ERROR = 1;
	public static final int EXISTED_USERNAME_ERROR = 2;
	public static final int PASSWORD_MISMATCH_ERROOR = 3;
	public static final int NUMBER_FIELD_ERROR = 4;
	
	
	public RegistrationForm setError(int errorCode) {
		if (0 <= errorCode && errorCode < ERRORS.length) {
			warningText.setText(ERRORS[errorCode]);
		}
		return this;
	}
	
	private void initComponents() {
		
		warningText = new JLabel();					
		
		contentPanel = new JPanel();
		centerPanel = new JPanel();
		footerPanel = new JPanel();
		
		usernameField = new JTextField();
		passwordFields = new ArrayList<>(Arrays.asList(
				new JPasswordField(),
				new JPasswordField()));
		informationFields = new ArrayList<>(Arrays.asList(
				new JTextField(),
				new JTextField(),
				new JTextField()));
		
		showPasswordCheckbox = new JCheckBox("Hiện mật khẩu");
		okButton = new JButton("Đăng ký");
		cancelButton = new JButton("Hủy");
		
		centerPanel = new JPanel();
		labels = new ArrayList<>(Arrays.asList(
				new JLabel("Tên đăng nhập (*)"),
				new JLabel("Mật khẩu (*)"),
				new JLabel("Nhập lại mật khẩu (*)"),
				new JLabel("Tên"),
				new JLabel("Số CMND/CCCD"),
				new JLabel("SĐT")
		));
		
		initCheckbox();
		initButtons();
		initNumberFields();
	}
	
	private void initCheckbox() {
		showPasswordCheckbox.addActionListener((event) -> {
				
			//If the check box is selected
			//	=> Show password
			if (showPasswordCheckbox.isSelected()) {
				passwordFields.forEach(field -> {
					field.setEchoChar((char)0);
				});
			} else {
					
				//If the check box is not selected
				//	=> Hide password by setting echo character with (char)'\u2022'
				passwordFields.forEach(field -> {
					field.setEchoChar('\u2022');
				});
			}
				
		});

	}
	
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
		//i = 1 and i = 2 is number fields
		for (int i = 1; i< informationFields.size(); ++i) {
			informationFields.get(i).addKeyListener(new KeyAdapter() {
				   public void keyTyped(KeyEvent e) {
					   ignoreNANValue(e);
				   }
			});
		}
	}
	
	private void initButtons() {
		cancelButton.addActionListener((event)->{
			close();
		});
		
	}
	
	private void setLayout() {
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		contentPanel.add(footerPanel, BorderLayout.SOUTH);
		footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		footerPanel.add(showPasswordCheckbox);
		footerPanel.add(okButton);
		footerPanel.add(cancelButton);
		
		contentPanel.add(centerPanel, BorderLayout.CENTER);
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		for (int i = 0; i<labels.size(); ++i) {
			String metaLayout = "4, " + (i+2)*2 + ", right, default";
			centerPanel.add(labels.get(i), metaLayout);
		}
		
		centerPanel.add(warningText, "6, 2, center, default");
		centerPanel.add(usernameField, "6, 4, fill, default");
		int offset = 3;
		for (int i = 0; i < passwordFields.size(); ++i) {
			centerPanel.add(passwordFields.get(i), "6, " + (i + offset) * 2 + ", fill, default");
		}
		
		offset += passwordFields.size();
		for (int i = 0; i < informationFields.size(); ++i) {
			centerPanel.add(informationFields.get(i), "6, " + (i + offset) * 2 + ", fill, default");
		}
		
	}
	
	public void init() {
		initComponents();
		setLayout();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 280);
		setResizable(false);
		setContentPane(contentPanel);
		setTitle("Đăng ký");
		
	}
	
	public RegistrationForm() {
		init();
	}
	
	public RegistrationForm(JFrame owner) {
		super(owner, true);
		init();
	}
	
	
	public boolean areThereAnyEmptyStarField() {
		
		String username = usernameField.getText().trim();
		if (null == username || username.equals("")) {
			return true;
		}
		
		String emptyStringEncryption = HashingUtil.passwordEncryption("");
		for (JPasswordField field: passwordFields) {
			if (null == new String(field.getPassword()).trim()) {
				return true;
			}
			String hashing = HashingUtil.passwordEncryption(new String(field.getPassword()).trim());
			if (emptyStringEncryption.equals(hashing)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isPasswordMismatch() {
		
		String hash0 = HashingUtil.passwordEncryption(
				new String(passwordFields.get(0).getPassword()).trim());
		
		String hash1 = HashingUtil.passwordEncryption(
				new String(passwordFields.get(1).getPassword()).trim());
		
		return !hash0.equals(hash1);
	}
	
	@Override
	public User submit() {
		
		User user = new User();
		user.setUsername(usernameField.getText().trim());
		String encryptedPassword = HashingUtil.passwordEncryption(
				new String(passwordFields.get(0).getPassword()).trim());
		user.setEncryptedPassword(encryptedPassword);
		
		CustomerAccount account = new CustomerAccount();
		account.setName(informationFields.get(0).getText());
		account.setIdentityCode(informationFields.get(1).getText());
		account.setPhoneNumber(informationFields.get(2).getText());
		
		user.setAccount(account);
		
		return user;
	}
	
	public void clear() {
		usernameField.setText("");
		passwordFields.forEach(field -> {
			field.setText("");
		});
		informationFields.forEach(field -> {
			field.setText("");
		});
	}

	@Override
	public JButton getSubmitButton() {
		return okButton;
	}
	
	public JButton getCancelButton() {
		return cancelButton;
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
