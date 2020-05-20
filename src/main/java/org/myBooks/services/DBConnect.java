package org.myBooks.services;

import org.hibernate.Session;
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
    }
    private DBConnect() {
        if(conf == null || sf == null) {
            dbc = this;
            conf = new Configuration();
            conf.addAnnotatedClass(Customer.class).addAnnotatedClass(Vendor.class);
            sf = conf.buildSessionFactory();
        }
    }
    public static Session getSession() {
        if(sf == null) new DBConnect();
        return sf.openSession();
    }
}
