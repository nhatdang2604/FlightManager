package com.tkpm.view.frame.form;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.tkpm.entities.User;
import com.tkpm.utils.HashingUtil;
import com.tkpm.view.component.Button;
import com.tkpm.view.component.FilledButton;
import com.tkpm.view.component.OutlinedButton;
import com.tkpm.view.component.PasswordField;
import com.tkpm.view.component.TextField;
import com.tkpm.view.frame.BaseFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Canvas;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;

public class Login extends BaseFrame implements FormBehaviour {
	
	private TextField txtUsername;				//Username text field
	private PasswordField passtxtPassword;		//Password text field
	private JCheckBox chckbxShowPassword;		//Checkbox to show/hide password
	private FilledButton btnLogin;				//Login button
	private OutlinedButton btnRegistrate;		//Registrate button
	
	private JPanel contentPanel;
	private JLabel Background;
	private JPanel panelLogin;
	
	//Display when: 
	//	1.) Wrong password or username: Type = 0
	private JLabel jlbWarningText;		
	
	public static final int WRONG_ACCOUNT_ERROR = 0;
	private static final String[] ERRORS = {
		"Sai mật khẩu hoặc tên đăng nhập"
	};
	private JPanel infoLogin;
	private JPanel panelButton;
		
	//Create and add Show/Hide feature for chckbxShowPassword
	private void initCheckbox() {
		chckbxShowPassword = new JCheckBox("Hiện mật khẩu");
		chckbxShowPassword.setBackground(Color.WHITE);
		chckbxShowPassword.setFont(new Font("Noto Sans", Font.PLAIN, 14));
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
		contentPanel = new JPanel();
		contentPanel.setPreferredSize(new Dimension(750, 465));
		contentPanel.setBackground(Color.WHITE);
		
		Background = new JLabel("");
		Background.setIcon(new ImageIcon(Login.class.getResource("/com/tkpm/view/frame/form/rafiki.jpg")));
		Background.setHorizontalAlignment(SwingConstants.CENTER);
		
		panelLogin = new JPanel();
		panelLogin.setBorder(new EmptyBorder(50, 30, 50, 30));
		panelLogin.setBackground(Color.WHITE);
		panelLogin.setLayout(new BorderLayout(0, 0));
		
		infoLogin = new JPanel();
		infoLogin.setBorder(new EmptyBorder(80, 10, 0, 10));
		infoLogin.setBackground(Color.WHITE);
		infoLogin.setLayout(new GridLayout(5, 0, 0, 10));
		
		panelButton = new JPanel();
		panelButton.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelButton.setBackground(Color.WHITE);
		panelButton.setLayout(new GridLayout(0, 2, 30, 0));
		
		btnLogin = new FilledButton("Đăng nhập");
		btnLogin.setFont(new Font("Noto Sans", Font.PLAIN, 14));
		
		btnRegistrate = new OutlinedButton("Đăng ký");
		btnRegistrate.setFont(new Font("Noto Sans", Font.PLAIN, 14));
		btnRegistrate.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnRegistrate.setBorderPainted(false);
		
		jlbWarningText = new JLabel();					//No warning when start login form
		jlbWarningText.setBackground(Color.WHITE);
		jlbWarningText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbWarningText.setFont(new Font("Noto Sans", Font.PLAIN, 14));
		jlbWarningText.setForeground(Color.RED);		//Warning have red text
		
		txtUsername = new TextField("Tên đăng nhập");
		passtxtPassword = new PasswordField("Mật khẩu");
		
		initCheckbox();									//Create and add Show/Hide feature for chckbxShowPassword
	}
	
	//Set size and location of each component
	private void setComponentSizeAndLocation() {
	}
	
	//Connect all components into getContentPane()
	private void addComponents() {
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		contentPanel.add(panelLogin);
		contentPanel.add(Background);
		panelLogin.add(infoLogin, BorderLayout.CENTER);
		infoLogin.add(txtUsername);
		infoLogin.add(passtxtPassword);
		infoLogin.add(chckbxShowPassword);
		panelButton.add(btnRegistrate);
		panelButton.add(btnLogin);
		infoLogin.add(panelButton);
		infoLogin.add(jlbWarningText);
	}
	
	public Login() {
		this.setTitle("Đăng nhập");
		this.getContentPane().setBackground(Color.WHITE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		initComponents();
		setComponentSizeAndLocation();
		addComponents();
		
		this.pack();
		
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
	public Button getSubmitButton() {return btnLogin;}
	public JButton getRegistrateButton() {return btnRegistrate;}

	@Override
	public FormBehaviour setError(int errorCode) {
		if (0 <= errorCode && errorCode < ERRORS.length) {
			jlbWarningText.setText(ERRORS[errorCode]);
		}
		
		return this;
	}

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
