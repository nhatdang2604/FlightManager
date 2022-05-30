package com.tkpm.entry_point;

import com.tkpm.view.frame.form.LoginForm;
import com.tkpm.view.frame.form.RegistrationForm;

public class Main {

	public static void main(String[] args) {
		LoginForm logForm = new LoginForm();
		RegistrationForm regForm = new RegistrationForm(logForm);
		logForm.getRegistrateButton().addActionListener(event -> {
			regForm.open();
		});
		logForm.open();
	}

}
