package com.tkpm.view.frame.form;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.tkpm.entities.User;
import com.tkpm.service.AccountService;
import com.tkpm.utils.HashingUtil;
import com.tkpm.view.frame.BaseFrame;

public class LoginForm extends BaseFrame implements FormBehaviour {
	
	//Main panel to add components into
	private JPanel contentPane;
	
	//Components
	private JButton btnLogin;				//Login button
	private JButton btnRegistrate;			//Registrate button
	private JTextField txtUsername;			//Username text field
	private JPasswordField passtxtPassword;	//Password text field
	private JLabel jlbUsername;				//Label for username field
	private JLabel jlbPassword;				//Label for password field
	private JCheckBox chckbxShowPassword;	//Checkbox to show/hide password
	
	//Display when: 
	//	1.) Wrong password or username: Type = 0
	private JLabel jlbWarningText;		
	
	public static final int WRONG_ACCOUNT_ERROR = 0;
	private static final String[] ERRORS = {
		"Sai mật khẩu hoặc tên đăng nhập"
	};
	
	//Change the warning text of jlbWarningText;
	public FormBehaviour setError(int errorCode) {
		if (0 <= errorCode && errorCode < ERRORS.length) {
			jlbWarningText.setText(ERRORS[errorCode]);
		}
		
		return this;
	}
	
	//Create and add Show/Hide feature for chckbxShowPassword
	private void initCheckbox() {
		chckbxShowPassword = new JCheckBox("Hiện mật khẩu");
		chckbxShowPassword.addActionListener((event) -> {
				
			//If the check box is selected
			//	=> Show password of passtxtPassword
			if (chckbxShowPassword.isSelected()) {
				passtxtPassword.setEchoChar((char)0);
			} else {
					
				//If the check box is not selected
				//	=> Hide password of passtxtPassword
				// by setting echo character with (char)'\u2022'
				passtxtPassword.setEchoChar('\u2022');
			}
				
		});

	}
	
	//Create all components;
	private void initComponents() {
		btnLogin = new JButton("Đăng nhập");
		btnRegistrate = new JButton("Đăng ký");
		
		jlbUsername = new JLabel("Tên đăng nhập:");
		jlbPassword = new JLabel("Mật khẩu:");
		
		jlbWarningText = new JLabel();					//No warning when start login form
		jlbWarningText.setForeground(Color.RED);		//Warning have red text
		
		txtUsername = new JTextField();
		passtxtPassword = new JPasswordField();
		initCheckbox();									//Create and add Show/Hide feature for chckbxShowPassword
	}
	
	
	
	//Connect all components into contentPane
	private void addComponents() {
		contentPane.add(btnLogin);
		contentPane.add(btnRegistrate);
		contentPane.add(jlbUsername);
		contentPane.add(jlbPassword);
		contentPane.add(jlbWarningText);
		contentPane.add(txtUsername);
		contentPane.add(passtxtPassword);
		contentPane.add(chckbxShowPassword);
	}
	
	//Create and set properties of the login form
	private void initLoginForm() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(10, 10, 420, 208);
		this.setTitle("Đăng nhập");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		this.setResizable(false);
	}
	
	public LoginForm() {
		initLoginForm();
		initComponents();
		setComponentSizeAndLocation();
		addComponents();
		
		this.setContentPane(contentPane);
	}

	@Override
	public User submit() {
		
		String username = txtUsername.getText().trim();
		String encryptedPassword = (null == passtxtPassword.getPassword()?
				HashingUtil.passwordEncryption(""):
				HashingUtil.passwordEncryption(new String(passtxtPassword.getPassword())));
		
		User user = new User();
		user.setUsername(username);
		user.setEncryptedPassword(encryptedPassword);
		
		return user;
	}
	
	@Override
	public JButton getSubmitButton() {return btnLogin;}
	public JButton getRegistrateButton() {return btnRegistrate;}
	
	@Override
	public void clear() {
		txtUsername.setText("");
		passtxtPassword.setText("");
		jlbWarningText.setText("");
	}
	
	@Override
	public void close() {
		super.close();
	}
	
}
