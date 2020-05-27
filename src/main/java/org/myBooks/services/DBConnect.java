package org.myBooks.services;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.myBooks.beans.Customer;
import org.myBooks.beans.Vendor;

public class DBConnect {
    static DBConnect dbc;
    static Configuration conf;
    static SessionFactory sf;
    static {
        conf = new Configuration();
        conf.addAnnotatedClass(Customer.class).addAnnotatedClass(Vendor.class);
        sf = conf.buildSessionFactory();


        dothis();
    }
    private DBConnect() {
        if(conf == null || sf == null) {
            dbc = this;
            conf = new Configuration();
            conf.addAnnotatedClass(Customer.class).addAnnotatedClass(Vendor.class);
            sf = conf.buildSessionFactory();

            dothis();
        }
    }
    public static Session getSession() {
        if(sf == null) new DBConnect();
        return sf.openSession();
    }

    private static void dothis(){
        Session session = DBConnect.getSession();
        Vendor v = new Vendor();
        v.setName("Crossword.in");
        v.setHome("https://www.crossword.in/");
        v.setQueryUrl("https://www.crossword.in/search?q=ISBN10");


        Vendor v2 = new Vendor();
        v2.setName("Amazon.in");
        v2.setHome("https://www.amazon.in/");
        v2.setQueryUrl("https://www.amazon.in/s?k=ISBN10&ref=nb_sb_noss");

        Transaction tx = session.beginTransaction();
        session.save(v);
        session.save(v2);
        tx.commit();
    }
}