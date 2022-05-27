package com.tkpm.view.frame.form;

import javax.swing.JButton;

public interface FormBehaviour {
	
	public Object submit();
	public JButton getSubmitButton();
	public FormBehaviour setError(int errorCode);
	public boolean isValidForm();
	public void clear();
	public void close();
}
