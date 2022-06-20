package com.tkpm.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.tkpm.entities.Airport;
import com.tkpm.entities.Ticket;
import com.tkpm.utils.HibernateUtil;

//Using enum for applying Singleton Pattern
public enum AirportDAO {

	INSTANCE;
	
	private SessionFactory factory;
	
	private AirportDAO() {
		factory = HibernateUtil.INSTANCE.getSessionFactory();
	}
	

	public Airport find(String name) {
		Session session = factory.getCurrentSession();
		Airport airport = null;
		
		try {
			session.beginTransaction();
			
			String param = "name";
			String query = "from " + Airport.class.getName() + " a where a.name = :" + param;
			
			airport = session
					.createQuery(query, Airport.class)
					.setParameter(param, name)
					.setMaxResults(1)
					.stream()
					.findFirst()
					.orElse(null);
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	
		return airport;
	}

	public int delete(List<Integer> ids) {

		Session session = factory.getCurrentSession();
		int errorCode = 0;
		
		try {
			session.beginTransaction();
			
			//Get param to delete reservation
			int size = ids.size();
			List<String> params = new LinkedList<>();
			for (int i = 0; i <size; ++i) {
				params.add("param" + i);
			}
			
			//Build the id set
			StringBuilder builder = new StringBuilder();
			builder.append("(-1");	//Using -1 for dynamically without checking "," if there is only 1 element in ids 
			for (String param: params) {
				builder.append(", :" + param);
			}
			builder.append(")");
			String idSet = builder.toString();
			
			//Query to delete
			String query = 
					"delete " +
					"from " + Airport.class.getName() + " " + 
					"where id in " + idSet;
		
			Query<Ticket> hql = session.createQuery(query);
			for (int i = 0; i < size; ++i) {
				hql = hql.setParameter(params.get(i), ids.get(i));
			}
			hql.executeUpdate();
			
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			session.getTransaction().rollback();
			errorCode = 1;
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	
		return errorCode;
	}
}
 