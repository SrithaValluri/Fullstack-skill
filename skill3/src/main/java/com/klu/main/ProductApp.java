package com.klu.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.klu.entity.Product;

public class ProductApp {

    public static void main(String[] args) {

        Configuration config = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sf = config.buildSessionFactory();
        Session s = sf.openSession();

        Transaction tx = s.beginTransaction();

        Product p1 = new Product("Laptop","Gaming Laptop",100000,10);
        Product p2 = new Product("Mouse","Wireless Mouse",1500,50);
        Product p3 = new Product("Keyboard","Mechanical Keyboard",4000,30);
        Product p4 = new Product("Monitor","LED Monitor",12000,20);
        Product p5 = new Product("Speaker","Bluetooth Speaker",2500,15);
        Product p6 = new Product("Tablet","Android Tablet",30000,8);
        Product p7 = new Product("Phone","Smartphone",60000,25);

        s.persist(p1);
        s.persist(p2);
        s.persist(p3);
        s.persist(p4);
        s.persist(p5);
        s.persist(p6);
        s.persist(p7);

        tx.commit();
        s.close();
        s = sf.openSession();

        System.out.println("Products sorted by price ASC: ");
        Query<Product> q1 = s.createQuery("FROM Product ORDER BY price ASC", Product.class);
        List<Product> list1 = q1.list();
        list1.forEach(System.out::println);

        System.out.println("Products sorted by price DESC: ");
        Query<Product> q2 = s.createQuery("FROM Product ORDER BY price DESC", Product.class);
        q2.list().forEach(System.out::println);

        System.out.println("Products sorted by quantity (highest first): ");
        Query<Product> q3 = s.createQuery("FROM Product ORDER BY quantity DESC", Product.class);
        q3.list().forEach(System.out::println);

        System.out.println("First 3 products: ");
        Query<Product> q4 = s.createQuery("FROM Product", Product.class);
        q4.setFirstResult(0);
        q4.setMaxResults(3);
        q4.list().forEach(System.out::println);

        System.out.println("Next 3 products: ");
        Query<Product> q5 = s.createQuery("FROM Product", Product.class);
        q5.setFirstResult(3);
        q5.setMaxResults(3);
        q5.list().forEach(System.out::println);

        System.out.println("Total Products: ");
        Long total = s.createQuery("SELECT COUNT(*) FROM Product", Long.class).uniqueResult();
        System.out.println("Total Products: " + total);

        System.out.println("Products with quantity > 0: ");
        Long available = s.createQuery(
                "SELECT COUNT(*) FROM Product WHERE quantity > 0", Long.class).uniqueResult();
        System.out.println("Available Products: " + available);

        System.out.println("Count grouped by description: ");
        Query<Object[]> q6 = s.createQuery(
                "SELECT description, COUNT(*) FROM Product GROUP BY description", Object[].class);

        for(Object[] row : q6.list()) {
            System.out.println(row[0] + " : " + row[1]);
        }

        System.out.println("Minimum and Maximum price: ");
        Object[] prices = (Object[]) s.createQuery(
                "SELECT MIN(price), MAX(price) FROM Product").uniqueResult();

        System.out.println("Min Price: " + prices[0]);
        System.out.println("Max Price: " + prices[1]);

        System.out.println("Products between price 2000 and 50000: ");
        Query<Product> q7 = s.createQuery(
                "FROM Product WHERE price BETWEEN 2000 AND 50000", Product.class);
        q7.list().forEach(System.out::println);

        System.out.println("Names starting with 'L': ");
        Query<Product> q8 = s.createQuery(
                "FROM Product WHERE name LIKE 'L%'", Product.class);
        q8.list().forEach(System.out::println);

        System.out.println("Names ending with 'e': ");
        Query<Product> q9 = s.createQuery(
                "FROM Product WHERE name LIKE '%e'", Product.class);
        q9.list().forEach(System.out::println);

        System.out.println("Names containing 'top': ");
        Query<Product> q10 = s.createQuery(
                "FROM Product WHERE name LIKE '%top%'", Product.class);
        q10.list().forEach(System.out::println);

        System.out.println("Names with exactly 5 characters: ");
        Query<Product> q11 = s.createQuery(
                "FROM Product WHERE LENGTH(name) = 5", Product.class);
        q11.list().forEach(System.out::println);

        s.close();
        sf.close();
    }
}