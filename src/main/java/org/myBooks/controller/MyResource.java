package org.myBooks.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.myBooks.beans.Customer;
import org.myBooks.beans.Vendor;
import org.myBooks.dao.CustomerDao;
import org.myBooks.services.DBConnect;
import org.myBooks.services.HTMLScraper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
//    @Path("/do")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

//    public static void main(String arg[]) {
//        Session session = DBConnect.getSession();
//        Customer s = new Customer();
//        s.setUser_name("vamceep");
//        s.setPwd("abs");
//        s.setLast_name("ponnada");
//        s.setFirst_name("vamsi");
//        s.setEmail("vamcee.p@gmail.com");
//        Vendor v = new Vendor();
//        v.setName("Crossword.in");
//        v.setHome("https://www.crossword.in/");
//        v.setQueryUrl("https://www.crossword.in/search?q=ISBN10");

//
//        Vendor v2 = new Vendor();
//        v2.setName("Amazon.in");
//        v2.setHome("https://www.amazon.in/");
//        v2.setQueryUrl("https://www.amazon.in/s?k=ISBN10&ref=nb_sb_noss");
//
//        Transaction tx = session.beginTransaction();
////        session.save(v);
//        session.save(v2);
//        tx.commit();

//        Query q = session.createQuery("from Customer where user_name =:v", Customer.class);
//        q.setParameter("v", "vamceep");
//    }
}
