package com.klu.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.klu.entity.Product;




public class ProductApp {
	public static void main(String[] args) {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sf = config.buildSessionFactory();
		Session s = sf.openSession();

		Product e = new Product("Laptop", "Gaming Laptop", 100000, 15); 
		// INSERTION
		Transaction tx = s.beginTransaction();
		tx.commit();
		s.close();
		// RETRIEVE
		s = sf.openSession();
		Product product = s.find(Product.class, 1);
		System.out.println(product);
		s.close();
		// UPDATE
		s = sf.openSession();
		Product prod1 = s.find(Product.class, 3);
		prod1.setName("Earpods");
		prod1.setDescription("Wireless");
		s.merge(prod1);
		tx = s.beginTransaction();
		tx.commit();
		s.close();
		// DELETE
		s = sf.openSession();
		Product prod2 = s.find(Product.class, 4);
		s.remove(prod2);
		tx = s.beginTransaction();
		tx.commit();
		s.close();
		sf.close();
	}
}
