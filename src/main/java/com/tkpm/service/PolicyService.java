package com.tkpm.service;

import java.time.LocalTime;

import com.tkpm.dao.PolicyDAO;
import com.tkpm.entities.Flight;
import com.tkpm.entities.Policy;
import com.tkpm.entities.Reservation;

//Using enum for applying Singleton Pattern
public enum PolicyService {

	INSTANCE;
	
	private PolicyDAO policyDAO;
	
	private PolicyService() {
		policyDAO = PolicyDAO.INSTANCE;
	}
	
	public Policy getPolicy() {
		return policyDAO.getPolicy();
	}
	
	public Boolean isLateToBook(Flight flight) {
		
		//TODO:
		
		return null;
	}
	
	public Boolean isLateToCancel(Flight flight) {
		
		//TODO:
		
		return null;
	}
}
