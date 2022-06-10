package com.tkpm.view.frame.form;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.tkpm.entities.User;
import com.tkpm.utils.HashingUtil;
import com.tkpm.view.component.Button;
import com.tkpm.view.component.FilledButton;
import com.tkpm.view.component.OutlinedButton;
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

public class Login extends BaseFrame implements FormBehaviour {
	
	private JLabel jlbUsername;					//Label for username field
	private JTextField txtUsername;				//Username text field
	private JLabel jlbPassword;					//Label for password field
	private JPasswordField passtxtPassword;		//Password text field
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
		contentPanel.setLayout(null);
		
		Background = new JLabel("");
		Background.setIcon(new ImageIcon(Login.class.getResource("/com/tkpm/view/frame/form/rafiki.jpg")));
		Background.setHorizontalAlignment(SwingConstants.CENTER);
		
		panelLogin = new JPanel();
		panelLogin.setBorder(null);
		panelLogin.setBackground(Color.WHITE);
		panelLogin.setLayout(null);
		
		btnLogin = new FilledButton("Đăng nhập");
		btnLogin.setBorderPainted(false);
		btnLogin.setFont(new Font("Noto Sans", Font.PLAIN, 14));
		
		btnRegistrate = new OutlinedButton("Đăng ký");
		btnRegistrate.setFont(new Font("Noto Sans", Font.PLAIN, 14));
		btnRegistrate.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnRegistrate.setMinimumSize(new Dimension(90, 30));
		btnRegistrate.setMaximumSize(new Dimension(90, 30));
		btnRegistrate.setBorderPainted(false);
		
		jlbUsername = new JLabel("Tên đăng nhập");
		jlbUsername.setBackground(Color.WHITE);
		jlbUsername.setFont(new Font("Noto Sans", Font.BOLD, 16));
		
		jlbPassword = new JLabel("Mật khẩu");
		jlbPassword.setBackground(Color.WHITE);
		jlbPassword.setFont(new Font("Noto Sans", Font.BOLD, 16));
		
		jlbWarningText = new JLabel();					//No warning when start login form
		jlbWarningText.setBackground(Color.WHITE);
		jlbWarningText.setHorizontalAlignment(SwingConstants.CENTER);
		jlbWarningText.setFont(new Font("Noto Sans", Font.PLAIN, 14));
		jlbWarningText.setForeground(Color.RED);		//Warning have red text
		
		txtUsername = new JTextField();
		txtUsername.setBackground(Color.WHITE);
		txtUsername.setFont(new Font("Noto Sans", Font.PLAIN, 14));
		
		passtxtPassword = new JPasswordField();
		passtxtPassword.setFont(new Font("Noto Sans", Font.PLAIN, 14));
		initCheckbox();									//Create and add Show/Hide feature for chckbxShowPassword
	}
	
	//Set size and location of each component
	private void setComponentSizeAndLocation() {
		Background.setBounds(375, 0, 380, 473);
		
		panelLogin.setBounds(0, 0, 380, 473);
		
		btnLogin.setBounds(210, 320, 130, 30);
		btnRegistrate.setBounds(80, 319, 120, 30);
		
		jlbUsername.setBounds(80, 120, 260, 30);
		jlbPassword.setBounds(80, 200, 260, 30);
	    jlbWarningText.setBounds(80, 365, 260, 30);
	    txtUsername.setBounds(80, 155, 260, 30);
	    passtxtPassword.setBounds(80, 235, 260, 30);
	    chckbxShowPassword.setBounds(80, 275, 150, 30);
	}
	
	//Connect all components into getContentPane()
	private void addComponents() {
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.add(panelLogin);
		contentPanel.add(Background);
		panelLogin.add(jlbUsername);
		panelLogin.add(jlbPassword);
		panelLogin.add(txtUsername);
		panelLogin.add(passtxtPassword);
		panelLogin.add(chckbxShowPassword);
		panelLogin.add(btnLogin);
		panelLogin.add(btnRegistrate);
		panelLogin.add(jlbWarningText);
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
