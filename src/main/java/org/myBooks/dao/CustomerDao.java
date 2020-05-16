package org.myBooks.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.myBooks.beans.Customer;
import org.myBooks.services.DBConnect;

public class CustomerDao {

    public static Customer getCustomerByUserName(String userName) {
        Session session = DBConnect.getSession();
        Query q = session.createQuery("from Customer where user_name = :userName", Customer.class);
        q.setParameter("userName",userName);
        Customer c = (Customer)q.uniqueResult();
        return c;
    }

    public static Customer getCustomerByEmail(String email) {
        Session session = DBConnect.getSession();
        Query q = session.createQuery("from Customer where email = :email", Customer.class);
        q.setParameter("email",email);
        Customer c = (Customer)q.uniqueResult();
        return c;
    }
}
