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
	
	//Create new account
		public BaseAccount create(BaseAccount account) {
			
			Session session = factory.getCurrentSession();
			
			try {
				session.beginTransaction();
				
				//Save the account to database
				Integer id = (Integer) session.save(account);
				
				//Update the current id for the given account
				account.setId(id);
				
			} catch (Exception ex) {
				ex.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.getTransaction().commit();
				session.close();
			}
			
			return account;
		}
		
}
 