package com.tkpm.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tkpm.entities.AdminAccount;
import com.tkpm.entities.BaseAccount;
import com.tkpm.entities.CustomerAccount;
import com.tkpm.entities.ManagerAccount;
import com.tkpm.utils.HibernateUtil;

//Using enum for applying Singleton Pattern
public enum AccountDAO {

	INSTANCE;
	
	private SessionFactory factory;
	
	private AccountDAO() {
		factory = HibernateUtil.INSTANCE.getSessionFactory();
	}
}
 