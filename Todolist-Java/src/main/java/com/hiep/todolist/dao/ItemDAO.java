package com.hiep.todolist.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import com.hiep.todolist.entity.Item;

public class ItemDAO {
	

	public static void main(String[] args) {

		List<Item> items = ItemDAO.listItem();
		for (Item item : items) {
			System.out.println(item.getId());
			System.out.println(item.getItemName());
		}
//		ItemDAO.addItem("absdfnasdn");
		
//		List<User> users = UserDAO.ListUser();
//		for(User user: users) {
//			System.out.println(user.getUserName());
//			System.out.println(user.getPassword());
//		}
//		
//		
//		User user = UserDAO.getUser("dinhhiep", "dinhhiep");
//		System.out.println(user.getPassword());
//		System.out.println(user.getUserName());
//		System.out.println(user.getRoles());
	}
	private static SessionFactory factory;

	// add Item
	public static void addItem(String itemName) {
		try {
			Configuration conf = new Configuration();
			Configuration conf1 = conf.configure();
			factory = conf1.buildSessionFactory();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		Session session = factory.getCurrentSession();
		Transaction tx = null;
        
        try {
			tx = session.beginTransaction();
			Item item = new Item(itemName);
			session.save(item);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
                tx.rollback();
            e.printStackTrace();
		}finally {
			session.close();
		}
	}

	
	// list Item
	public static List<Item> listItem() {
		List<Item> itemList = new ArrayList<Item>();
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
			List items = session.createQuery("FROM Item").list();
			for (Iterator iterator = items.iterator(); iterator.hasNext();) {
				Item item = (Item) iterator.next();
				itemList.add(item);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();

		} finally {
			session.close();
		}
		
		System.out.println("successfully");
		return itemList;
	}
	
	
	//delete item
	public static void delItem(int id) {
		try {
			Configuration conf = new Configuration();
			Configuration conf1 = conf.configure();
			factory = conf1.buildSessionFactory();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
            tx = session.beginTransaction();
            Item item = (Item) session.get(Item.class, id);
            session.delete(item);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

	}
}


//public class ItemDAO {
//	private static SessionFactory factory;
//
//	public static void main(String[] args) {
//
//		List<Item> items = ItemDAO.listItem();
//		for (Item item : items) {
//			System.out.println(item.getId());
//			System.out.println(item.getItemName());
//		}
//		ItemDAO.addItem("absdfnasdn");
//	}
//
//	// add Item
//	public static void addItem(String itemName) {
//		SessionFactory factory = HibernateUtils.getSessionFactory();
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//        
//        try {
//			tx = session.beginTransaction();
//			Item item = new Item(itemName);
//			session.save(item);
//			tx.commit();
//		} catch (HibernateException e) {
//			if (tx != null)
//                tx.rollback();
//            e.printStackTrace();
//		}finally {
//			session.close();
//		}
//	}
//
//	
//	// list Item
//	public static List<Item> listItem() {
//		List<Item> itemList = new ArrayList<Item>();
//		SessionFactory factory = HibernateUtils.getSessionFactory();
//		Session session = factory.getCurrentSession();
//	
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			List items = session.createQuery("FROM Item").list();
//			for (Iterator iterator = items.iterator(); iterator.hasNext();) {
//				Item item = (Item) iterator.next();
//				itemList.add(item);
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
//		
//		System.out.println("hiep");
//		return itemList;
//	}
//	
//	
//	//delete item
//	public static void delItem(int id) {
//		try {
//			Configuration conf = new Configuration();
//			Configuration conf1 = conf.configure();
//			factory = conf1.buildSessionFactory();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		Session session = factory.openSession();
//		Transaction tx = null;
//		try {
//            tx = session.beginTransaction();
//            Item item = (Item) session.get(Item.class, id);
//            session.delete(item);
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null)
//                tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//
//	}
//}
