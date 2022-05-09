package com.tkpm.dao;

import org.hibernate.SessionFactory;

import com.tkpm.utils.HibernateUtil;

//Using enum for applying Singleton Pattern
public enum PolicyDAO {

	INSTANCE;
	
	private SessionFactory factory;
	
	private PolicyDAO() {
		factory = HibernateUtil.INSTANCE.getSessionFactory();
	}
}
