package org.myBooks.services;

import org.myBooks.beans.Customer;
import org.myBooks.dao.CustomerDao;

public class AuthenticationService {

    public boolean authenticate(String key , String pwd) {
        Customer c = null;
        if(key.indexOf("@") < 0)
            c = CustomerDao.getCustomerByUserName(key);
        else
            c = CustomerDao.getCustomerByEmail(key);
        if(c == null) return false;
        return c.getPwd().equals(pwd);
    }
}
