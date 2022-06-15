package com.tkpm.entry_point;

import com.tkpm.controller.LoginController;

public class Main {

	public static void main(String[] args) {
		//LoginForm logForm = new LoginForm();
//		Login logForm = new Login();
//		//RegistrationForm regForm = new RegistrationForm(logForm);
//		Registration regForm = new Registration();
//		logForm.getRegistrateButton().addActionListener(event -> {
//			logForm.close();
//			regForm.open();
//		});
//		regForm.getSubmitButton().addActionListener(event -> {
//			logForm.open();
//			regForm.close();
//		});
//		regForm.getCancelButton().addActionListener(event -> {
//			logForm.open();
//		});
		//logForm.open();
		//AirportTransitionForm airportTransitionForm = new AirportTransitionForm();
		//airportTransitionForm.open();
		
		//BaseMainFrame mainFrame = new CustomerMainFrame();
		//BaseMainFrame mainFrame = new ManagerMainFrame();
//		BaseMainFrame mainFrame = new AdminMainFrame();
//		mainFrame.open();
		
//		//User user = UserService.INSTANCE.getUsersWithReservations(Arrays.asList(8)).get(0);
//		AdminController controller = new AdminController(new AdminMainFrame());
//		//controller.setAccount(user.getAccount());
//		controller.run();
		
		LoginController controller = new LoginController();
		controller.run();
		
	}

}
