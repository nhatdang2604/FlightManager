package com.tkpm.service;

import com.tkpm.dao.PolicyDAO;

//Using enum for applying Singleton Pattern
public enum PolicyService {

	INSTANCE;
	
	private PolicyDAO policyDAO;
	
	private PolicyService() {
		policyDAO = PolicyDAO.INSTANCE;
	}
}
