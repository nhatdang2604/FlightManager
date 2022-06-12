package com.tkpm.view.frame.form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.nio.channels.NonWritableChannelException;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Dimension;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import com.tkpm.entities.CustomerAccount;
import com.tkpm.entities.User;
import com.tkpm.utils.HashingUtil;
import com.tkpm.view.component.FilledButton;
import com.tkpm.view.component.OutlinedButton;
import com.tkpm.view.component.PasswordField;
import com.tkpm.view.component.TextField;
import javax.swing.SwingConstants;

public class Registration extends JFrame implements FormBehaviour{

	private JPanel contentPane;
	private JPanel panelRegistrate;
	private JPanel panelInfo;
	private JPanel panelButton;
	
	private JLabel background;
	private JCheckBox showPasswordCheckbox;
	
	private TextField txtUsername;
	private PasswordField passtxtPassword;
	private PasswordField passtxtRetypePassword;
	private TextField txtName;
	private TextField txtIdentity;
	private TextField txtMobileNumber;
	
	private OutlinedButton cancelButton;
	private FilledButton okButton;
	
	public static final int NO_ERROR = 0;
	public static final int EMPTY_STAR_FIELD_ERROR = 1;
	public static final int EXISTED_USERNAME_ERROR = 2;
	public static final int PASSWORD_MISMATCH_ERROOR = 3;
	public static final int NUMBER_FIELD_ERROR = 4;
	
	private static final String[] ERRORS = {
			"",
			"Có ít nhất một ô (*) bị trống",
			"Tên đăng nhập đã tồn tại",
			"<html><body>Nhập lại mật khẩu và<br>mật khẩu mới không trùng khớp</body></html>",
			"CMND/CCCD và SĐT phải là số"
	};
	private JLabel warningText;
	
