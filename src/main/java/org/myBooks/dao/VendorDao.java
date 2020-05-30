package org.myBooks.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.myBooks.beans.Vendor;
import org.myBooks.services.DBConnect;
import org.myBooks.services.HTMLScraper;

import java.util.List;

public class VendorDao {
    public List<Vendor> getAll() {
        Session session = DBConnect.getSession();
        Query q = session.createQuery("FROM Vendor", Vendor.class);
        List<Vendor> vendors = q.list();
        //for(Vendor v:vendors)
        //System.out.println(v.getQueryUrl());
        return vendors;
    }
}
