package com.hiep.todolist.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import com.hiep.todolist.entity.User;

public class UserDAO {
	private static SessionFactory factory;
	
//	public static List<User> ListUser(){
//		List<User> users = new ArrayList<User>();
//		try {
//			Configuration conf = new Configuration();
//			Configuration conf1 = conf.configure();
//			factory = conf1.buildSessionFactory();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
////		System.out.println("abcxyx"+factory);
//		Session session = factory.openSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			List items = session.createQuery("FROM User").list();
//			for (Iterator iterator = items.iterator(); iterator.hasNext();) {
//				User user = (User) iterator.next();
//				users.add(user);
//			}
//			tx.commit();
//		} catch (HibernateException e) {
//			if (tx != null)
//				tx.rollback();
//			e.printStackTrace();
//
//		} finally {
//			session.close();
//		}
//		return users;
//	}
	
	public static User getUser(String userName, String pass) {
		User _user = new User();
		try {
			Configuration conf = new Configuration();
			Configuration conf1 = conf.configure();
			factory = conf1.buildSessionFactory();
		} catch (Throwable e) {
			e.printStackTrace();
		}
//		System.out.println("abcxyx"+factory);
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List users = session.createQuery("FROM User WHERE user_name = \'"+ userName + "\' AND password = \'" + pass +"\'").list();
			for (Iterator iterator = users.iterator(); iterator.hasNext();) {
				User user = (User) iterator.next();
				_user = user;
				break;
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();

		} finally {
			session.close();
		}
		if (_user != null) {
			return _user;
		} else {
			return null;
		}
	}
}