	private void initCheckbox() {
		showPasswordCheckbox.addActionListener((event) -> {
				
			//If the check box is selected
			//	=> Show password
			if (showPasswordCheckbox.isSelected()) {
				passtxtPassword.setEchoChar((char)0);
				passtxtRetypePassword.setEchoChar((char)0);
			} else {
					
				//If the check box is not selected
				//	=> Hide password by setting echo character with (char)'\u2022'
				passtxtPassword.setEchoChar('\u2022');
				passtxtRetypePassword.setEchoChar('\u2022');
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
		txtIdentity.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				ignoreNANValue(e);
			}
		});
		
		txtMobileNumber.addKeyListener(new KeyAdapter() {
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
		
		background = new JLabel("");
		background.setBackground(Color.WHITE);
		background.setIcon(new ImageIcon(Registration.class.getResource("/com/tkpm/view/frame/form/rafiki.jpg")));
		
		panelRegistrate = new JPanel();
		panelRegistrate.setBackground(Color.WHITE);
		
		panelInfo = new JPanel();
		panelInfo.setBorder(new EmptyBorder(20, 80, 10, 40));
		panelInfo.setBackground(Color.WHITE);
		
		panelButton = new JPanel();
		panelButton.setBorder(new EmptyBorder(8, 0, 8, 0));
		panelButton.setPreferredSize(new Dimension(260, 30));
		panelButton.setBackground(Color.WHITE);
		
		txtUsername = new TextField("Tên đăng nhập (*)");
		txtUsername.setPreferredSize(new Dimension(260, 50));
		txtUsername.setColumns(10);
		
		passtxtPassword = new PasswordField("Mật khẩu (*)");
		passtxtPassword.setPreferredSize(new Dimension(260, 50));
		
		passtxtRetypePassword = new PasswordField("Nhập lại mật khẩu (*)");
		passtxtRetypePassword.setPreferredSize(new Dimension(260, 50));
		
		txtName = new TextField("Họ và tên");
		txtName.setPreferredSize(new Dimension(260, 50));
		txtName.setColumns(10);
		
		txtIdentity = new TextField("CMND/CCCD");
		txtIdentity.setPreferredSize(new Dimension(260, 50));
		txtIdentity.setColumns(10);
		
		txtMobileNumber = new TextField("Số điện thoại");
		txtMobileNumber.setPreferredSize(new Dimension(260, 50));
		txtMobileNumber.setColumns(10);
		
		showPasswordCheckbox = new JCheckBox("Hiện mật khẩu");
		showPasswordCheckbox.setPreferredSize(new Dimension(260, 30));
		showPasswordCheckbox.setBackground(Color.WHITE);
		showPasswordCheckbox.setFont(new Font("Noto Sans", Font.PLAIN, 14));
		
		cancelButton = new OutlinedButton("Huỷ");
		cancelButton.setBorderPainted(false);
		cancelButton.setFont(new Font("Noto Sans", Font.PLAIN, 14));
		
		okButton = new FilledButton("Đăng ký");
		okButton.setBorderPainted(false);
		okButton.setFont(new Font("Noto Sans", Font.PLAIN, 14));
		
		warningText = new JLabel("");
		warningText.setFont(new Font("Noto Sans", Font.PLAIN, 16));
		warningText.setForeground(Color.RED);
		
		initCheckbox();
		initButtons();
		initNumberFields();
	}
	
	private void setLayout() {
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		panelRegistrate.setLayout(new BorderLayout(0, 0));
		panelInfo.setLayout(new GridLayout(9, 1, 0, 10));
		panelButton.setLayout(new GridLayout(0, 2, 20, 0));
		
		panelInfo.add(txtUsername);
		panelInfo.add(passtxtPassword);
		panelInfo.add(passtxtRetypePassword);
		panelInfo.add(txtName);
		panelInfo.add(txtIdentity);
		panelInfo.add(txtMobileNumber);
		panelInfo.add(showPasswordCheckbox);
		panelInfo.add(panelButton);
		panelButton.add(cancelButton);
		panelButton.add(okButton);
		panelInfo.add(warningText);
		
		contentPane.add(panelInfo);
		contentPane.add(background);
	}

	
	/**
	 * Create the frame.
	 */
	public Registration() {
		setTitle("Đăng ký");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(20, 50, 20, 50));
		setContentPane(contentPane);
		
		initComponents();
		setLayout();
		
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
	}
	
	public boolean areThereAnyEmptyStarField() {
		
		String username = txtUsername.getText().trim();
		if (null == username || username.equals("")) {
			return true;
		}
		
		String emptyStringEncryption = HashingUtil.passwordEncryption("");
		if (null == new String(passtxtPassword.getPassword()).trim()) {
			return true;
		}
		String hashing = HashingUtil.passwordEncryption(new String(passtxtPassword.getPassword()).trim());
		if (emptyStringEncryption.equals(hashing)) {
			return true;
		}
		
		if (null == new String(passtxtRetypePassword.getPassword()).trim()) {
			return true;
		}
		hashing = HashingUtil.passwordEncryption(new String(passtxtRetypePassword.getPassword()).trim());
		if (emptyStringEncryption.equals(hashing)) {
			return true;
		}
		
		return false;
	}
	
	public boolean isPasswordMismatch() {
		
		String hash0 = HashingUtil.passwordEncryption(
				new String(passtxtPassword.getPassword()).trim());
		
		String hash1 = HashingUtil.passwordEncryption(
				new String(passtxtRetypePassword.getPassword()).trim());
		
		return !hash0.equals(hash1);
	}

	@Override
	public Object submit() {
		User user = new User();
		user.setUsername(txtUsername.getText().trim());
		String encryptedPassword = HashingUtil.passwordEncryption(
				new String(passtxtPassword.getPassword()).trim());
		user.setEncryptedPassword(encryptedPassword);
		
		CustomerAccount account = new CustomerAccount();
		account.setName(txtName.getText());
		account.setIdentityCode(txtIdentity.getText());
		account.setPhoneNumber(txtMobileNumber.getText());
		
		user.setAccount(account);
		
		return user;
	}

	@Override
	public JButton getSubmitButton() { return okButton;	}
	public JButton getCancelButton() { return cancelButton; }

	@Override
	public FormBehaviour setError(int errorCode) {
		if (0 <= errorCode && errorCode < ERRORS.length) {
			warningText.setText(ERRORS[errorCode]);
		}
		return this;
	}

	@Override
	public void clear() {
		txtUsername.setText("");
		passtxtPassword.setText("");
		passtxtRetypePassword.setText("");
		txtName.setText("");
		txtIdentity.setText("");
		txtMobileNumber.setText("");
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
