package com.tkpm.entry_point;

import com.tkpm.controller.ManagerController;
import com.tkpm.view.frame.ManagerMainFrame;
import com.tkpm.view.frame.form.Login;
import com.tkpm.view.frame.form.Registration;

public class Main {

	public static void main(String[] args) {
		//LoginForm logForm = new LoginForm();
		Login logForm = new Login();
		//RegistrationForm regForm = new RegistrationForm(logForm);
		Registration regForm = new Registration();
		logForm.getRegistrateButton().addActionListener(event -> {
			logForm.close();
			regForm.open();
		});
		regForm.getSubmitButton().addActionListener(event -> {
			logForm.open();
			regForm.close();
		});
		regForm.getCancelButton().addActionListener(event -> {
			logForm.open();
		});
		//logForm.open();
		//AirportTransitionForm airportTransitionForm = new AirportTransitionForm();
		//airportTransitionForm.open();
		
		//BaseMainFrame mainFrame = new CustomerMainFrame();
		//BaseMainFrame mainFrame = new ManagerMainFrame();
//		BaseMainFrame mainFrame = new AdminMainFrame();
//		mainFrame.open();
		
		ManagerController controller = new ManagerController(new ManagerMainFrame());
		controller.run();
		
	}

}
