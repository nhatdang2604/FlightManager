package com.tkpm.controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.tkpm.entities.User;
import com.tkpm.entities.User.USER_ROLE;
import com.tkpm.service.UserService;
import com.tkpm.view.frame.AdminMainFrame;
import com.tkpm.view.frame.CustomerMainFrame;
import com.tkpm.view.frame.ManagerMainFrame;
import com.tkpm.view.frame.form.Login;
import com.tkpm.view.frame.form.Registration;

public class LoginController{

	//Forms
	private Login loginForm;
	private Registration registrationForm;
	
	//Services
	private UserService userService;

	//Controller
	private CustomerController controller;

	private CustomerController getControllerBaseOnRole(User user) {
		if (USER_ROLE.Customer.equals(USER_ROLE.convertStringToUSER_ROLE(user.getRole()))) {
			
			controller = new CustomerController(new CustomerMainFrame());
			controller.setAccount(user.getAccount());
		}
		else if (USER_ROLE.Manager.equals(USER_ROLE.convertStringToUSER_ROLE(user.getRole()))) {
			
			controller = new ManagerController(new ManagerMainFrame());
			controller.setAccount(user.getAccount());
		}
		else if (USER_ROLE.Admin.equals(USER_ROLE.convertStringToUSER_ROLE(user.getRole()))) {
			
			controller = new AdminController(new AdminMainFrame());
			controller.setAccount(user.getAccount());
		}
		else {
			controller = null;
		}
		
		return controller;
	}
	
	public int initLoginForm() {
		
		loginForm.getSubmitButton().addActionListener(event -> {
			
			User loginUser = loginForm.submit();
			
			loginUser = userService.login(loginUser);
			
			if (null == loginUser) {
				loginForm.setError(Login.WRONG_ACCOUNT_ERROR);
				return;
			}
			
			controller = getControllerBaseOnRole(loginUser);
			if (null == controller) {
				return;
			}
			controller.run();
			
			loginForm.setError(Login.NO_ERROR);
			loginForm.close();
			
		});
		
		loginForm.getRegistrateButton().addActionListener(event -> {
			registrationForm.open();
		});
		
		return 0;
	}
	
	
	public int initRegistrationForm() {
		
		registrationForm.getSubmitButton().addActionListener(event -> {
			
			if (registrationForm.areThereAnyEmptyStarField()) {
				registrationForm.setError(Registration.EMPTY_STAR_FIELD_ERROR);
				return;
			}
			
			if (registrationForm.isPasswordMismatch()) {
				registrationForm.setError(Registration.PASSWORD_MISMATCH_ERROOR);
				return;
			}
			
			User user = registrationForm.submit();
			User other = userService.findUserByUsername(user.getUsername());
			
			//Check if the username is existed
			if (null != other) {
				registrationForm.setError(Registration.EXISTED_USERNAME_ERROR);
				return;
			}
			
			userService.createUser(user);
			
			registrationForm.setError(Registration.NO_ERROR);
			registrationForm.close();
			
			
			JOptionPane.showMessageDialog(loginForm, "Đã đăng ký thành công");
		});
		
		return 0;
	}
	
	public LoginController() {
		
		this.loginForm = new Login();
		this.loginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.registrationForm = new Registration(loginForm);
		this.userService = UserService.INSTANCE;
		
		initLoginForm();
		initRegistrationForm();
		
	}


	public void run() {
		loginForm.open();
	}
	
}
